package com.greencity.auto.steps.events;

import com.greencity.auto.steps.common.BaseSteps;

public class EventSteps extends BaseSteps<EventSteps> {
    private final EventAssertion assertion = new EventAssertion(this);

    public EventSteps() {
        super();
    }

    /**
     * A method used to begin assertion
     * @return EcoNewsAssertion object
     */
    @Override
    public EventAssertion assertThat() {
        return assertion;
    }
}
