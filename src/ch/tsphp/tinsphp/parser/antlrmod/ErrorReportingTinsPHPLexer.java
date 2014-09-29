/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ErrorReportingTSPHPLexer from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.antlrmod;

import ch.tsphp.common.ErrorReporterHelper;
import ch.tsphp.common.IErrorLogger;
import ch.tsphp.common.IErrorReporter;
import ch.tsphp.tinsphp.parser.antlr.TinsPHPLexer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * Extends the generated TinsPHPLexer by implementing IErrorReporter.
 */
public class ErrorReportingTinsPHPLexer extends TinsPHPLexer implements IErrorReporter
{

    private final Collection<IErrorLogger> errorLoggers = new ArrayDeque<>();
    private boolean hasFoundError = false;

    public ErrorReportingTinsPHPLexer() {
    }

    public ErrorReportingTinsPHPLexer(CharStream input) {
        super(input);
    }

    public ErrorReportingTinsPHPLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }

    @Override
    public boolean hasFoundError() {
        return hasFoundError;
    }

    @Override
    public void reportError(RecognitionException exception) {
        hasFoundError = true;
        ErrorReporterHelper.reportError(errorLoggers, exception, "parsing");
    }

    @Override
    public void registerErrorLogger(IErrorLogger errorLogger) {
        errorLoggers.add(errorLogger);
    }

    @Override
    public void reset() {
        hasFoundError = false;
    }
}
