package de.bfs.idfparser.parser;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.bfs.idfparser.Data;
import de.bfs.idfparser.parser.KdaUtil.Result;
import de.bfs.idfparser.parser.idf.IdfBaseListener;
import de.bfs.idfparser.parser.idf.IdfParser;
import de.bfs.idfparser.parser.idf.IdfParser.CommentContext;
import de.bfs.idfparser.parser.idf.IdfParser.Data_lineContext;
import de.bfs.idfparser.parser.idf.IdfParser.Idf_headerContext;
import de.bfs.idfparser.parser.idf.IdfParser.Location_lineContext;
import de.bfs.idfparser.parser.idf.IdfParser.Measure_lineContext;
import de.bfs.idfparser.model.Dom;
import de.bfs.idfparser.model.Locality;
import de.bfs.idfparser.model.Measure;
import de.bfs.idfparser.model.Nuclide;
import de.bfs.idfparser.parser.idf.IdfBaseListener;
import de.bfs.idfparser.parser.idf.IdfParser;
import de.bfs.idfparser.parser.idf.IdfParser.CommentContext;
import de.bfs.idfparser.parser.idf.IdfParser.Data_lineContext;
import de.bfs.idfparser.parser.idf.IdfParser.Idf_headerContext;
import de.bfs.idfparser.parser.idf.IdfParser.Location_lineContext;
import de.bfs.idfparser.parser.idf.IdfParser.Measure_lineContext;

public class IDFListener extends IdfBaseListener {

    // Error and warning objects
    ObjectNode errors;
    ObjectNode warnings;

    // The resulting data objects
    ArrayList<Locality> localities;
    ArrayList<Measure> measures;

    Boolean attnData;

    Boolean testData;

    // Json object factory
    ObjectMapper mapper;

    // The resulting JSON Object
    private ObjectNode result;

    // Parsed header data
    String headerSenderCountry = "";
    String headerSenderOrg = "";
    String headerDestintaionCountry = "";
    String headerDestinationOrg = "";
    Date headerTimestamp = new Timestamp(new Date().getTime());
    String headerVersion;

    //Date format used in json
    DateFormat dateFormat;

    private enum State {HEADER, DATA, SITE, TEXT, NONE};
    private State currentState;

    private Locality lastLocality;
    private Measure lastMeasure;

    private List<String> text;

    // Static data loaded from resources
    Data data;

    // Bundle to translate codes to human readable strings.
    ResourceBundle i18n;

    public IDFListener() {
        currentState = State.NONE;
        Locale de = Locale.GERMAN;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        this.text = new ArrayList<String>();
        this.mapper = new ObjectMapper();
        this.errors = mapper.createObjectNode();
        this.warnings = mapper.createObjectNode();
        this.result = mapper.createObjectNode();
        this.result.set("errors", errors);
        this.result.set("warnings", warnings);
        i18n = ResourceBundle.getBundle("import", de);
        try {
            data = Data.getInstance();
        } catch (JsonParseException e) {
            this.errors.put("data", i18n.getString("600"));
            return;
        } catch (JsonMappingException e) {
            this.errors.put("data", i18n.getString("600"));
            return;
        } catch (IOException e) {
            this.errors.put("data", i18n.getString("600"));
            return;
        }
        measures = new ArrayList<Measure>();
        localities = new ArrayList<Locality>();
    }

    public ObjectNode getResult() {
        ObjectMapper mapper = new ObjectMapper();
        //Overwrite timezone to UTC
        mapper.setDateFormat(dateFormat);
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        ArrayNode m = mapper.convertValue(measures, ArrayNode.class);
        ArrayNode l = mapper.convertValue(localities, ArrayNode.class);
        ArrayNode t = mapper.convertValue(text, ArrayNode.class);
        this.result.set("site", l);
        this.result.set("data", m);
        this.result.set("text", t);
        return this.result;
    }

    /**
     * @return the errors
     */
    public ObjectNode getErrors() {
        return errors;
    }

    /**
     * @return the warnings
     */
    public ObjectNode getWarnings() {
        return warnings;
    }

    private void addError(String key, Integer line, String value, String  cause) {
        if (this.errors.get(key) == null) {
            ArrayNode an = this.mapper.createArrayNode();
            this.errors.set(key, an);
        }
        ObjectNode err = mapper.createObjectNode();
        err.put("line", line);
        err.put("value", value);
        err.put("cause", cause);
        ((ArrayNode)this.errors.get(key)).add(err);
    }

