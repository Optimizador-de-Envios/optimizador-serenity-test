package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import org.nahulemes.ui.AppHeaderUI;
import org.nahulemes.util.TestData;
import org.nahulemes.hooks.OpenBrowser;

/**
 * Navigates to the order history page.
 * Assumes the actor is already authenticated.
 */
public class NavigateToHistory implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.HISTORY_PATH)
        );
    }

    public static NavigateToHistory page() {
        return Tasks.instrumented(NavigateToHistory.class);
    }
}
