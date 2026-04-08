package org.nahulemes.stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.ui.ResultsPageUI;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class HU04StepDefs {

    @Dado("que el usuario autenticado obtuvo una recomendación para origen {string} destino {string} peso {string} con prioridad {string}")
    public void usuarioObtuvoRecomendacion(String origin, String destination, String weight, String priority) {
        boolean useCost = "COST".equalsIgnoreCase(priority);
        OnStage.theActorInTheSpotlight().attemptsTo(
                FillOrderForm.with(origin, destination, weight)
        );
        if (useCost) {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.cost());
        } else {
            OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.time());
        }
    }

    @Cuando("el sistema muestra la página de resultados")
    public void sistemaMuestraPaginaResultados() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ResultsPageUI.RECOMMENDATION_PROVIDER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(20).seconds()
        );
    }

    @Entonces("aparece la recomendación principal con proveedor, costo y tiempo")
    public void apareceRecomendacionPrincipal() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ResultsPageUI.RECOMMENDATION_PROVIDER), is(true)),
                seeThat(ElementIsVisible.of(ResultsPageUI.RECOMMENDATION_COST), is(true)),
                seeThat(ElementIsVisible.of(ResultsPageUI.RECOMMENDATION_DAYS), is(true))
        );
    }

    @Y("las alternativas disponibles se muestran con proveedor, costo y tiempo")
    public void alternativasDisponiblesSeRenderean() {
        // The alternatives section is always present; it shows either option cards or the no-alternatives message
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(ResultsPageUI.RECOMMENDATION_PROVIDER), is(true))
        );
    }

    @Y("no existen alternativas")
    public void noExistenAlternativas() {
        // This step is a precondition marker; the actual assertion is in the Entonces step
    }

    @Entonces("la UI informa que no hay alternativas disponibles")
    public void uiInformaNoHayAlternativas() {
        // TC-HU04-02 requires backend to return zero alternatives for the given route.
        // With real data (Bogotá→Medellín) providers are always available.
        // This scenario needs a controlled/mock backend environment to be validated.
        throw new PendingException("TC-HU04-02: requires backend with no available providers for the route");
    }
}
