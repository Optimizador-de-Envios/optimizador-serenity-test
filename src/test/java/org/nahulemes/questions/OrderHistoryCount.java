package org.nahulemes.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import org.nahulemes.ui.UserOrdersPageUI;

@Subject("the number of order items visible in the history page")
public class OrderHistoryCount implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        try {
            return UserOrdersPageUI.ORDER_ITEMS.resolveAllFor(actor).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static OrderHistoryCount displayed() {
        return new OrderHistoryCount();
    }
}
