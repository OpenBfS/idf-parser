/* Copyright (C) 2013 by Bundesamt fuer Strahlenschutz
 * Software engineering by Intevation GmbH
 *
 * This file is Free Software under the GNU GPL (v>=3)
 * and comes with ABSOLUTELY NO WARRANTY! Check out
 * the documentation coming with IMIS-Labordaten-Application for details.
 */
package de.bfs.idfparser.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;

import org.locationtech.jts.geom.Coordinate;

/**
 * Utilities for coordinate transformations.
 *
 */
public class KdaUtil {


    /* Represents geodetic coordinates in decimal notation */
    public static final int KDA_GD = 1;

    /* Represents coordinates in Gauß-Krüger CRS */
    public static final int KDA_GK = 3;

    /* Represents coordinates in UTM CRS with WGS84 datum */
    public static final int KDA_UTM_WGS84 = 6;

    /* Expected format of projected input coordinates */
    private static final Pattern X_GK = Pattern.compile(
        "\\d{7,9}(\\.\\d*)?");
    private static final Pattern X_UTM = Pattern.compile(
        "\\d{6,8}(\\.\\d*)?");
    private static final Pattern Y = Pattern.compile(
        "(\\+|-)?\\d{1,7}(\\.\\d*)?");

    /* Expected format of sexagesimal input coordinates */
    // with decimal separator
    private static final Pattern LON_DEC = Pattern.compile(
        "([+|\\-|W|E]?)(\\d{1,3})(\\d{2})(\\d{2})\\.(\\d{1,5})([W|E]?)");
    private static final Pattern LAT_DEC = Pattern.compile(
        "([+|\\-|N|S]?)(\\d{1,2})(\\d{2})(\\d{2})\\.(\\d{1,5})([N|S]?)");
    // Without decimal separator, can include leading zeros
    private static final Pattern LON = Pattern.compile(
        "([+|\\-|W|E]?)(\\d{3})(\\d{0,2})(\\d{0,2})([W|E]?)");
    private static final Pattern LAT = Pattern.compile(
        "([+|\\-|N|S]?)(\\d{2})(\\d{0,2})(\\d{0,2})([N|S]?)");

    /*
     * If eastings should be prefixed with a zone number, multiply zone
     * number with this value and add it to the easting
     */
    static final double ZONE_PREFIX_MULTIPLIER = 1e6;

    /*
     * DecimalFormat pattern for eastings including zone prefix
     */
    static final String EASTING_PATTERN = "0000000.###";

    /*
     * DecimalFormat pattern for northings
     */
    static final String NORTHING_PATTERN = "0.###";

    /*
     * Maximum allowed values for longitude and latitude
     */
    private static final double MAX_LON = 180, MAX_LAT = 90;

    /**
     * Representation of transformation result.
     */
    public class Result {
        // Easting or longitude
        private String x;

        // Northing or latitude
        private String y;

        Result(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public String getX() {
            return this.x;
        }

        void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return this.y;
        }

        void setY(String y) {
            this.y = y;
        }
    }

    /**
     * Transform coordinates.
     * @param kdaFrom KDA of given coordinates
     * @param kdaTo KDA to be transformed to
     * @param x Easting or longitude
     * @param y Northing or latitude
     * @return Result with transformed coordinates
     */
    public Result transform(
        int kdaFrom, int kdaTo, String x, String y
    ) {
        if (x == null || y == null) {
            return null;
        }
        x = x.replace(',', '.');
        y = y.replace(',', '.');
        Transform t;
        try {
            switch (kdaFrom) {
                case KDA_GK: t = new TransformGK(x, y); break;
                case KDA_GD: t = this.new TransformGD(x, y); break;
                case KDA_UTM_WGS84: t = this.new TransformUTM_WGS84(x, y); break;
                default: return null;
            }
        } catch (ValidationException | FactoryException fe) {
            return null;
        }
        return t.transform(kdaTo);
    }

    /**
     * Defines the methods to be implemented for coordinate transformation.
     */
    private interface Transform {
        void isInputValid() throws ValidationException;
        Result transform(int to);
        Result transformToGK();
        Result transformToGD();
        Result transformToUTM_WGS84();
    }

    /**
     * Exception to be thrown on invalid coordinate input.
     */
    private class ValidationException extends Exception { };

    /**
     * Delegates to a class per input KDA.
     */
    private abstract class AbstractTransform implements Transform {
        // Input coordinates
        protected String x;
        protected String y;

