package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OrderFormUI {

    public static final Target ORIGIN_INPUT =
            Target.the("origin input").locatedBy("[data-testid='input-origin']");

    public static final Target DESTINATION_INPUT =
            Target.the("destination input").locatedBy("[data-testid='input-destination']");

    public static final Target WEIGHT_INPUT =
            Target.the("weight input").locatedBy("[data-testid='input-weight']");

    public static final Target SUBMIT_BUTTON =
            Target.the("calculate shipping button").locatedBy("button[type='submit']");

    public static final Target ORDER_SUCCESS =
            Target.the("order success message").locatedBy("[data-testid='order-success']");

    public static final Target ROUTE_PREVIEW_SECTION =
            Target.the("route preview section").locatedBy("[data-testid='shipment-route-preview']");

    public static final Target ROUTE_PREVIEW_MAP =
            Target.the("route preview map").locatedBy("[data-testid='shipment-route-preview-map']");

    public static final Target NO_ROUTE_DATA =
            Target.the("no route data message").locatedBy("[data-testid='no-route-data']");

    /** First autocomplete suggestion for the origin input */
    public static final Target ORIGIN_FIRST_SUGGESTION =
            Target.the("first origin suggestion")
                    .located(By.xpath("//input[@data-testid='input-origin']/../ul/li[1]"));

    /** All autocomplete suggestions for the origin input */
    public static final Target ORIGIN_SUGGESTIONS =
            Target.the("origin suggestions")
                    .located(By.xpath("//input[@data-testid='input-origin']/../ul/li"));

    /** First autocomplete suggestion for the destination input */
    public static final Target DESTINATION_FIRST_SUGGESTION =
            Target.the("first destination suggestion")
                    .located(By.xpath("//input[@data-testid='input-destination']/../ul/li[1]"));

    /** All autocomplete suggestions for the destination input */
    public static final Target DESTINATION_SUGGESTIONS =
            Target.the("destination suggestions")
                    .located(By.xpath("//input[@data-testid='input-destination']/../ul/li"));

    private OrderFormUI() {
    }
}
