package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.ui.PrioritySelectorUI;
import org.nahulemes.ui.ResultsPageUI;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class HU02StepDefs {

    @Dado("que el usuario autenticado registró un pedido válido con origen {string} destino {string} y peso {string}")
    public void usuarioRegistroPedidoValido(String origin, String destination, String weight) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                FillOrderForm.with(origin, destination, weight)
        );
    }

    @Cuando("selecciona la prioridad {string}")
    public void seleccionaPrioridad(String priority) {
        boolean useCost = "COST".equalsIgnoreCase(priority);
        if (useCost) {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.cost());
        } else {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.time());
        }
    }

    @Entonces("el sistema registra la prioridad y permite continuar con la recomendación")
    public void sistemaRegistraPrioridadYPermiteContinuar() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ResultsPageUI.RECOMMENDATION_PROVIDER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(20).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ResultsPageUI.RECOMMENDATION_PROVIDER), is(true))
        );
    }
}
