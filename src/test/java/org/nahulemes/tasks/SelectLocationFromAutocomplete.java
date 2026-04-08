package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.ui.OrderFormUI;

/**
 * Types a search query into a location input, waits for the autocomplete
 * suggestions to appear, then clicks the first result to properly populate
 * the Location object (name + lat + lng).
 */
public class SelectLocationFromAutocomplete implements Task {

    private final String searchText;
    private final boolean isOrigin;

    public SelectLocationFromAutocomplete(String searchText, boolean isOrigin) {
        this.searchText = searchText;
        this.isOrigin = isOrigin;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var input      = isOrigin ? OrderFormUI.ORIGIN_INPUT      : OrderFormUI.DESTINATION_INPUT;
        var firstSugg  = isOrigin ? OrderFormUI.ORIGIN_FIRST_SUGGESTION : OrderFormUI.DESTINATION_FIRST_SUGGESTION;

        actor.attemptsTo(
                Click.on(input),
                Enter.theValue(searchText).into(input),
                WaitUntil.the(firstSugg, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on(firstSugg)
        );
    }

    public static SelectLocationFromAutocomplete forOrigin(String searchText) {
        return Tasks.instrumented(SelectLocationFromAutocomplete.class, searchText, true);
    }

    public static SelectLocationFromAutocomplete forDestination(String searchText) {
        return Tasks.instrumented(SelectLocationFromAutocomplete.class, searchText, false);
    }
}
