package com.proyecto.questions;

import com.proyecto.ui.CalculatorScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/**
 * Pregunta (Question) que evalúa el resultado mostrado en la pantalla de la
 * calculadora.
 */
public class CalculatorResult implements Question<String> {

    public static CalculatorResult displayed() {
        return new CalculatorResult();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Devuelve el texto contenido en el campo de resultado final de la calculadora
        return Text.of(CalculatorScreen.RESULT_FIELD).answeredBy(actor);
    }
}
