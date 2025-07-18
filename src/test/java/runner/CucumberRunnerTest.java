package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import utils.BaseTestConfiguration;

/**
 * Cucumber Options
 */
@CucumberOptions(
      plugin = {"html:target/cucumber-html-report", "json:target/cucumber-reports/cucumber.json", "junit:target/cucumber.xml", "pretty", 
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "summary"},
      features = "src/test/resources/features",
      snippets = SnippetType.UNDERSCORE,
      glue={"step_definitions"}
      ,tags="@XMLDemo"
      ) 

/**
 * This class is the main entry point for running cucumber tests. 
 * Note for Surefire to automatically run this - the file name must end in Test.
 * 
 */
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

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
