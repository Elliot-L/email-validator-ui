import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.TextFieldElement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class contains JUnit tests, which are run using Vaadin TestBench 4.
 *
 * To run this, first get an evaluation license from
 * https://vaadin.com/addon/vaadin-testbench and follow the instructions at
 * https://vaadin.com/directory/help/installing-cval-license to install it.
 *
 * Once the license is installed, you can run this class as a JUnit test.
 */
public class ValidatorTest extends TestBenchTestCase {
    @Rule
    public ScreenshotOnFailureRule screenshotOnFailureRule =
            new ScreenshotOnFailureRule(this, true);

    @Before
    public void setUp() throws Exception {
        setDriver(new PhantomJSDriver()); // Chrome
        
        //the line below can be uncommented to use firefox
        //setDriver(new FirefoxDriver());
        // To use Chrome, first install chromedriver.exe from
        // http://chromedriver.storage.googleapis.com/index.html
        // on your system path (e.g. C:\Windows\System32\)
        //   setDriver(new ChromeDriver()); // Chrome

        // To use Internet Explorer, first install iedriverserver.exe from
        // http://selenium-release.storage.googleapis.com/index.html?path=2.43/
        // on your system path (e.g. C:\Windows\System32\)
        //   setDriver(new InternetExplorerDriver()); // IE

        // To test headlessly (without a browser), first install phantomjs.exe
        // from http://phantomjs.org/download.html on your system path
        // (e.g. C:\Windows\System32\)
        //setDriver(new PhantomJSDriver()); // PhantomJS headless browser
    }

    /**
     * Opens the URL where the application is deployed.
     */
    private void openTestUrl() {
        getDriver().get("http://localhost:8080/");
    }

    @Test
    public void testClickButton() throws Exception {
        openTestUrl();

        // At first there should be no labels
        assertFalse($(LabelElement.class).exists());
        
        //the first section tests that a valid email should be valid
        
        // Set the name
        TextFieldElement textField = $(TextFieldElement.class)
        		.caption("Type your name here:").first();
        textField.setValue("elliot@dal.ca");

        // Click the button
        ButtonElement clickMeButton = $(ButtonElement.class).
                caption("Enter the email address to check:").first();
        clickMeButton.click();

        // There should now be one label
        assertEquals(1, $(LabelElement.class).all().size());
        // ... with the specified text
        assertEquals("After checking: elliot@dal.ca is a valid email address",
                $(LabelElement.class).first().getText());
        
        //Second section tests for failures 
        textField.setValue("shouldfail");        
        // Click the button again
        clickMeButton.click();

        // There should now be two labels
        List<LabelElement> allLabels = $(LabelElement.class).all();
        assertEquals(2, allLabels.size());
        // ... and the last label should have the correct text
        LabelElement lastLabel = allLabels.get(1);
        assertEquals("After checking: should fail is not a valid email address", lastLabel.getText());
    }
}