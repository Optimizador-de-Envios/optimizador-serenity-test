package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPageUI {

    public static final Target EMAIL_INPUT =
            Target.the("email input").locatedBy("#login-email");

    public static final Target PASSWORD_INPUT =
            Target.the("password input").locatedBy("#login-password");

    public static final Target SUBMIT_BUTTON =
            Target.the("login submit button").locatedBy("button[type='submit']");

    public static final Target ERROR_MESSAGE =
            Target.the("login error message").locatedBy("[role='alert']");

    private LoginPageUI() {
    }
}
