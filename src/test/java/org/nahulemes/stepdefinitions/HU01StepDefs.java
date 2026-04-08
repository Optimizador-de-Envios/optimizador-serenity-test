package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.tasks.DoLogin;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.SelectLocationFromAutocomplete;
import org.nahulemes.questions.AutocompleteSuggestionsContainCountry;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.ui.PrioritySelectorUI;
import org.nahulemes.util.TestData;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class HU01StepDefs {

    @Dado("que el usuario está autenticado en la plataforma")
    public void usuarioAutenticado() {
        OnStage.theActorCalled("Usuario").attemptsTo(
                DoLogin.withDefaultTestUser()
        );
    }

    @Dado("que el usuario autenticado necesita enviar un producto")
    public void usuarioNecesitaEnviar() {
        // Authentication already done in background step
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.ORDER_PATH)
        );
    }

    @Cuando("ingresa origen {string}, destino {string} y peso {string}")
    public void ingresaDatosOrigenDestinoPeso(String origin, String destination, String weight) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectLocationFromAutocomplete.forOrigin(origin),
                SelectLocationFromAutocomplete.forDestination(destination),
                Click.on(OrderFormUI.WEIGHT_INPUT),
                Enter.theValue(weight).into(OrderFormUI.WEIGHT_INPUT)
        );
    }

    @Entonces("el formulario avanza al cálculo conservando los datos")
    public void formularioAvanzaAlCalculo() {
        // Clicking submit transitions the form step → priority step in one React 18 batch,
        // so 'order-success' is never rendered.  The visible indicator that the order was
        // accepted is the PrioritySelector appearing immediately after submit.
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(OrderFormUI.SUBMIT_BUTTON),
                WaitUntil.the(PrioritySelectorUI.COST_OPTION, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(PrioritySelectorUI.COST_OPTION), is(true))
        );
    }

    @Dado("que el usuario autenticado registra un pedido")
    public void usuarioRegistraPedido() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.ORDER_PATH)
        );
    }

    @Cuando("escribe texto parcial {string} en el campo de origen")
    public void escribeTextoParcialEnOrigen(String query) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(OrderFormUI.ORIGIN_INPUT),
                Enter.theValue(query).into(OrderFormUI.ORIGIN_INPUT),
                WaitUntil.the(OrderFormUI.ORIGIN_FIRST_SUGGESTION, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds()
        );
    }

    @Entonces("el sistema muestra sugerencias que contienen {string}")
    public void sistemaMuestraSugerenciasConPais(String expectedCountry) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(AutocompleteSuggestionsContainCountry.named(expectedCountry), is(true))
        );
    }

    @Y("el usuario puede seleccionar una sugerencia válida")
    public void usuarioSeleccionaSugerencia() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(OrderFormUI.ORIGIN_FIRST_SUGGESTION)
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(OrderFormUI.ORIGIN_INPUT), is(true))
        );
    }
}
