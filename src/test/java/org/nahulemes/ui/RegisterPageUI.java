package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;

public class RegisterPageUI {

    public static final Target NAME_INPUT =
            Target.the("name input").locatedBy("#register-name");

    public static final Target EMAIL_INPUT =
            Target.the("email input").locatedBy("#register-email");

    public static final Target PASSWORD_INPUT =
            Target.the("password input").locatedBy("#register-password");

    public static final Target SUBMIT_BUTTON =
            Target.the("register submit button").locatedBy("button[type='submit']");

    public static final Target ERROR_MESSAGE =
            Target.the("register error message").locatedBy("[role='alert']");

    private RegisterPageUI() {
    }
}
