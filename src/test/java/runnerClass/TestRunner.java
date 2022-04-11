package runnerClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(
        features = "src/test/java/features/",
        dryRun=false,
        monochrome=true,
        glue = {"stepDefinition"},
        //tags = "@Colorcontrast",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                })
public class TestRunner{
    public WebDriver driver;
    
    
        
    private TestNGCucumberRunner testNGCucumberRunner;
   
  
    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
    	

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleEvent,FeatureWrapper featureWrapper){
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }
    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
  
}