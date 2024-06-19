package de.bfs.idfparser.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.logging.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import de.bfs.idfparser.Data;
import de.bfs.idfparser.model.Header;
import de.bfs.idfparser.model.IdfJson;
import de.bfs.idfparser.model.Locality;
import de.bfs.idfparser.model.Measure;
import de.bfs.idfparser.model.Nuclide;


/**
 * Converter class to convert JSON to IDF
 * @author Alexander Woestmann <awoestmann@intevation.de>
 */
public class IdfJsonParser {

    private static Logger LOG = Logger.getLogger("IDFJSONParser");

    private static final String KEYWORD_DATA = "DATA";
    private static final String KEYWORD_SITE = "SITE";
    private static final String KEYWORD_TEST = "TEST";
    private static final String EOF = "ZZZZZ";
    private static final String NEWLINE = "\n";

    private IdfJson idfJsonObj;

    private String result;
    private String header;
    private String data;
    private String testData;
    private String site;

    //Idf date format
    private SimpleDateFormat dateFormat;

    // Static data loaded from resources
    Data staticData;

    public IdfJsonParser() {
        dateFormat = new SimpleDateFormat("YYMMddhhmmss");
        try {
            staticData = Data.getInstance();
        } catch (IOException e) {
            LOG.warning("Could not load static data");
            return;
        }
    }

    /**
     * Converts the given json input stream to an idf string.
     * @param jsonStream Json as stream
     * @return Result as String
     * @throws InvalidHeaderException Thrown if header is invalid
     * @throws MissingMandatoryFieldException Thrown if mandatory fields are missing
     * @throws ParseException Thrown if header date is unparsable
     */
    public String convert(InputStream jsonStream)
            throws InvalidHeaderException, MissingMandatoryFieldException, ParseException {
        LOG.info("Converting json file");
        Jsonb jsonb = JsonbBuilder.create();
        idfJsonObj = jsonb.fromJson(jsonStream, IdfJson.class);
        return doConvert(idfJsonObj, getHeader());
    }

    /**
     * Converts the given json input stream to an idf string.
     * @param jsonStream Json as stream
     * @param header Header to use
     * @return Result as String
     * @throws InvalidHeaderException Thrown if header is invalid
     * @throws MissingMandatoryFieldException Thrown if mandatory fields are missing
     */
    public String convert(InputStream jsonStream, String header)
            throws InvalidHeaderException, MissingMandatoryFieldException {
        LOG.info("Converting json file");
        Jsonb jsonb = JsonbBuilder.create();
        idfJsonObj = jsonb.fromJson(jsonStream, IdfJson.class);
        return doConvert(idfJsonObj, header);
    }

    private String doConvert(IdfJson idfJsonObj, String header)
            throws InvalidHeaderException, MissingMandatoryFieldException{
        checkHeader(header);
        this.header = header;
        LOG.info(
            String.format("Data entries: %d, Site entries: %d", idfJsonObj.getData().size(), idfJsonObj.getSite().size()));
        buildData(idfJsonObj.getData());
        buildSites(idfJsonObj.getSite());
        result = buildResult();
        return result;
    }

    /**
     * Build the result string.
     * @return Result as String
     */
    private String buildResult() {
        StringBuilder result = new StringBuilder();
        result.append(header).append(NEWLINE);
        if (data != null && data.length() > 0) {
            result.append(KEYWORD_DATA)
            .append(NEWLINE)
            .append(data)
            .append(NEWLINE);
        }
        if (testData != null && testData.length() > 0) {
            result.append(KEYWORD_TEST)
            .append(" ")
            .append(KEYWORD_DATA)
            .append(NEWLINE)
            .append(testData)
            .append(NEWLINE);
        }
        if (site != null && site.length() > 0) {
            result.append(KEYWORD_SITE)
            .append(NEWLINE)
            .append(site)
            .append(NEWLINE);
        }
        result.append(EOF);
        return result.toString();
    }

    /**
     * Get header string from idf json
     * @return Header as string
     * @throws ParseException Thrown if header date is unparsable
     * @throws InvalidHeaderException Thrown if header is not found
     */
    private String getHeader() throws ParseException, InvalidHeaderException {
        Header headerObj = idfJsonObj.getHeader();
        if (headerObj == null) {
            throw new InvalidHeaderException("", "Header property not found");
        }
        SimpleDateFormat dateSourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date timestamp = dateSourceFormat.parse(headerObj.getDate());
        String header = String.format("%s%s %s%s %s %s",
            headerObj.getSenderCountry(),
            headerObj.getSenderOrganization(),
            headerObj.getDestCountry(),
            headerObj.getDestOrg(),
            dateFormat.format(timestamp),
            headerObj.getVersion()
        );
        return header;
    }

