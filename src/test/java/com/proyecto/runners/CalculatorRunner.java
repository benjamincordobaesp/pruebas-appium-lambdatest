package com.proyecto.runners;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Ejecutor principal de las pruebas de la Calculadora para Serenity 5.x.
 * Utiliza JUnit 5 (JUnit Platform Suite) en lugar del obsoleto
 * CucumberWithSerenity.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/calculator.feature")
public class CalculatorRunner {
}
