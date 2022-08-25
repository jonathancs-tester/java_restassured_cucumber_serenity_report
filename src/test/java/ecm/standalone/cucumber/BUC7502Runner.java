package ecm.standalone.cucumber;

import org.junit.runner.RunWith;

import com.standalone.testbase.TestBase;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/BUC7502/")
public class BUC7502Runner extends TestBase{

}
