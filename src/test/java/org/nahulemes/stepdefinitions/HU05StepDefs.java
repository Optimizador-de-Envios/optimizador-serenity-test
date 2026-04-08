package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.ConfirmProvider;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.tasks.SelectProvider;
import org.nahulemes.ui.ConfirmationPageUI;
import org.nahulemes.ui.ResultsPageUI;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class HU05StepDefs {

    @Dado("que el usuario autenticado visualiza recomendación para origen {string} destino {string} peso {string} con prioridad {string}")
    public void usuarioVisualizaRecomendacion(String origin, String destination, String weight, String priority) {
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
                        .forNoMoreThan(20).seconds()
        );
    }

    @Cuando("selecciona el proveedor recomendado")
    public void seleccionaProveedorRecomendado() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SelectProvider.theRecommendedOne()
        );
    }

    @Y("confirma la selección")
    public void confirmaLaSeleccion() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConfirmProvider.selection()
        );
    }

    @Entonces("el sistema muestra la pantalla de confirmación con los datos del pedido")
    public void sistemaMuestraPantallaConfirmacion() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ConfirmationPageUI.SUCCESS_HEADER), is(true)),
                seeThat(ElementIsVisible.of(ConfirmationPageUI.PROVIDER), is(true)),
                seeThat(ElementIsVisible.of(ConfirmationPageUI.ORIGIN), is(true)),
                seeThat(ElementIsVisible.of(ConfirmationPageUI.DESTINATION), is(true))
        );
    }
}
