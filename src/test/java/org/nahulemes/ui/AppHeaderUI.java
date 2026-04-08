package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AppHeaderUI {

    public static final Target HISTORY_LINK =
            Target.the("history link").locatedBy("a[href='/history']");

    public static final Target LOGOUT_BUTTON =
            Target.the("logout button")
                    .located(By.xpath("//button[contains(normalize-space(),'Cerrar sesión')]"));

    public static final Target LOGIN_LINK =
            Target.the("login link").locatedBy("a[href='/login']");

    public static final Target REGISTER_LINK =
            Target.the("register link").locatedBy("a[href='/register']");

    private AppHeaderUI() {
    }
}
