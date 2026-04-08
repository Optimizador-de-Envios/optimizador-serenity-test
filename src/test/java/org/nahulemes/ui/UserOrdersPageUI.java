package org.nahulemes.ui;

import net.serenitybdd.screenplay.targets.Target;

public class UserOrdersPageUI {

    public static final Target ORDERS_EMPTY =
            Target.the("no orders message").locatedBy("[data-testid='orders-empty']");

    public static final Target ORDERS_LOADING =
            Target.the("orders loading indicator").locatedBy("[data-testid='orders-loading']");

    public static final Target ORDERS_ERROR =
            Target.the("orders error message").locatedBy("[data-testid='orders-error']");

    public static final Target ORDER_ITEMS =
            Target.the("order history items").locatedBy("//article");

    private UserOrdersPageUI() {
    }
}
