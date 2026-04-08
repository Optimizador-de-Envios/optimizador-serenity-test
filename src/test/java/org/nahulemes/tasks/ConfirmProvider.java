package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.ui.ConfirmationPageUI;
import org.nahulemes.ui.ResultsPageUI;

public class ConfirmProvider implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ResultsPageUI.CONFIRM_BUTTON, WebElementStateMatchers.isEnabled())
                        .forNoMoreThan(10).seconds(),
                Click.on(ResultsPageUI.CONFIRM_BUTTON),
                WaitUntil.the(ConfirmationPageUI.SUCCESS_HEADER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds()
        );
    }

    public static ConfirmProvider selection() {
        return Tasks.instrumented(ConfirmProvider.class);
    }
}
