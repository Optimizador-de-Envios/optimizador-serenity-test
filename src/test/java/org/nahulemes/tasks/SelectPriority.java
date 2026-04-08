package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.ui.OrderFormUI;
import org.nahulemes.ui.PrioritySelectorUI;

public class SelectPriority implements Task {

    private final boolean useCost;

    public SelectPriority(boolean useCost) {
        this.useCost = useCost;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(OrderFormUI.SUBMIT_BUTTON),
                WaitUntil.the(PrioritySelectorUI.COST_OPTION, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on(useCost ? PrioritySelectorUI.COST_OPTION : PrioritySelectorUI.TIME_OPTION),
                Click.on(PrioritySelectorUI.CONFIRM_BUTTON)
        );
    }

    public static SelectPriority cost() {
        return Tasks.instrumented(SelectPriority.class, true);
    }

    public static SelectPriority time() {
        return Tasks.instrumented(SelectPriority.class, false);
    }
}
