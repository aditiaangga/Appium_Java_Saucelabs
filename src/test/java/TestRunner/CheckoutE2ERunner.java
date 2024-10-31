package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
        glue= {"StepDef"},
        plugin ={"pretty","json:target/cucumber-result/json/cucumber.json"
                , "html:target/cucumber-result/html/cucumber.html"
        }
        ,tags =
        "@success"
//        "@problem"
//        "@locked_out"
)
public class CheckoutE2ERunner {
}
