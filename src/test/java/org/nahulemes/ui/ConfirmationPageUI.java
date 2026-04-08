package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPageUI {

    public static final Target SUCCESS_HEADER =
            Target.the("confirmation success header").locatedBy("[data-testid='confirmation-success']");

    public static final Target PROVIDER =
            Target.the("confirmed provider name").locatedBy("[data-testid='confirmation-provider']");

    public static final Target ORIGIN =
            Target.the("confirmation origin").locatedBy("[data-testid='confirmation-origin']");

    public static final Target DESTINATION =
            Target.the("confirmation destination").locatedBy("[data-testid='confirmation-destination']");

    public static final Target DISTANCE =
            Target.the("confirmation distance").locatedBy("[data-testid='confirmation-distance']");

    public static final Target ROUTE_SECTION =
            Target.the("shipment route section").locatedBy("[data-testid='shipment-route-section']");

    public static final Target ROUTE_MAP =
            Target.the("shipment route map").locatedBy("[data-testid='shipment-route-map']");

    private ConfirmationPageUI() {
    }
}
