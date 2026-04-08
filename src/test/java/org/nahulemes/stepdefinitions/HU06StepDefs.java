package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.ConfirmProvider;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.tasks.SelectProvider;
import org.nahulemes.ui.ConfirmationPageUI;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.ui.ResultsPageUI;
import org.nahulemes.util.TestData;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class HU06StepDefs {

    @Dado("que el usuario autenticado ha confirmado un pedido con origen {string} destino {string} peso {string} y prioridad {string}")
    public void usuarioHaConfirmadoPedido(String origin, String destination, String weight, String priority) {
        boolean useCost = "COST".equalsIgnoreCase(priority);
        OnStage.theActorInTheSpotlight().attemptsTo(
                FillOrderForm.with(origin, destination, weight)
        );
        if (useCost) {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.cost());
        } else {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.time());
        }
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ResultsPageUI.RECOMMENDATION_PROVIDER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(20).seconds(),
                SelectProvider.theRecommendedOne(),
                ConfirmProvider.selection()
        );
    }

    @Cuando("visualiza la página de confirmación")
    public void visualizaPaginaConfirmacion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ConfirmationPageUI.SUCCESS_HEADER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    @Entonces("el mapa se renderiza con la ruta dibujada")
    public void mapaSeRenderizaConRuta() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ConfirmationPageUI.ROUTE_MAP, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ConfirmationPageUI.ROUTE_MAP), is(true))
        );
    }

    @Y("los marcadores de origen y destino son visibles")
    public void marcadoresOrigenDestinoVisibles() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ConfirmationPageUI.ROUTE_SECTION), is(true))
        );
    }

    @Entonces("el contenedor del mapa es visible en pantalla")
    public void contenedorMapaEsVisible() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ConfirmationPageUI.ROUTE_MAP), is(true))
        );
    }

    @Y("la sección de ruta está desplegada correctamente")
    public void seccionRutaDesplegada() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ConfirmationPageUI.ROUTE_SECTION), is(true))
        );
    }

    @Dado("que el usuario autenticado está en el formulario de pedido")
    public void usuarioEstaEnFormularioPedido() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.ORDER_PATH)
        );
    }

    @Cuando("no ha completado origen ni destino")
    public void noHaCompletadoOrigenNiDestino() {
        // The order form is opened without filling any fields — no action needed
    }

    @Entonces("no se muestra el mapa de vista previa")
    public void noSeMuestraMapaVistaPrevia() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(OrderFormUI.ROUTE_PREVIEW_SECTION), is(false))
        );
    }
}
