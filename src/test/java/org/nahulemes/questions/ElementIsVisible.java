package org.nahulemes.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.targets.Target;

@Subject("whether the element is visible on the page")
public class ElementIsVisible implements Question<Boolean> {

    private final Target target;

    public ElementIsVisible(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return target.resolveFor(actor).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static ElementIsVisible of(Target target) {
        return new ElementIsVisible(target);
    }
}
