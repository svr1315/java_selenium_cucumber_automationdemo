package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/", glue={"stepdefinitions"}, 
tags={"@functionaltest"},
monochrome=true,
dryRun=false,
strict=true,
plugin={"pretty", "junit:target/JunitReports/report.xml",
		"json:target/JSONReports/report.json",
		"html:target/HTMLReports"})
public class TestRunner {

}
