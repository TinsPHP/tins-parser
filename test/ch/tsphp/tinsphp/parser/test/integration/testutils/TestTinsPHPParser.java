/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class TestTSPHPParser from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.test.integration.testutils;

import ch.tsphp.tinsphp.parser.antlrmod.ErrorReportingTinsPHPParser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Overwrite reportError methods in order that unit tests with parser errors fails at the end.
 */
public class TestTinsPHPParser extends ErrorReportingTinsPHPParser
{

    private boolean isErrorReportingOn = true;
    protected final List<Exception> exceptions = new ArrayList<>();

    public TestTinsPHPParser(TokenStream input) {
        super(input);
    }

    public void setErrorReporting(boolean isOn) {
        isErrorReportingOn = isOn;
    }

    @Override
    public void reportError(RecognitionException e) {
        super.reportError(e);
        exceptions.add(e);
        if (isErrorReportingOn) {
            String message = e.getMessage();
            if (message != null) {
                System.out.println(message);
            } else {
                System.out.println(e.token.toString());
            }
        }
    }

    public void setBacktracking(int backtracking) {
        state.backtracking = backtracking;
    }

    public RecognizerSharedState getState() {
        return state;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