        // CRS of input coordinates
        protected CoordinateReferenceSystem crs;

        AbstractTransform(String x, String y) throws ValidationException {
            this.x = x;
            this.y = y;
            isInputValid();
        }

        public Result transform(int to) {
            switch (to) {
                case KDA_GK: return transformToGK();
                case KDA_GD: return transformToGD();
                case KDA_UTM_WGS84: return transformToUTM_WGS84();
                default: return null;
            }
        }
    }

    /**
     * Implements coordinate transformations for Gauß-Krüger input.
     */
    private class TransformGK extends AbstractTransform {

        TransformGK(
            String x,
            String y
        ) throws ValidationException, FactoryException {
            super(x, y);
            this.crs = getCRSForGK(x);
        }

        @Override
        public void isInputValid() throws ValidationException {
            if (!(X_GK.matcher(x).matches() && Y.matcher(y).matches())) {
                throw new ValidationException();
            }
        }

        @Override
        public Result transformToGK() {
            return new Result(x, y);
        }

        @Override
        public Result transformToGD() {
            Result coords = jtsTransform(crs, "EPSG:4326", y, x);
            if (coords == null) {
                return null;
            }
            String coordX = coords.getX();
            String coordY = coords.getY();
            int maxLenX = coordX.length() - coordX.indexOf(".");
            int precX = maxLenX < 7 ? maxLenX : 7;
            int maxLenY = coordY.length() - coordY.indexOf(".");
            int precY = maxLenY < 7 ? maxLenY : 7;
            coordX = coordX.substring(0, coordX.indexOf(".") + precX);
            coordY = coordY.substring(0, coordY.indexOf(".") + precY);
            return new Result(coordY, coordX);
        }

        @Override
        public Result transformToUTM_WGS84() {
            Result degrees = jtsTransform(crs, "EPSG:4326", y, x);
            String epsgWGS = getWgsUtmEpsg(
                Double.parseDouble(degrees.getY()),
                Double.parseDouble(degrees.getX()));
            Result coords = jtsTransform(crs,
                epsgWGS,
                y,
                x);
            if (coords == null) {
                return null;
            }
            coords.setX(epsgWGS.substring(
                epsgWGS.length() - 2,
                epsgWGS.length()) + coords.getX());
            String coordX = String.valueOf(Math.round(Double.valueOf(coords.getX())));
            String coordY = String.valueOf(Math.round(Double.valueOf(coords.getY())));
            coords.setX(coordX);
            coords.setY(coordY);
            return coords;
        }
    }

    /**
     * Implements coordinate transformations for decimal geodetic input.
     */
    private class TransformGD extends AbstractTransform {
        TransformGD(String x, String y) throws ValidationException {
            super(transformX(x), transformY(y));
        }

        @Override
        public void isInputValid() throws ValidationException {
            double dX = Double.parseDouble(x);
            double dY = Double.parseDouble(y);
            try {
                if (dX < -MAX_LON || dX > MAX_LON
                    || dY < -MAX_LAT || dY > MAX_LAT
                ) {
                    throw new ValidationException();
                }
            } catch (NumberFormatException nfe) {
                throw new ValidationException();
            }
        }

        @Override
        public Result transformToGK() {
            String epsgGk = getGkEpsg(Double.valueOf(x), Double.valueOf(y));
            Result coords = jtsTransform("EPSG:4326", epsgGk, y, x);
            if (coords == null) {
                return null;
            }
            String coordX = String.valueOf(Math.round(Double.valueOf(coords.getX())));
            String coordY = String.valueOf(Math.round(Double.valueOf(coords.getY())));
            coords.setX(coordY);
            coords.setY(coordX);
            return coords;
        }

        @Override
        public Result transformToGD() {
            return new Result(x, y);
        }

        @Override
        public Result transformToUTM_WGS84() {
            String epsgWgs = getWgsUtmEpsg(
                Double.valueOf(x), Double.valueOf(y));
            Result coords = jtsTransform("EPSG:4326", epsgWgs, y, x);
            if (coords == null) {
                return null;
            }
            coords.setX(epsgWgs.substring(
                epsgWgs.length() - 2,
                epsgWgs.length()) + coords.getX());
            String coordX = String.valueOf(Math.round(Double.valueOf(coords.getX())));
            String coordY = String.valueOf(Math.round(Double.valueOf(coords.getY())));
            coords.setX(coordX);
            coords.setY(coordY);
            return coords;
        }
    }

