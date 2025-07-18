package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import utils.BaseTestConfiguration;

/**
 * TestNG runner for executing Cucumber feature files.
 * <p>
 * This runner supports parallel execution and Cucumber plugin integrations (Extent, JSON, JUnit).
 */
@CucumberOptions(plugin = { "html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json", "junit:target/cucumber.xml",
      "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
      "summary" }, features = "src/test/resources/features", snippets = SnippetType.UNDERSCORE, glue = {
            "step_definitions" }, tags = "@XMLDemo")

/**
 * Executes Cucumber tests using TestNG runner.
 */
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

   /**
    * Default Constructor
    */
   public CucumberRunnerTest() {

   }

   @Override
   @DataProvider(parallel = true)
   public Object[][] scenarios() {
      int threadCount = BaseTestConfiguration.getMaxLocalInstances();
      if (threadCount < 1) {
         threadCount = 1;
      }
      System.setProperty("dataproviderthreadcount", String.valueOf(threadCount));
      return super.scenarios();
   }
}
