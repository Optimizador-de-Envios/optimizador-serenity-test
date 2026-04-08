package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ResultsPageUI {

    public static final Target RECOMMENDATION_PROVIDER =
            Target.the("recommendation provider name").locatedBy("[data-testid='recommendation-provider']");

    public static final Target RECOMMENDATION_COST =
            Target.the("recommendation cost").locatedBy("[data-testid='recommendation-cost']");

    public static final Target RECOMMENDATION_DAYS =
            Target.the("recommendation estimated days").locatedBy("[data-testid='recommendation-days']");

    public static final Target CONFIRM_BUTTON =
            Target.the("confirm selection button").locatedBy("[data-testid='confirm-button']");

    public static final Target NO_ALTERNATIVES =
            Target.the("no alternatives message").locatedBy("[data-testid='no-alternatives']");

    public static Target optionCard(String providerName) {
        return Target.the("option card for " + providerName)
                .locatedBy("[data-testid='option-card-" + providerName + "']");
    }

    public static Target selectButton(String providerName) {
        return Target.the("select button for " + providerName)
                .locatedBy("[data-testid='select-button-" + providerName + "']");
    }

    private ResultsPageUI() {
    }
}
