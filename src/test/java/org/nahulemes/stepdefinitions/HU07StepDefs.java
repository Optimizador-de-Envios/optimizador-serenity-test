package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.questions.CurrentUrl;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.DoLogin;
import org.nahulemes.tasks.DoRegister;
import org.nahulemes.ui.LoginPageUI;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.util.TestData;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class HU07StepDefs {

    @Dado("que una persona desea utilizar la plataforma")
    public void personaDeseaUtilizarPlataforma() {
        // Actor is set on stage via the Hook @Before — no additional action needed
    }

    @Cuando("ingresa nombre {string}, un correo único y contraseña {string}")
    public void ingresaDatosRegistro(String name, String password) {
        String uniqueEmail = TestData.REGISTER_EMAIL_PREFIX
                + System.currentTimeMillis()
                + TestData.REGISTER_EMAIL_DOMAIN;

        OnStage.theActorInTheSpotlight().remember("registeredEmail", uniqueEmail);
        OnStage.theActorInTheSpotlight().remember("registeredPassword", password);

        OnStage.theActorInTheSpotlight().attemptsTo(
                DoRegister.withData(name, uniqueEmail, password)
        );
    }

    @Entonces("el sistema registra al usuario y puede iniciar sesión con la cuenta creada")
    public void sistemaRegistraUsuarioYPuedeIniciarSesion() {
        // After registration the app should redirect to /login or /
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPageUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );

        String email    = OnStage.theActorInTheSpotlight().recall("registeredEmail");
        String password = OnStage.theActorInTheSpotlight().recall("registeredPassword");

        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogin.withCredentials(email, password)
        );

        // After login the protected order page should be accessible
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(OrderFormUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(CurrentUrl.displayed(), containsString("/"))
        );
    }
}
