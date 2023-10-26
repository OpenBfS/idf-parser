package de.bfs.idfparser.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;


public class IDFErrorListener extends BaseErrorListener {

    public static final IDFErrorListener INSTANCE =
        new IDFErrorListener();

    private List<String> errors = new ArrayList<String>();

    @Override
    public void syntaxError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg,
        RecognitionException e
    ) {
        String sourceName = "Parser";
        if (e != null && e.getCtx() != null) {
            sourceName = e.getCtx().getText();
        }
        errors.add(sourceName);
    }

    public void reset() {
        this.errors.clear();
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
