package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.ui.RegisterPageUI;
import org.nahulemes.util.TestData;

public class DoRegister implements Task {

    private final String name;
    private final String email;
    private final String password;

    public DoRegister(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.REGISTER_PATH),
                Enter.theValue(name).into(RegisterPageUI.NAME_INPUT),
                Enter.theValue(email).into(RegisterPageUI.EMAIL_INPUT),
                Enter.theValue(password).into(RegisterPageUI.PASSWORD_INPUT),
                Click.on(RegisterPageUI.SUBMIT_BUTTON)
        );
    }

    public static DoRegister withData(String name, String email, String password) {
        return Tasks.instrumented(DoRegister.class, name, email, password);
    }
}
