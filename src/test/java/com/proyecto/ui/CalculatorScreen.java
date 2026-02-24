package com.proyecto.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Mapeo de elementos de la interfaz de usuario para la calculadora nativa de
 * Android de Google
 * (App ID: com.google.android.calculator).
 */
public class CalculatorScreen {

    // Target genérico paramétrico para hacer clic en los números del 0 al 9
    // dinámicamente
    // Usamos el ID estándar que Android le da a los botones numéricos en esta app.
    public static final Target DIGIT_BUTTON = Target.the("botón del dígito {0}")
            .locatedBy("id:com.google.android.calculator:id/digit_{0}"); // Reemplazará {0} con el número

    // Botón de suma (+)
    public static final Target ADD_BUTTON = Target.the("botón de suma")
            .located(AppiumBy.accessibilityId("plus"));

    // Botón de igual (=)
    public static final Target EQUALS_BUTTON = Target.the("botón de igual")
            .located(AppiumBy.accessibilityId("equals"));

    // Campo de resultados donde se muestra la operación final ("result_final") o
    // parcial ("result_preview")
    public static final Target RESULT_FIELD = Target.the("campo de resultado")
            .located(AppiumBy.id("com.google.android.calculator:id/result_final"));
}
