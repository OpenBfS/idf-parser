package de.bfs.idfparser;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.bfs.idfparser.parser.IDFErrorListener;
import de.bfs.idfparser.parser.IDFListener;
import de.bfs.idfparser.parser.idf.IdfLexer;
import de.bfs.idfparser.parser.idf.IdfParser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * DWDToJSON transforms a DWD file format to GeoJSON
 */
public class IDFToJSON {
    /**
     * Do the transformation
     *
     * @param in InputStream of the file content
     * @return String in JSON format
     */
    public String idfToJson(InputStream in) {

        CharStream is;
        // Convert to an antlr4 conform stream
        try {
            is = CharStreams.fromStream(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Do the lexer and parser actions
        IdfLexer lexer = new IdfLexer(is);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        IdfParser parser = new IdfParser(cts);
        ParseTree tree = parser.idfdatei();
        ParseTreeWalker walker = new ParseTreeWalker();
        IDFListener listener = new IDFListener();
        ObjectMapper mapper = new ObjectMapper();
        if (listener.getErrors().size() != 0) {
            try {
                return mapper.writeValueAsString(listener.getResult());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
        IDFErrorListener errorListener = IDFErrorListener.INSTANCE;
        parser.addErrorListener(errorListener);
        walker.walk(listener, tree);

        //Get the resulting JSON object and return it as string
        ObjectNode result = listener.getResult();
        try {
            String resultString = mapper.writeValueAsString(result);
            return resultString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
