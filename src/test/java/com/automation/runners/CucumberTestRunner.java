package com.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.automation.stepdefinitions",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json"
        },
        tags = "@pet"
)
public class CucumberTestRunner {
    // Runner de Cucumber con Serenity BDD.
    // No requiere contenido adicional.
}