    /**
     * Implements coordinate transformations for UTM-WGS84 input.
     */
    private class TransformUTM_WGS84 extends AbstractTransform {
        TransformUTM_WGS84(
            String x,
            String y
        ) throws ValidationException, FactoryException {
            super(x, y);
            this.crs = getCRSForWgsUtm(x);
        }

        @Override
        public void isInputValid() throws ValidationException {
            if (!(X_UTM.matcher(x).matches() && Y.matcher(y).matches())) {
                throw new ValidationException();
            }
        }

        @Override
        public Result transformToGK() {
            x = x.substring(2, x.length());
            Result degrees = jtsTransform(crs, "EPSG:4326", x, y);
            if (degrees == null) {
                return null;
            }
            String epsgGk = getGkEpsg(
                Double.parseDouble(degrees.getY()),
                Double.parseDouble(degrees.getX()));
            Result coords = jtsTransform(crs, epsgGk, x, y);
            if (coords == null) {
                return null;
            }
            String coordX = String.valueOf(Math.round(Double.valueOf(coords.getX())));
            String coordY = String.valueOf(Math.round(Double.valueOf(coords.getY())));
            coords.setX(coordY);
            coords.setY(coordX);
            return coords;
        }

        @Override
        public Result transformToGD() {
            x = x.substring(2, x.length());
            Result coords = jtsTransform(crs, "EPSG:4326", x, y);
            if (coords == null) {
                return null;
            }
            String coordX = coords.getX();
            String coordY = coords.getY();
            int maxLenX = coordX.length() - coordX.indexOf(".");
            int precX = maxLenX < 7 ? maxLenX : 7;
            int maxLenY = coordY.length() - coordY.indexOf(".");
            int precY = maxLenY < 7 ? maxLenY : 7;
            coordX = coordX.substring(0, coordX.indexOf(".") + precX);
            coordY = coordY.substring(0, coordY.indexOf(".") + precY);
            coords.setX(coordY);
            coords.setY(coordX);
            return coords;
        }

        @Override
        public Result transformToUTM_WGS84() {
            return new Result(x, y);
        }
    }

    /**
     * Transform given coordinates from epsgFrom to epsgTo.
     * Returns null in case a given EPSG code is invalid.
     */
    private Result jtsTransform(
        String epsgFrom,
        String epsgTo,
        String x,
        String y
    ) {
        CoordinateReferenceSystem src;
        try {
            src = CRS.decode(epsgFrom);
        } catch (FactoryException fe) {
            return null;
        }
        return jtsTransform(src, epsgTo, x, y);
    }

    /**
     * Transform given coordinates from CRS to epsgTo.
     * Returns null in case the given EPSG code is invalid.
     */
    private Result jtsTransform(
        CoordinateReferenceSystem src,
        String epsgTo,
        String x,
        String y
    ) {
        try {
            CoordinateReferenceSystem target = CRS.decode(epsgTo);

            MathTransform transform = CRS.findMathTransform(src, target);
            Coordinate srcCoord = new Coordinate();
            srcCoord.y = Double.valueOf(y);
            srcCoord.x = Double.valueOf(x);
            Coordinate targetCoord = new Coordinate();
            JTS.transform(srcCoord, targetCoord, transform);
            return new Result(
                String.valueOf(targetCoord.x),
                String.valueOf(targetCoord.y));
        } catch (FactoryException | TransformException e) {
            return null;
        }
    }

