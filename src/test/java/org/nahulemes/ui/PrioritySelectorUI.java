package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PrioritySelectorUI {

    public static final Target COST_OPTION =
            Target.the("cost priority option").locatedBy("[data-testid='option-cost']");

    public static final Target TIME_OPTION =
            Target.the("time priority option").locatedBy("[data-testid='option-time']");

    public static final Target CONFIRM_BUTTON =
            Target.the("confirm priority button")
                    .located(By.xpath("//button[normalize-space()='Confirmar']"));

    private PrioritySelectorUI() {
    }
}