    /**
     * Build the idf data and testData section
     * @param measures
     * @throws MissingMandatoryFieldException
     */
    private void buildData(List<Measure> measures) throws MissingMandatoryFieldException {
        StringBuilder dataBuilder = new StringBuilder();
        StringBuilder testBuilder = new StringBuilder();
        for (int i = 0; i < measures.size(); i++) {
            Measure measure = measures.get(i);

            //Get country and site id
            String localityCode = measure.getLocalityCode();
            if (localityCode == null || localityCode.length() < 7) {
                throw new MissingMandatoryFieldException("localitycode", localityCode);
            }
            String country = localityCode.substring(0, 2);
            String siteId = localityCode.substring(2);

            /* Get time and description of measurement
               As the json input does not provide a timekey, the start date is used with the "1" timekey
            */
            String tom = dateFormat.format(measure.getStartMeasure());
            String timekey = "1";
            String dom = measure.getDom().toString();

            //Get measured value
            String valueConstraint = measure.getValueConstraint() != null? measure.getValueConstraint(): "";
            Double value = measure.getMeasuredValue();
            String measureValue = valueConstraint + value.toString();
            String status = measure.getStatus() != null? measure.getStatus().toString(): "*";
            String uncertainty = measure.getUncertainty() != null? measure.getUncertainty().toString(): "*";

            //Get nuclide
            Integer nuclideCode = measure.getNuclide();
            Nuclide nuclide = nuclideCode != null? staticData.getNuclideByCode(nuclideCode): null;
            String idfNuclide = nuclide != null? nuclide.getIdfNuclideKey(): "*";

            //Skip currently unused values:
            // <minimum decision threshold (0-9)>
            // <maximum decision threshold (0-9)>
            // <comment for balancing (0-40)>
            // <time period [s] (0-8)>
            // <maximum detection limit (0-9)>
            String min = "*";
            String max = "*";
            String comment = "*";
            String period = "*";
            String maxLimit = "*";

            //Build the data line
            StringJoiner lineBuilder = new StringJoiner(" ");
            lineBuilder.add(country)
            .add(siteId)
            .add(tom)
            .add(timekey)
            .add(dom)
            .add(measureValue)
            .add(status)
            .add(uncertainty)
            .add(idfNuclide)
            .add(min)
            .add(max)
            .add(comment)
            .add(period)
            .add(maxLimit)
            .add(NEWLINE);

            if (measure.getTestData() != null && measure.getTestData() == true) {
                testBuilder.append(lineBuilder);
            } else {
                dataBuilder.append(lineBuilder);
            }
        }
        data = dataBuilder.toString();
        testData = testBuilder.toString();
    }

    private void buildSites(List<Locality> localities) {
        StringBuilder siteBuilder = new StringBuilder();

        for (int i = 0; i < localities.size(); i++) {
            StringJoiner lineBuilder = new StringJoiner(" ");
            Locality locality = localities.get(i);

            //Extract siteID
            String siteId = locality.getLocalityCode().substring(2);

            //Inputjson only contains geographical coordinates
            String geoKey = "1";
            String firstGeo = "*";
            String secondGeo = "*";
            Double latitude = locality.getLatitude();
            Double longitude = locality.getLongitude();
            if (latitude != null && longitude != null) {
                if (longitude < 0.0) {
                    firstGeo = "W";
                    longitude *= -1;
                } else {
                    firstGeo = "E";
                }
                if (latitude < 0.0) {
                    secondGeo = "S";
                    latitude *= -1;
                }
                else {
                    secondGeo = "N";
                }
                //Format coordinates
                DecimalFormat longitudeFormat = new DecimalFormat("000.00000", new DecimalFormatSymbols(Locale.US));
                DecimalFormat latitudeFormat = new DecimalFormat("00.00000", new DecimalFormatSymbols(Locale.US));
                firstGeo += longitudeFormat.format(longitude);
                secondGeo += latitudeFormat.format(latitude);
            }
            Double heightAboveSea = locality.getHeightAboveSea();
            Double heightAboveGround = locality.getHeightAboveGround();

            if (heightAboveSea != null && heightAboveGround != null) {
                heightAboveSea -= heightAboveGround;
            }
            String altitude = heightAboveSea != null? heightAboveSea.toString(): "*";
            if (heightAboveGround != null) {
                altitude += "+";
                altitude += heightAboveGround;
            }

            String locationName = locality.getLocalityName() != null?
                "\"" + locality.getLocalityName() + "\"": "*";

            lineBuilder.add(siteId);
            lineBuilder.add(geoKey);
            lineBuilder.add(firstGeo);
            lineBuilder.add(secondGeo);
            lineBuilder.add(altitude);
            lineBuilder.add(locationName);
            lineBuilder.add(NEWLINE);
            siteBuilder.append(lineBuilder);
        }

        site = siteBuilder.toString();
    }

    /**
     * Check header format.
     * @param header Header line to check
     * @throws InvalidHeaderException Thrown if header is invalid
     */
    private void checkHeader(String header) throws InvalidHeaderException {
        String[] parts = header.split(" ");
        if (parts.length < 4) {
            throw new InvalidHeaderException(header, "Missing header fields");
        }
        if (parts[0].length() != 3) {
            throw new InvalidHeaderException(parts[0], "Invalid sender/organization");
        }
        if (parts[1].length() != 3) {
            throw new InvalidHeaderException(parts[1], "Invalid destination country/organization");
        }
        if (parts[2].length() != 10) {
            throw new InvalidHeaderException(parts[2], "Invalid date/time");
        }
        if (parts[3].length() != 3) {
            throw new InvalidHeaderException(parts[3], "Invalid idf version");
        }
    }

    public class InvalidHeaderException extends Exception {
        private String header;
        private String reason;
        public InvalidHeaderException(String header, String reason) {
            this.header = header;
            this.reason = reason;
        }
        public String getHeader() {
            return header;
        }
        public String getReason() {
            return reason;
        }
    }

    public class MissingMandatoryFieldException extends Exception {
        private String fieldname;
        private String object;
        public MissingMandatoryFieldException(String fieldname, String object) {
            this.fieldname = fieldname;
            this.object = object;
        }
        public String getFieldname() {
            return fieldname;
        }
        public String getObject() {
            return object;
        }
    }
}
