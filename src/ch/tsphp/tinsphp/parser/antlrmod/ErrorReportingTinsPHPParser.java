/*
 * This file is part of the TinsPHP project published under the Apache License 2.0
 * For the full copyright and license information, please have a look at LICENSE in the
 * root folder or visit the project's website http://tsphp.ch/wiki/display/TINS/License
 */

/*
 * This class is based on the class ErrorReportingTSPHPParser from the TSPHP project.
 * TSPHP is also published under the Apache License 2.0
 * For more information see http://tsphp.ch/wiki/display/TSPHP/License
 */

package ch.tsphp.tinsphp.parser.antlrmod;

import ch.tsphp.tinsphp.common.issues.EIssueSeverity;
import ch.tsphp.tinsphp.common.issues.IIssueLogger;
import ch.tsphp.tinsphp.common.issues.IIssueReporter;
import ch.tsphp.tinsphp.common.issues.IssueReporterHelper;
import ch.tsphp.tinsphp.parser.antlr.TinsPHPParser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.EnumSet;

/**
 * Extends the generated TinsPHPParser by implementing IErrorReporter.
 */
public class ErrorReportingTinsPHPParser extends TinsPHPParser implements IIssueReporter
{

    private final Collection<IIssueLogger> issueLoggers = new ArrayDeque<>();
    private boolean hasFoundFatalError = false;

    public ErrorReportingTinsPHPParser(TokenStream input) {
        super(input);
    }

    @Override
    public void reportError(RecognitionException exception) {
        hasFoundFatalError = true;
        IssueReporterHelper.reportIssue(issueLoggers, exception, "parsing");
    }

    @Override
    public boolean hasFound(EnumSet<EIssueSeverity> severity) {
        return hasFoundFatalError && severity.contains(EIssueSeverity.FatalError);
    }

    @Override
    public void registerIssueLogger(IIssueLogger issueLogger) {
        issueLoggers.add(issueLogger);
    }

}
