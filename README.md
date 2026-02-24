# Ejecución del Proyecto Serenity + Appium + LambdaTest

Este proyecto demuestra cómo ejecutar pruebas automatizadas móviles usando el patrón Screenplay de Serenity BDD con Appium, ejecutándose en la nube a través de la plataforma LambdaTest.

## Requisitos Previos

1. Java JDK 11 o superior instalado y configurado en tus variables de entorno.
2. Maven instalado en tu sistema (`mvn -version`).
3. Una cuenta activa en [LambdaTest](https://www.lambdatest.com/).

## Configuración de Autenticación de LambdaTest

Necesitas tu **Username** y tu **Access Key** de LambdaTest. Puedes encontrarlos en el panel principal (Dashboard) o en la sección de Automation Settings en tu cuenta de LambdaTest.

Para evitar almacenar credenciales sensibles en el código, el archivo `serenity.conf` espera que estas credenciales estén en variables de entorno. Exórtalas en tu terminal antes de correr las pruebas:

**En Mac o Linux:**
```bash
export LT_USERNAME="tu_usuario_de_lambdatest"
export LT_ACCESS_KEY="tu_llave_de_acceso"
```

**En Windows (Command Prompt):**
```cmd
set LT_USERNAME=tu_usuario_de_lambdatest
set LT_ACCESS_KEY=tu_llave_de_acceso
```

**En Windows (PowerShell):**
```powershell
$env:LT_USERNAME="tu_usuario_de_lambdatest"
$env:LT_ACCESS_KEY="tu_llave_de_acceso"
```

## Configurar tu Aplicación a Probar (App)

Para este proyecto específico de **Calculadora en Android (Pixel 7)**, no hace falta que subas una APP a LambdaTest. En vez de enviar la URL de una app externa usando `"app" = "lt://..."`, el archivo `serenity.conf` ya tiene configurados explícitamente el paquete nativo y la actividad correspondiente de la calculadora de Google en Android (`appPackage` y `appActivity`).

Si deseas ejecutarlo en iOS u otra app personalizada, debes subir tu aplicación (`.apk` o `.ipa`) a LambdaTest:
1. Ve a "App Automation".
2. Sube la app y copia el **App URL** (ej. `lt://APP...`).
3. Modifica tu bloque en `serenity.conf` eliminando el paquete de la calculadora y colocando `"app"` = tu_url_copiada.

## Ejecución de Pruebas

Para ejecutar las pruebas apuntando a dispositivos en la nube de LambdaTest, debes usar Maven desde tu terminal e indicarle a Serenity el entorno (`environment`).

Para ejecutar las pruebas en un dispositivo **Android**:

```bash
mvn clean verify -Denvironment=android
```

Para ejecutar las pruebas en un dispositivo **iOS**:

```bash
mvn clean verify -Denvironment=ios
```

## Generación de Reportes de Serenity

Al ejecutar el comando `mvn clean verify`, el plugin the Serenity integrado en el archivo `pom.xml` generará automáticamente el reporte en formato HTML al finalizar las pruebas (gracias al goal `aggregate` en la fase `post-integration-test`).

Si por alguna razón necesitas generar el reporte manualmente sin correr los tests, ejecuta:

```bash
mvn serenity:aggregate
```

El reporte final lo encontrarás dentro del directorio del proyecto en la siguiente ruta:
`target/site/serenity/index.html`

Abre ese archivo en tu navegador web para visualizar los resultados interactivos, tiempos de ejecución y las capturas de pantalla de la prueba paso a paso.
