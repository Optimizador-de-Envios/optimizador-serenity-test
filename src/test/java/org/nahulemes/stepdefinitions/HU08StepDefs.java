package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.questions.CurrentUrl;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.ConfirmProvider;
import org.nahulemes.tasks.DoLogin;
import org.nahulemes.tasks.DoLogout;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.NavigateToHistory;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.tasks.SelectProvider;
import org.nahulemes.ui.AppHeaderUI;
import org.nahulemes.ui.LoginPageUI;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.ui.ResultsPageUI;
import org.nahulemes.util.TestData;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class HU08StepDefs {

    @Dado("que el usuario ya está registrado en la plataforma")
    public void usuarioYaEstaRegistrado() {
        // The test user qa.usuario@example.com must be pre-seeded in the DB
    }

    @Cuando("ingresa correo {string} y contraseña {string}")
    public void ingresaCredenciales(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogin.withCredentials(email, password)
        );
    }

    @Entonces("el sistema permite el acceso y habilita las funcionalidades protegidas")
    public void sistemaPermiteAcceso() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(OrderFormUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(AppHeaderUI.LOGOUT_BUTTON), is(true))
        );
    }

    // ── TC-HU08-04 ──────────────────────────────────────────────────────────

    @Dado("que el usuario autenticado tiene historial de pedidos visible")
    public void usuarioConHistorialVisible() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogin.withDefaultTestUser(),
                NavigateToHistory.page()
        );
    }

    @Cuando("cierra sesión")
    public void cierraSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogout.now()
        );
    }

    @Entonces("el sistema redirige a la página de inicio de sesión")
    public void sistemaRedirigeALogin() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPageUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(CurrentUrl.displayed(), containsString(TestData.LOGIN_PATH))
        );
    }

    @Y("las rutas protegidas no son accesibles sin autenticación")
    public void rutasProtegidasInaccesibles() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.ORDER_PATH),
                WaitUntil.the(LoginPageUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(CurrentUrl.displayed(), containsString(TestData.LOGIN_PATH))
        );
    }

    // ── TC-HU08-06 ──────────────────────────────────────────────────────────

    @Dado("que el usuario inició sesión correctamente")
    public void usuarioInicioSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogin.withDefaultTestUser()
        );
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(OrderFormUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    @Cuando("recarga el navegador")
    public void recargaElNavegador() {
        BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver().navigate().refresh();
    }

    @Entonces("el sistema restaura la sesión y permanece en la ruta protegida")
    public void sistemaRestauraSesion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(OrderFormUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(AppHeaderUI.LOGOUT_BUTTON), is(true))
        );
    }
}
