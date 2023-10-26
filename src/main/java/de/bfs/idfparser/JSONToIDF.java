package de.bfs.idfparser;

import java.io.InputStream;
import java.text.ParseException;

import de.bfs.idfparser.parser.IdfJsonParser;
import de.bfs.idfparser.parser.IdfJsonParser.InvalidHeaderException;
import de.bfs.idfparser.parser.IdfJsonParser.MissingMandatoryFieldException;

/**
 * Converter class to convert JSON to IDF
 * @author Alexander Woestmann <awoestmann@intevation.de>
 */
public class JSONToIDF {

    /**
     * Converts the given json input to idf.
     * @param jsonStream Input json as Stream
     * @return Result as string
     */
    public String jsonToIdf(InputStream jsonStream) {
        String result;
        try {
            IdfJsonParser parser = new IdfJsonParser();
            result = parser.convert(jsonStream);
        } catch (InvalidHeaderException ihe) {
            result = "Could not convert JSON: Header is invalid.";
            result += String.format("Header: %s, reason: %s", ihe.getHeader(), ihe.getReason());
        } catch (MissingMandatoryFieldException mmfe) {
            result = "Could not convert JSON: Missing mandatory field";
            result += String.format("Field: %s, Object: %s", mmfe.getFieldname(), mmfe.getObject());
        } catch (ParseException e) {
            result = "Unparsable header date";
        }
        return result;
    }

    /**
     * Converts the given json input to idf using the given header.
     * @param jsonStream Input json as Stream
     * @param header Header to use
     * @return Result as string
     */
    public String jsonToIdf(InputStream jsonStream, String header) {
        String result;
        try {
            IdfJsonParser parser = new IdfJsonParser();
            result = parser.convert(jsonStream, header);
        } catch (InvalidHeaderException ihe) {
            result = "Could not convert JSON: Header is invalid.";
            result += String.format("Header: %s, reason: %s", ihe.getHeader(), ihe.getReason());
        } catch (MissingMandatoryFieldException mmfe) {
            result = "Could not convert JSON: Missing mandatory field";
            result += String.format("Field: %s, Object: %s", mmfe.getFieldname(), mmfe.getObject());
        }
        return result;
    }
}
