package com.proyecto.stepdefinitions;

import com.proyecto.questions.CalculatorResult;
import com.proyecto.tasks.AddNumbers;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/**
 * Mapeo de los pasos Gherkin (Calculadora) con código Screenplay.
 */
public class CalculatorStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que {string} abre la calculadora")
    public void queAbreLaCalculadora(String actorName) {
        // Inicializar el actor, Appium levantará la app predeterminada (Calculadora)
        // según las capabilities seteadas en serenity.conf
        theActorCalled(actorName);
    }

    @Cuando("(él )(ella )suma {string} más {string}")
    public void ellaSumaMas(String firstNumber, String secondNumber) {
        theActorInTheSpotlight().attemptsTo(
                AddNumbers.fromTo(firstNumber, secondNumber));
    }

    @Entonces("el resultado que (él )(ella )debería ver es {string}")
    public void elResultadoQueEllaDeberiaVerEs(String expectedResult) {
        theActorInTheSpotlight().should(
                seeThat("El resultado de la operación",
                        CalculatorResult.displayed(), equalTo(expectedResult)));
    }
}
