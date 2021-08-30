import com.codeborne.selenide.Configuration;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
        features = {"src/test/resource"}
)

public class MiroTest {

    public static ClassLoader TestRun;

    @BeforeClass
    public static void setupTimeout() throws Exception
    {
        Configuration.timeout = 60000;
    }

}
