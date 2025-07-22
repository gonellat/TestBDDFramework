package step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.DriverManager;
import utils.DynamicRoutingUtil;
import utils.TestInitialization;
import utils.TestLoggerHolder;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;

/**
 * Cucumber Hooks for scenario setup and teardown.
 */
public class Hooks {

   /**
    * Default Constructor
    */
   public Hooks() {

   }

// Ensure global init happens only once per test run
   private static boolean isInitialized = false;

   /**
    * Hook that runs before each scenario to configure logging and context.
    * Global test setup (drivers, config, extent) is initialized only once.
    *
    * @param scenario the currently running Cucumber scenario
    */
   @Before(order = 0)
   public void beforeEachScenario(Scenario scenario) {
       if (!isInitialized) {
           try {
               TestInitialization.init(); // Global init
               isInitialized = true;
           } catch (IOException e) {
               throw new RuntimeException("❌ Failed to initialize global test config", e);
           }
       }

       // Per-scenario log routing
       Logger log = DynamicRoutingUtil.createLoggerForTest(scenario.getName(), "global");
       TestLoggerHolder.setLogger(log);
       TestLoggerHolder.getLogger().info("▶ Starting scenario: {}", scenario.getName());
   }

   /**
    * Hook that runs after each scenario.
    * <p>
    * Captures logs, attaches reports, and performs cleanup.
    *
    * @param scenario the completed scenario
    */
   @After
   public void afterScenario(Scenario scenario) {
       TestLoggerHolder.getLogger().info("{} {}", "⏹ Finished scenario: ", scenario.getName());
       List<String> tags = scenario.getSourceTagNames()
                                   .stream()
                                   .map(String::toLowerCase)
                                   .toList();

       if (tags.contains("@web") && DriverManager.getCurrentDriver() != null) {
           try {
               DriverManager.closeDriver();
           } catch (Exception e) {
               TestLoggerHolder.getLogger().error("❌ Error closing driver in @After hook: " + e.getMessage());
           }
       }
   }
}