    private void addWarning(String key, Integer line, String value, String  cause) {
        if (this.warnings.get(key) == null) {
            ArrayNode an = this.mapper.createArrayNode();
            this.warnings.set(key, an);
        }
        ObjectNode warn = mapper.createObjectNode();
        warn.put("line", line);
        warn.put("value", value);
        warn.put("cause", cause);
        ((ArrayNode)this.warnings.get(key)).add(warn);
    }

    @Override
    public void enterIdf_header(Idf_headerContext ctx) {
        currentState = State.HEADER;
        if (ctx.getChildCount() < 5) {
            this.addError("header", ctx.getStart().getLine(), "count", i18n.getString("673"));
            return;
        }
        String senderOrg = ctx.getChild(0).toString();
        String destinationOrg = ctx.getChild(1).toString();
        String dateTime = ctx.getChild(2).toString();
        headerVersion = ctx.getChild(3).toString();

        // Sender informations are used as fallbacks.
        if (!senderOrg.matches(DataTypes.SC3)) {
            this.addWarning("header", ctx.getStart().getLine(), senderOrg, i18n.getString("675"));
        } else {
            headerSenderCountry = senderOrg.substring(0, 2);
            headerSenderOrg = senderOrg.substring(senderOrg.length() - 1);
        }
        if (!destinationOrg.matches(DataTypes.SC3)) {
            this.addWarning("header", ctx.getStart().getLine(), destinationOrg, i18n.getString("675"));
        } else {
            headerDestintaionCountry = destinationOrg.substring(0, 2);
            headerDestinationOrg = destinationOrg.substring(destinationOrg.length() - 1);
        }
        // Date and time are fallbacks, warnings about format are tracked.
        if (!dateTime.matches(DataTypes.SI10) && !dateTime.matches(DataTypes.SI12)) {
            this.addWarning("header", ctx.getStart().getLine(), dateTime, i18n.getString("675"));
        }
        else {
            // TODO: IDF-standard does not support seconds yet
            SimpleDateFormat formatter = dateTime.matches(DataTypes.SI12) ? new SimpleDateFormat("yyMMddhhmmss") : new SimpleDateFormat("yyMMddhhmm");
            Date ts;
            try {
                ts = formatter.parse(dateTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(ts);
                headerTimestamp = new Timestamp(cal.getTime().getTime());
            } catch (ParseException e) {
                this.addWarning("header", ctx.getStart().getLine(), dateTime, i18n.getString("670"));
            }
        }
        // Version is ignored, warnings about format are tracked.
        if (!headerVersion.matches(DataTypes.SI3)) {
            this.addWarning("header", ctx.getStart().getLine(), headerVersion, i18n.getString("670"));
        }

        //Build header version
        ObjectNode header = mapper.createObjectNode();
        header.put("sender_country", headerSenderCountry);
        header.put("sender_organization", headerSenderOrg);
        header.put("dest_country", headerDestintaionCountry);
        header.put("dest_org", headerDestinationOrg);
        header.put("date", dateFormat.format(headerTimestamp));
        header.put("idf_version", headerVersion);
        result.set("header", header);
    }

    @Override
    public void exitIdf_header(Idf_headerContext ctx) {
        currentState = State.NONE;
    }

    @Override
    public void enterData_line(Data_lineContext ctx) {
        //Check for block prefixes
        this.attnData = ctx.getChild(0).getText().equals("ATTN");
        this.testData = ctx.getChild(0).getText().equals("TEST");
    }

    @Override
    public void enterMeasure_line(Measure_lineContext ctx) {
        // Last item is the newline token. -> Remove it.
        ctx.removeLastChild();

        //Count child elements
        int count = ctx.getChildCount();

        // If second last item is a comment -> Ignore it
        if (ctx.getChild(ctx.getChildCount() - 1).getClass() == CommentContext.class) {
            count--;
        }
        // If there are less than 6 mandatory items set an error
        if (count < 6) {
            this.addError("measure", ctx.getStart().getLine(), "count", i18n.getString("673"));
            return;
        }
        Measure m = new Measure();
        m.setAttnData(attnData);
        m.setTestData(testData);
        String country = ctx.getChild(0).toString();
        String site = ctx.getChild(1).toString();
        String measureTime = ctx.getChild(2).toString();
        String timeKey = ctx.getChild(3).toString();
        String measureDescr = ctx.getChild(4).toString();
        String measureValue = ctx.getChild(5).toString();

        String localityCountry = "";
        // We skip doms which are not present in dom.json as time calc depends on it (IDF-standard 2.3.14. Time period, delta t is not implemented)
        if (data.getDomByCode(Integer.valueOf(measureDescr)) == null) {
            this.addError("measure", ctx.getStart().getLine(), "dom: " + measureDescr, i18n.getString("673"));
            return;
        }
        // Wrong format for country leads to warning. Sender country from header will be
        // taken.
        if (!country.matches(DataTypes.SC2)) {
            this.addError("measure", ctx.getStart().getLine(), country, i18n.getString("675"));
            localityCountry = headerSenderCountry;
        } else {
            localityCountry = country;
        }
        // TODO: change in standard needed (4 bytes in length at least, max. 5 bytes?!)
        if (!site.matches(DataTypes.SC4) && !site.matches(DataTypes.SC5)) {
            this.addWarning("measure", ctx.getStart().getLine(), country, i18n.getString("675"));
            return;
        }
        // localittyCode is the concatenation of country and site
        m.setLocalityCode(localityCountry + site);

        if (!measureTime.matches(DataTypes.DT10) && !measureTime.matches(DataTypes.DT12)) {
            this.addWarning("measure", ctx.getStart().getLine(), measureTime, i18n.getString("675"));
        }
        if (!timeKey.matches(DataTypes.C4)) {
            this.addWarning("measure", ctx.getStart().getLine(), timeKey, i18n.getString("675"));
        }
        if (!measureDescr.matches(DataTypes.SI3)) {
            this.addWarning("measure", ctx.getStart().getLine(), measureDescr, i18n.getString("675"));
        }
        else {
          m.setDom(Integer.parseInt(measureDescr));
        }
        // Start and end date/time of a measurement are calculated from
        // measureTime, timeKey and dom.
        if ((measureTime.matches(DataTypes.DT10) || measureTime.matches(DataTypes.DT12)) && timeKey.matches(DataTypes.C4) && measureDescr.matches(DataTypes.SI3)) {
            setDates(m, timeKey, measureTime, measureDescr);
        }
        if (data.getDomByCode(Integer.valueOf(measureDescr)).getDuration() == null) {
            this.addWarning("measure", ctx.getStart().getLine(), "duration of dom " + measureDescr, i18n.getString("673"));
        }
        if (!measureValue.matches(DataTypes.C9)) { // measured_value
            this.addWarning("measure", ctx.getStart().getLine(), measureValue, i18n.getString("675"));
            m.setValueConstraint(getValueConstraint(measureValue));
            m.setMeasuredValue(getMeasuredValue(measureValue));
        } else {
            m.setValueConstraint(getValueConstraint(measureValue));
            m.setMeasuredValue(getMeasuredValue(measureValue));
        }
        if (count >= 7) {
            String status = ctx.getChild(6).toString();
            /*
            // If the status is not wildcard and not matching the datatype, track a
            // warning, else set the current status.
            if (!"*".equals(status) && !status.matches(DataTypes.I3)) {
                this.addWarning("measure", ctx.getStart().getLine(), status, i18n.getString("675"));
            } else {
                m.setStatus(status);
            }
            */
            m.setStatus(Integer.parseInt(status));
        }
        if (count >= 8) {
            String measureError = ctx.getChild(7).toString();
            // If the measured error is not wildcard and not matches the datatype,
            // track a warning, else set the value as uncertainty
            if (!"*".equals(measureError) && !measureError.matches(DataTypes.C9)) {
                this.addWarning("measure", ctx.getStart().getLine(), measureError, i18n.getString("675"));
            } else if (!"*".equals(measureError)) {
                if (measureError.endsWith("%")) {
                    m.setUncertainty(Double.valueOf(measureError.substring(0,measureError.length() - 1)));
                }
                else {
                    m.setUncertainty(Double.valueOf(measureError));
                }
            }
        }
        if (count >= 9) {
            String nuclide = ctx.getChild(8).toString();
            // If the nuclide is not wildcard and not matches the datatype, track
            // a warning, else set the nuclide.
            if (!"*".equals(nuclide) && !nuclide.matches(DataTypes.C6)) {
                this.addWarning("measure", ctx.getStart().getLine(), nuclide, i18n.getString("675"));
            } else if (!"*".equals(nuclide)) {
                Nuclide n = data.getNuclideByIdfKey(nuclide);
                if (n == null) {
                    this.addError("measure", ctx.getStart().getLine(), nuclide, i18n.getString("675"));
                } else {
                    m.setNuclide(n.getCode());
                }
            }
        }

        // If nuclide is missing in DATA block, set it via dom.
        if (m.getNuclide() == null) {
            m.setNuclide(data.getDomByCode(m.getDom()).getIdfNuclide());
        }

        if (count >= 10) {
            String min = ctx.getChild(9).toString();
            // Currently not used. Format warnings are tracked.
            if (!min.matches(DataTypes.C9)) {
                this.addWarning("measure", ctx.getStart().getLine(), min, i18n.getString("675"));
            }
        }
        if (count >= 11) {
            String max = ctx.getChild(10).toString();
            // Currently not used. Format warnings are tracked.
            if (!max.matches(DataTypes.C9)) {
                this.addWarning("measure", ctx.getStart().getLine(), max, i18n.getString("675"));
            }
        }
        if (count >= 12) {
            String comment = ctx.getChild(11).toString();
            // Currently not used. Format warnings are tracked.
            if (!comment.matches(DataTypes.C9)) {
                this.addWarning("measure", ctx.getStart().getLine(), comment, i18n.getString("675"));
            }
        }
        if (count >= 13) {
            String timePeriod = ctx.getChild(12).toString();
            // Currently not used. Format warnings are tracked.
            if (!timePeriod.matches(DataTypes.C8)) {
                this.addWarning("measure", ctx.getStart().getLine(), timePeriod, i18n.getString("675"));
            }
        }
        if (count >= 14) {
            String maxLimit = ctx.getChild(13).toString(); // detection_limit
            // Currently not used. Format warnings are tracked.
            if (!maxLimit.matches(DataTypes.C9)) {
                this.addWarning("measure", ctx.getStart().getLine(), maxLimit, i18n.getString("675"));
            }
        }
        m.setTimeStamp(headerTimestamp);
        measures.add(m);
        lastMeasure = m;
    }

    @Override
    public void enterSite_line(IdfParser.Site_lineContext ctx) {
        this.attnData = ctx.getChild(0).getText().equals("ATTN");
        this.testData = ctx.getChild(0).getText().equals("TEST");
    }

    @Override
    public void enterLocation_line(Location_lineContext ctx) {
        // Last item is the newline token. -> Remove it.
        ctx.removeLastChild();
        int count = ctx.getChildCount();
        // If second last item is a comment -> Ignore it
        if (ctx.getChild(ctx.getChildCount() - 1).getClass() == CommentContext.class) {
            count--;
        }
        // If there are less than 2 mandatory items set an error
        if (count < 2) {
            this.addError("site", ctx.getStart().getLine(), "count for item " + ctx.getStart().getText(), i18n.getString("670"));
            return;
        }
        Locality l = new Locality();
        l.setAttnData(attnData);
        l.setTestData(testData);
        String site = ctx.getChild(0).toString();
        String geoKey = ctx.getChild(1).toString();
        l.setGeoKey(geoKey);

        boolean hasFirstCoordinate = false;
        boolean hasSecondCoordinate = false;
        // Set locality code as country + site
        if (!site.matches(DataTypes.SC5)) {
            this.addWarning("site", ctx.getStart().getLine(), site, i18n.getString("675"));
        } else {
            l.setLocalityCode(headerSenderCountry + site);
        }
        if (!geoKey.matches(DataTypes.C2)) {
            this.addWarning("site", ctx.getStart().getLine(), geoKey, i18n.getString("675"));
        }
        String firstGeo = "";
        if (count >= 3) {
            firstGeo = ctx.getChild(2).toString();
            if (!firstGeo.matches(DataTypes.C15)) {
                this.addWarning("site", ctx.getStart().getLine(), firstGeo, i18n.getString("675"));
            } else {
              hasFirstCoordinate = true;
            }
        }
        String secondGeo = "";
        if (count >= 4) {
            secondGeo = ctx.getChild(3).toString();
            if (!secondGeo.matches(DataTypes.C10)) {
                this.addWarning("site", ctx.getStart().getLine(), secondGeo, i18n.getString("675"));
            }
            else {
              hasSecondCoordinate = true;
            }
        }
        if (geoKey.matches(DataTypes.C2) && !"99".equals(geoKey)&&
            hasFirstCoordinate && hasSecondCoordinate
        ) {
            try {
                transformCoordinates(l, geoKey, firstGeo, secondGeo);
            } catch (NumberFormatException e) {
                this.addError("site", ctx.getStart().getLine(), "coordinates", i18n.getString("675"));
                return;
            }

        }
        if (count >= 5) {
            String altitude = ctx.getChild(4).toString();
            if (!altitude.matches(DataTypes.C9)) {
                this.addWarning("site", ctx.getStart().getLine(), altitude, i18n.getString("675"));
            } else {
                l.setHeightAboveSea(transformAltitudeAboveSea(altitude));
                l.setHeightAboveGround(transformAltitudeAboveGround(altitude));
            }
        }
        /* location name is ignored and tracks no warning since it is not parsable
        * without quotation*/
        if (count >= 6) {
            String locationName = ctx.getChild(5).toString();
            if (!locationName.matches(DataTypes.C_STAR)) {
                this.addWarning("site", ctx.getStart().getLine(), locationName, i18n.getString("675"));
            }
            locationName= locationName.replaceAll("\"", "").trim();
            l.setLocalityName(locationName);
        }
        /* Country is currently ignored since it is not decidable without
        * quotation whether country follows or not.
        if (count >= 7) {
            String country = ctx.getChild(6).toString();
            if (!country.matches(DataTypes.C2)) {
                ReportItem warn = new ReportItem();
                warn.setKey("Line " + Integer.toString(ctx.getStart().getLine()));
                warn.setValue("Country: " + country);
                warn.setCode(632);
                parserWarnings.add(warn);
            }
        }
        */
        l.setTimeStamp(headerTimestamp);
        localities.add(l);
        lastLocality = l;
    }


    @Override
    public void enterComment(IdfParser.CommentContext ctx) {
        String comment = "";
        for (int i = 1; i < ctx.getChildCount(); i++) {
            if (i > 1) {
                comment += " ";
            }
            comment += ctx.getChild(i).getText();
        }
        switch(currentState) {
            case DATA:
                lastMeasure.setComment(comment);
                break;
            case SITE:
                lastLocality.setComment(comment);
                break;
            default: return;
        }
    }

    @Override
    public void enterText_block(IdfParser.Text_blockContext ctx) {
        this.currentState = State.TEXT;
        StringBuilder blockBuilder = new StringBuilder();
        //Build text block string without first two childs ("TEXT\n")
        for (int i = 2; i < ctx.getChildCount() - 1; i++) {
            String item = ctx.getChild(i).getText();
            //Add whitespace if needed needed
            if (i != 2 && !item.equals("\n")) {
                blockBuilder.append(" ");
            }
            blockBuilder.append(item);
        }
        this.text.add(blockBuilder.toString());
    }

    @Override
    public void exitText_block(IdfParser.Text_blockContext ctx) {
        this.currentState = State.NONE;
    }

    @Override
    public void enterMeasure_block(IdfParser.Measure_blockContext ctx) {
        currentState = State.DATA;
    }

    @Override
    public void exitMeasure_block(IdfParser.Measure_blockContext ctx) {
        currentState = State.NONE;
        lastMeasure = null;
    }

    @Override
    public void enterLocation_block(IdfParser.Location_blockContext ctx) {
        currentState = State.SITE;
    }

    @Override
    public void exitLocation_block(IdfParser.Location_blockContext ctx) {
        currentState = State.NONE;
        lastLocality = null;
    }


    public List<Measure> getMeasures() {
        return measures;
    }

    public List<Locality> getLocalities() {
        return localities;
    }

    private TimeZone getTimeZone(String timeKey) {
        String tz = timeKey.substring(1);
        if (tz.startsWith("+") || tz.startsWith("-")) {
            if ("+0".equals(tz) || "-0".equals(tz)) {
                return TimeZone.getTimeZone("UTC");
            }
            else if ("+1".equals(tz)) {
                return TimeZone.getTimeZone("GMT+1");
            }
            else if ("+2".equals(tz)) {
                return TimeZone.getTimeZone("EET");
            }
            else {
                return TimeZone.getTimeZone("GMT" + tz);
            }
        }
        if ("".equals(tz)) {
            return TimeZone.getTimeZone("UTC");
        }
        else {
            return TimeZone.getTimeZone(tz);
        }
    }

    private void setDates(Measure m, String timeKey, String time, String dom) {
        // TODO calculate start/end date from time key, dom and time of measurement
        Dom domItem = data.getDomByCode(Integer.valueOf(dom));
        Integer period = Integer.valueOf(timeKey.substring(0, 1));
        TimeZone timezone = getTimeZone(timeKey);
        SimpleDateFormat formatter = time.matches(DataTypes.SI12) ? new SimpleDateFormat("yyMMddHHmmssz") : new SimpleDateFormat("yyMMddHHmmz");

        if (period == 1) {
            Date start;
            try {
                start = formatter.parse(time + timezone.getID());
            } catch (ParseException e) {
                this.addWarning("measure", -1, time + timezone.getID(), i18n.getString("675"));
                return;
            }
            if (domItem.getDurationUnit() != null && domItem.getDuration() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                cal.setTime(start);
                cal.add(getDateUnit(domItem.getDurationUnit()), domItem.getDuration());
                m.setEndMeasure(cal.getTime());
            }
            m.setStartMeasure(start);
        } 
        else if (period == 2) {
            Date middle;
            try {
                middle = formatter.parse(time + timezone.getID());
            } catch (ParseException e) {
                this.addWarning("measure", -1, time + timezone.getID(), i18n.getString("675"));
                return;
            }
            Calendar start = Calendar.getInstance();
            if (domItem.getDurationUnit() != null && domItem.getDuration() != null) {
                int dateUnit = getDateUnit(domItem.getDurationUnit());
                int duration = domItem.getDuration();
                //If duration is one, convert unit to prevent flooring to 0
                if (duration == 1) {
                    switch (dateUnit) {
                        case Calendar.DAY_OF_MONTH:
                            dateUnit = Calendar.HOUR_OF_DAY;
                            duration *= 24;
                            break;
                        case Calendar.HOUR_OF_DAY:
                            dateUnit = Calendar.MINUTE;
                            duration *= 60;
                            break;
                    }
                }
                start.setTime(middle);
                start.add(dateUnit, -1*duration/2);
                Calendar end = Calendar.getInstance();
                end.setTime(middle);
                end.add(dateUnit, duration/2);
                m.setStartMeasure(start.getTime());
                m.setEndMeasure(end.getTime());
            }
        }
        else if (period == 3) {
            Date end;
            try {
                end = formatter.parse(time + timezone.getID());
            } catch (ParseException e) {
                this.addWarning("measure", -1, time + timezone.getID(), i18n.getString("675"));
                return;
            }
            Calendar start = Calendar.getInstance();
            start.setTime(end);
            if (domItem.getDurationUnit() != null && domItem.getDuration() != null) {
                start.add(getDateUnit(domItem.getDurationUnit()), -1*domItem.getDuration());
                m.setStartMeasure(start.getTime());
            }
            m.setEndMeasure(end);
        }
        return;
    }

    private int getDateUnit(int domUnit) {
        switch (domUnit) {
            case 13:
                return Calendar.SECOND;
            case 14:
                return Calendar.MINUTE;
            case 15:
                return Calendar.HOUR_OF_DAY;
            case 16:
                return Calendar.DAY_OF_MONTH;
            case 17:
                return Calendar.WEEK_OF_MONTH;
            case 18:
                return Calendar.MONTH;
            case 19:
                return Calendar.YEAR;
            default:
                return -1;
        }
    }

    private String getValueConstraint(String value) {
        if (value.startsWith("<")) {
            return "<";
        }
        return "";
    }

    private Double getMeasuredValue(String value) {
        String measure = value;
        if (value.startsWith("<")) {
            measure = value.substring(1, value.length());
        }
        return Double.valueOf(measure);
    }

    private void transformCoordinates(Locality l, String geoKey, String first, String second) {
        Integer kda = Integer.parseInt(geoKey);
        KdaUtil transform = new KdaUtil();
        Result n = transform.transform(kda, KdaUtil.KDA_GD, first, second);
        if (n == null) {
            this.addWarning("site", -1, "transform", i18n.getString("675"));
            return;
        }
        l.setLatitude(Double.parseDouble(n.getY()));
        l.setLongitude(Double.parseDouble(n.getX()));

    }

    private Double transformAltitudeAboveSea(String altitude) {
        if ("*".equals(altitude)) {
            this.addWarning("site", -1, "altitude", i18n.getString("675"));
            return null;
        }
        if (altitude.indexOf("+") > 0) {
            String[] parts = altitude.split("\\+");
            if ("*".equals(parts[0]) || "*".equals(parts[1])) {
                this.addWarning("site", -1, "altitude", i18n.getString("675"));
                return null;
            }
            parts[1] = parts[1].replace(",", ".");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        }
        return Double.parseDouble(altitude);
    }

    private Double transformAltitudeAboveGround(String altitude) {
        Double heightAboveGround = null;
        if (altitude.contains("+")) {
            String[] parts = altitude.split("\\+");
            String hagString = parts[1].replace(",", ".");
            heightAboveGround = Double.parseDouble(hagString);
        }
        return heightAboveGround;
    }
}
