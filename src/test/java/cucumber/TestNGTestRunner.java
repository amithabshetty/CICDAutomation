package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber - testng,junit depending on what assertions are used
@CucumberOptions(features="src/test/java",glue="SeleniumAutomaiton.StepDefinitionFiles",
monochrome=true,tags="@Regression",plugin={"html:target/cucumber.html"})//output of cucumber will be in encrypted format, monochrome is to create report in readable format,)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {//inbuilt cucumber doesnt have ability to scan testng codes and assertions

}
