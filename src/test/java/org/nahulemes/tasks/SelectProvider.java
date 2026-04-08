package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.ui.ResultsPageUI;

public class SelectProvider implements Task {

    private final String providerName;

    public SelectProvider(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ResultsPageUI.selectButton(providerName), WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on(ResultsPageUI.selectButton(providerName))
        );
    }

    /** Selects whichever provider is shown as the recommendation (first select button visible). */
    public static SelectProvider theRecommendedOne() {
        return new SelectProvider("__recommended__") {
            @Override
            public <T extends Actor> void performAs(T actor) {
                actor.attemptsTo(
                        WaitUntil.the(ResultsPageUI.RECOMMENDATION_PROVIDER, WebElementStateMatchers.isVisible())
                                .forNoMoreThan(15).seconds()
                );
                String provider = ResultsPageUI.RECOMMENDATION_PROVIDER.resolveFor(actor).getText();
                actor.attemptsTo(
                        Click.on(ResultsPageUI.selectButton(provider))
                );
            }
        };
    }

    public static SelectProvider named(String providerName) {
        return Tasks.instrumented(SelectProvider.class, providerName);
    }
}
