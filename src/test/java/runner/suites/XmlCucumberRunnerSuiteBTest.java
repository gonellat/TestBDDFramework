package runner.suites;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

/**
 * Cucumber Options
 */
@CucumberOptions(
      plugin = {"html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json", "json:target/cucumber-reports/cucumber.xml", "junit:target/cucumber.xml", "pretty", 
            "listener.ListenerPlugin", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"},
      features = "src/test/resources/features",
      snippets = SnippetType.UNDERSCORE,
      glue={"step_definitions"}
      ,tags="@Regression"
      ) 

/**
 * This class is the main entry point for running cucumber tests
 * 
 */
public class XmlCucumberRunnerSuiteBTest extends AbstractTestNGCucumberTests {

   @Override
   @DataProvider(parallel = false)
   public Object[][] scenarios() {
      return super.scenarios();
   }
}
