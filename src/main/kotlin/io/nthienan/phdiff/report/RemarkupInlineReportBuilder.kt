package io.nthienan.phdiff.report

import org.sonar.api.scanner.ScannerSide
import org.sonar.api.batch.postjob.issue.PostJobIssue

/**
 * Created on 20-Jul-17.
 * @author nthienan
 */
@ScannerSide
class RemarkupInlineReportBuilder(val remarkupUtils: RemarkupUtils) : InlineReportBuilder {

  private var issue: PostJobIssue? = null

  override fun issue(issue: PostJobIssue?): InlineReportBuilder {
    issue?.let { this.issue = issue }
    return this
  }

  override fun build(): String {
    var str = ""
    issue?.let { issue ->
      str = StringBuilder().append(remarkupUtils.icon(issue.severity()))
        .append(" ").append(remarkupUtils.message(issue.message()))
        .append(" ").append(remarkupUtils.rule(issue.ruleKey().toString()))
        .toString()
    }
    return str

  }
}
