package org.nahulemes.stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.questions.ElementIsVisible;
import org.nahulemes.questions.OrderHistoryCount;
import org.nahulemes.tasks.ConfirmProvider;
import org.nahulemes.tasks.DoLogin;
import org.nahulemes.tasks.FillOrderForm;
import org.nahulemes.tasks.NavigateToHistory;
import org.nahulemes.tasks.SelectPriority;
import org.nahulemes.tasks.SelectProvider;
import org.nahulemes.ui.ResultsPageUI;
import org.nahulemes.ui.UserOrdersPageUI;
import org.nahulemes.util.TestData;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class HU09StepDefs {

    @Dado("que el usuario autenticado tiene pedidos confirmados en su cuenta")
    public void usuarioTienePedidosConfirmados() {
        // Ensure there is at least one order by running the full flow
        OnStage.theActorInTheSpotlight().attemptsTo(
                FillOrderForm.withDefaults()
        );
        OnStage.theActorInTheSpotlight().attemptsTo(SelectPriority.cost());
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ResultsPageUI.RECOMMENDATION_PROVIDER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(20).seconds(),
                SelectProvider.theRecommendedOne(),
                ConfirmProvider.selection()
        );
    }

    @Cuando("consulta su historial de pedidos")
    public void consultaHistorialPedidos() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavigateToHistory.page()
        );
    }

    @Entonces("el sistema muestra sus pedidos con origen, destino, peso, prioridad y proveedor")
    public void sistemaMuestraPedidosConDatos() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(OrderHistoryCount.displayed(), greaterThan(0))
        );
    }

    // ── TC-HU09-02 ──────────────────────────────────────────────────────────

    @Dado("que el usuario autenticado no tiene pedidos registrados")
    public void usuarioSinPedidos() {
        // Log in with a fresh test user that has no orders
        OnStage.theActorInTheSpotlight().attemptsTo(
                DoLogin.withCredentials(TestData.TEST_USER_B_EMAIL, TestData.TEST_USER_B_PASSWORD)
        );
    }

    @Entonces("el sistema informa que no existen pedidos registrados")
    public void sistemaInformaSinPedidos() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ElementIsVisible.of(UserOrdersPageUI.ORDERS_EMPTY), is(true))
        );
    }
}
