package com.proyecto.stepdefinitions;

import com.proyecto.questions.CalculatorResult;
import com.proyecto.tasks.AddNumbers;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * Mapeo de los pasos Gherkin (Calculadora) con código Screenplay.
 */
public class CalculatorStepDefinitions {

    @Managed
    WebDriver driver;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que {string} abre la calculadora")
    public void queAbreLaCalculadora(String actorName) {
        theActorCalled(actorName);
        if (driver != null) {
            try {
                WebDriver realDriver = ((net.thucydides.core.webdriver.WebDriverFacade) driver).getProxiedDriver();
                System.out.println("REAL DRIVER CLASS: " + realDriver.getClass().getName());
            } catch (Exception e) {
                System.out.println("FAILED TO GET PROXIED DRIVER: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("DRIVER IS NULL!");
        }
    }

    @Cuando("(él )(ella )suma {string} más {string}")
    public void ellaSumaMas(String firstNumber, String secondNumber) {
        WebDriver actorDriver = net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(theActorInTheSpotlight())
                .getDriver();
        if (actorDriver instanceof net.thucydides.core.webdriver.WebDriverFacade) {
            WebDriver realDriver = ((net.thucydides.core.webdriver.WebDriverFacade) actorDriver).getProxiedDriver();
            if (realDriver instanceof org.openqa.selenium.remote.RemoteWebDriver) {
                System.out.println("ACTOR SESSION ID: "
                        + ((org.openqa.selenium.remote.RemoteWebDriver) realDriver).getSessionId());
            }
        }
        System.out.println("EXECUTING CLICKS NOW...");
        theActorInTheSpotlight().attemptsTo(
                AddNumbers.fromTo(firstNumber, secondNumber));
    }

    @Entonces("el resultado que (él )(ella )debería ver es {string}")
    public void elResultadoQueEllaDeberiaVerEs(String expectedResult) {
        theActorInTheSpotlight().attemptsTo(
                net.serenitybdd.screenplay.waits.WaitUntil.the(
                        com.proyecto.ui.CalculatorScreen.RESULT_FIELD,
                        net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText(expectedResult))
                        .forNoMoreThan(15).seconds());

        theActorInTheSpotlight().should(
                seeThat("El resultado de la operación",
                        CalculatorResult.displayed(), equalTo(expectedResult)));
    }
}
