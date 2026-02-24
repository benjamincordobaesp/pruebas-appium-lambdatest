package com.proyecto.tasks;

import com.proyecto.ui.CalculatorScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

/**
 * Tarea (Task) que ejecuta la suma de dos números simples (de 1 dígito) en la
 * calculadora.
 */
public class AddNumbers implements Task {

    private final String firstNumber;
    private final String secondNumber;

    public AddNumbers(String firstNumber, String secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public static AddNumbers fromTo(String firstNumber, String secondNumber) {
        return Tasks.instrumented(AddNumbers.class, firstNumber, secondNumber);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Hacemos clic en el primer número
                Click.on(CalculatorScreen.DIGIT_BUTTON.of(firstNumber)),
                // Hacemos clic en el símbolo +
                Click.on(CalculatorScreen.ADD_BUTTON),
                // Hacemos clic en el segundo número
                Click.on(CalculatorScreen.DIGIT_BUTTON.of(secondNumber)),
                // Hacemos clic en el símbolo =
                Click.on(CalculatorScreen.EQUALS_BUTTON));
    }
}
