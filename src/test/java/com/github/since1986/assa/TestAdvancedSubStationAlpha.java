package com.github.since1986.assa;

import org.junit.jupiter.api.Test;

class TestAdvancedSubStationAlpha {

    @Test
    void testToString() {
        var advancedSubStationAlpha = new AdvancedSubStationAlpha(
                ScriptInfo.builder().build(),
                Style.builder().build(),
                new Event(Dialogue.builder().build())
        );
        System.out.println(advancedSubStationAlpha);
    }
}