    /**
     * Convert degrees in sexagesimal notation into decimal notation.
     * @param x Longitude in sexagesimal notation.
     * @param y Latitude in sexagesimal notation.
     * @return Result with coordinates in decimal notation.
     */
    protected Result arcToDegree(String x, String y) {
        int xDegree = 0;
        int xMin = 0;
        int yDegree = 0;
        int yMin = 0;
        double xSec = 0;
        double ySec = 0;
        String xPrefix = "";
        String xSuffix = "";
        String yPrefix = "";
        String ySuffix = "";
        try {
            if (x.contains(".")) {
                Matcher m = LON_DEC.matcher(x);
                m.matches();
                xPrefix = m.group(1);
                xDegree = Integer.valueOf(m.group(2));
                xMin = Integer.valueOf(m.group(3));
                xSec = Double.valueOf(m.group(4) + "." + m.group(5));
                xSuffix = m.group(6);
            } else {
                Matcher m = LON.matcher(x);
                m.matches();
                xPrefix = m.group(1);
                xDegree = Integer.valueOf(m.group(2));
                xMin = Integer.valueOf(
                    !m.group(3).isEmpty() ? m.group(3) : "0");
                xSec = Double.valueOf(
                    !m.group(4).isEmpty() ? m.group(4) : "0.0");
                xSuffix = m.group(5);
            }
            if (y.contains(".")) {
                Matcher m = LAT_DEC.matcher(y);
                m.matches();
                yPrefix = m.group(1);
                yDegree = Integer.valueOf(m.group(2));
                yMin = Integer.valueOf(m.group(3));
                ySec = Double.valueOf(m.group(4) + "." + m.group(5));
                ySuffix = m.group(6);
            } else {
                Matcher m = LAT.matcher(y);
                m.matches();
                yPrefix = m.group(1);
                yDegree = Integer.valueOf(m.group(2));
                yMin = Integer.valueOf(
                    !m.group(3).isEmpty() ? m.group(3) : "0");
                ySec = Double.valueOf(
                    !m.group(4).isEmpty() ? m.group(4) : "0.0");
                ySuffix = m.group(5);
            }
        } catch (IllegalStateException e) {
            return null;
        }

        double ddX = xDegree + ((xMin / 60d) + (xSec / 3600d));
        double ddY = yDegree + ((yMin / 60d) + (ySec / 3600d));

        if ((xPrefix != null && (xPrefix.equals("-") || xPrefix.equals("W")))
            || (xSuffix != null && xSuffix.equals("W"))
        ) {
            ddX = ddX * -1;
        }
        if ((yPrefix != null && (yPrefix.equals("-") || yPrefix.equals("S")))
            || (ySuffix != null && (ySuffix.equals("S")))
        ) {
            ddY = ddY * -1;
        }
        return new Result(String.valueOf(ddX), String.valueOf(ddY));
    }

    private String getWgsUtmEpsg(double x, double y) {
        int pref;
        if (y > 0) {
            pref = 32600;
        } else {
            pref = 32700;
        }
        int code = pref + getUTMZone(x);
        return "EPSG:" + code;
    }

    private String getGkEpsg(double x, double y) {
        int code = 31460;
        int ref = (int) Math.round(x / 3);
        switch (ref) {
            case 2: code += 6; break;
            case 3: code += 7; break;
            case 4: code += 8; break;
            case 5: code += 9; break;
            default: break;
        }
        return "EPSG:" + code;
    }

    private CoordinateReferenceSystem getCRSForWgsUtm(
        String x
    ) throws FactoryException {
        String epsg = "EPSG:326";
        String part = x.split("\\.")[0];
        String zone = part.length() == 7
            ? ("0" + part.substring(0, 1))
            : part.substring(0, 2);
        return CRS.decode(epsg + zone);
    }

    private CoordinateReferenceSystem getCRSForGK(
        String x
    ) throws FactoryException {
        // Only one-digit zone numbers match the expected EPSG codes
        final int acceptedXLength = 7;
        if (x.split("\\.")[0].length() != acceptedXLength) {
            throw new FactoryException();
        }
        String zoneNumber = x.substring(0, 1);

        String epsgSuffix;
        switch (zoneNumber) {
            case "2": epsgSuffix = "6"; break;
            case "3": epsgSuffix = "7"; break;
            case "4": epsgSuffix = "8"; break;
            case "5": epsgSuffix = "9"; break;
            default: throw new FactoryException();
        }

        return CRS.decode("EPSG:3146" + epsgSuffix);
    }

    /*
     * Get UTM zone for given longitude
     */
    private static int getUTMZone(double lon) {
        return (int) Math.floor((lon + MAX_LON) / 6) + 1;
    }

    private static String transformX(String x) {
        Double dX;
        if (x.startsWith("E")) {
            dX = Double.parseDouble(x.substring(1, x.length()));
        }
        else if (x.startsWith("W")) {
            dX = Double.parseDouble(x.substring(1, x.length()));
            dX *= -1;
        }
        else {
            dX = Double.parseDouble(x);
        }
        return dX.toString();
    }

    private static String transformY(String y) {
        Double dY;
        if (y.startsWith("N")) {
            dY = Double.parseDouble(y.substring(1, y.length()));
        } else if (y.startsWith("S")) {
            dY = Double.parseDouble(y.substring(1, y.length()));
            dY *= -1;
        }
        else {
            dY = Double.parseDouble(y);
        }
        return dY.toString();
    }
}
