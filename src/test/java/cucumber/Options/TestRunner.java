package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/features", glue= {"stepDefinitions"})

public class TestRunner {

}
// working as intended in 110. Removing Hardcoded resource details with Enum class Methods