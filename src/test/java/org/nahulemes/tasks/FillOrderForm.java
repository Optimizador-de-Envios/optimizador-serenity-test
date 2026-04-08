package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.util.TestData;

/**
 * Navigates to the order form and fills origin, destination and weight.
 * Each location is resolved through the autocomplete, selecting the first
 * result to ensure the Location object has name + lat + lng.
 */
public class FillOrderForm implements Task {

    private final String originQuery;
    private final String destinationQuery;
    private final String weight;

    public FillOrderForm(String originQuery, String destinationQuery, String weight) {
        this.originQuery = originQuery;
        this.destinationQuery = destinationQuery;
        this.weight = weight;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.ORDER_PATH),
                SelectLocationFromAutocomplete.forOrigin(originQuery),
                SelectLocationFromAutocomplete.forDestination(destinationQuery),
                Click.on(OrderFormUI.WEIGHT_INPUT),
                Enter.theValue(weight).into(OrderFormUI.WEIGHT_INPUT)
        );
    }

    public static FillOrderForm withDefaults() {
        return Tasks.instrumented(
                FillOrderForm.class,
                TestData.ORIGIN_SEARCH_TEXT,
                TestData.DESTINATION_SEARCH_TEXT,
                TestData.WEIGHT_VALUE
        );
    }

    public static FillOrderForm with(String origin, String destination, String weight) {
        return Tasks.instrumented(FillOrderForm.class, origin, destination, weight);
    }
}
