package listener;

import java.io.IOException;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunStarted;
import utils.TestInitialization;

/**
 * Registers project-level initialization with the Cucumber event system.
 */
public class TestProjectListenerPlugin implements EventListener, io.cucumber.plugin.Plugin {

   /**
    * Default Constructor
    */
   public TestProjectListenerPlugin() {

   }

   @Override
   public void setEventPublisher(EventPublisher publisher) {
      publisher.registerHandlerFor(TestRunStarted.class, event -> {
         // 🔧 Init test-specific setup like driver config, API filters etc.
         try {
            TestInitialization.init();
         } catch (IOException e) {
            e.printStackTrace();
         }
      });
   }
}
