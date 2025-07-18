package step_definitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.DynamicRoutingUtil;
import utils.TestInitialization;
import utils.TestLoggerHolder;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

public class Hooks {

    /**
     * Initializes the test environment before any scenario runs.
     * Reads configs, sets up drivers, logging, context managers, etc.
     */
    @Before(order = 0)
    public void initializeScenario(Scenario scenario) {
        try {
            // 💡 This should load config, setup driver, filters, and Extent hooks
            TestInitialization.init();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize test", e);
        }

        Logger log = DynamicRoutingUtil.createLoggerForTest(scenario.getName(), "global");
        TestLoggerHolder.setLogger(log);
        TestLoggerHolder.getLogger().info("▶ Starting scenario: " + scenario.getName());
    }


    @After
    public void afterScenario(Scenario scenario) {
    	TestLoggerHolder.getLogger().info("⏹ Finished scenario: " + scenario.getName());
    }
}

