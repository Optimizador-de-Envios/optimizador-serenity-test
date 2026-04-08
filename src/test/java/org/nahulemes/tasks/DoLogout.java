package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.nahulemes.ui.AppHeaderUI;
import org.nahulemes.ui.LoginPageUI;

public class DoLogout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(AppHeaderUI.LOGOUT_BUTTON),
                WaitUntil.the(LoginPageUI.SUBMIT_BUTTON, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    public static DoLogout now() {
        return Tasks.instrumented(DoLogout.class);
    }
}
