package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:web",
        glue = {"web", "hooks"},
        plugin = {
                "pretty",
                "html:build/cucumber-reports/web-cucumber-report.html",
                "json:build/cucumber-reports/web-cucumber-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@web"
)
public class WebTestRunner {
}
