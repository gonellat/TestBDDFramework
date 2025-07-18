package utils;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;

/**
 * This class is used at the start of a test run to get any required web driver, set up the rest assured filters, and
 * initialise the reports
 */
public class TestInitialization {

   private static boolean isInit;
   static {
      isInit = false;
   }

   private TestInitialization() {
   }

   /**
    * This is the first method run. It is called by the test listener and sets up the configuration for the test run. It
    * also sets the rest-assured run filters for logging
    * 
    * @throws IOException General IO Exception
 * @throws InterruptedException 
    */
   public static synchronized void init() throws IOException {
      if (!isInit) {
         String userDir = System.getProperty("user.dir");

         System.setProperty("TestCaseName", "Default");

         BaseTestConfiguration.readRunProperties(Paths.get(userDir, "run.properties"));
         TestConfiguration.readConfig(BaseTestConfiguration.getEnv());

         TestReport.init();
         TestReport testReport = new TestReport();

         // Depending on the browser specified set the driverpath
         DriverManager.setOrDownloadDriver(BaseTestConfiguration.getBrowser());

         RestAssured.filters((request, response, filterContext) -> {
            // Request logging
            // The HTML logging can in places encode the URI string the way it wants to instead of the correct format,
            // the String.replaces below are trying to catch and correct this
            // NB: The generic corrections can unintentionally cause their own issues, hence the specific corrections at
            // the end - i.e. '.replace("/count", "$count")'
            // NB: '%24' is the hexadecimal representation of '$', see
            // https://www.rapidtables.com/code/text/ascii-table.html
            String requestMsg = "-->  Request " + request.getMethod() + " "
                  + request.getURI().replace("%28", "(").replace("%29", ")").replace("%24", "/").replace("%3D", "=").replace("%2C", ",")
                        .replace("%27", "'").replace("/count", "$count").replace("/expand", "$expand").replace("/top", "$top")
                        .replace("/skip", "$skip");
            if (StringUtils.isNotBlank(request.getBody())) {
               requestMsg = requestMsg + " " + request.getBody().toString();
            }

            Response next = filterContext.next(request, response);

            // Now extract and prepare the header and body sections of the request and response messages for the
            // different types of reports
            // NB: With the new version of the Extent Reports plug-in the response message is split into separate
            // message header and body sections,
            // NB: This makes it possible to log the response body in "pretty print" format in the new Extent Reports
            // version
            String responseHeaders = next.getHeaders().toString();
            String responseBody = ((RestAssuredResponseImpl) next).getBody().asString().replace("\n", " ");

            String responseMsg = "<-- Response Status: " + next.getStatusCode() + " <br><p style=\"color:blue;\">Response Header:</p>"
                  + responseHeaders;
            String responseMsgNoBody = "<-- Response Status: " + next.getStatusCode() + " <br><p style=\"color:blue;\">Response Header:</p>"
                  + responseHeaders;

            if (!responseBody.isEmpty()) {
               responseMsg = responseMsg + " <br><p style=\"color:blue;\"> Response Body: </p> " + responseBody;
               responseMsgNoBody = responseMsgNoBody + " <br><p style=\"color:blue;\"> Response Body: </p> ";
            }

            // Log the request and response messages to Log4j and the original HTML Extent Report
            TestLoggerHolder.getLogger().info(requestMsg);
            TestLoggerHolder.getLogger().info(responseMsg);
            testReport.log(requestMsg);
            testReport.log(responseMsg);

            // Log the XML request message in the new type of Extent report created using the plug-in
            // "extentreports-cucumber6-adapter"
            TestReport.extentReportLogInfoMessage(requestMsg);
            TestReport.extentReportLogInfoXMLMessage(responseMsgNoBody, responseBody);

            return next;
         });

         isInit = true;
      }
   }
}