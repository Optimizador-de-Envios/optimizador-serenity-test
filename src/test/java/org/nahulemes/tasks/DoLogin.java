package org.nahulemes.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.nahulemes.hooks.OpenBrowser;
import org.nahulemes.ui.LoginPageUI;
import org.nahulemes.util.TestData;

public class DoLogin implements Task {

    private final String email;
    private final String password;

    public DoLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenBrowser.withUrl(TestData.APP_URL + TestData.LOGIN_PATH),
                Enter.theValue(email).into(LoginPageUI.EMAIL_INPUT),
                Enter.theValue(password).into(LoginPageUI.PASSWORD_INPUT),
                Click.on(LoginPageUI.SUBMIT_BUTTON)
        );
    }

    public static DoLogin withCredentials(String email, String password) {
        return Tasks.instrumented(DoLogin.class, email, password);
    }

    public static DoLogin withDefaultTestUser() {
        return Tasks.instrumented(DoLogin.class, TestData.TEST_USER_EMAIL, TestData.TEST_USER_PASSWORD);
    }
}
