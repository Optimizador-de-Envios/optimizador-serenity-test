package org.nahulemes.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import org.nahulemes.ui.OrderFormUI;

@Subject("whether any autocomplete suggestion contains the expected country")
public class AutocompleteSuggestionsContainCountry implements Question<Boolean> {

    private final String country;

    public AutocompleteSuggestionsContainCountry(String country) {
        this.country = country;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            var items = OrderFormUI.ORIGIN_SUGGESTIONS.resolveAllFor(actor);
            return items.stream()
                    .anyMatch(el -> el.getText().contains(country));
        } catch (Exception e) {
            return false;
        }
    }

    public static AutocompleteSuggestionsContainCountry named(String country) {
        return new AutocompleteSuggestionsContainCountry(country);
    }
}
