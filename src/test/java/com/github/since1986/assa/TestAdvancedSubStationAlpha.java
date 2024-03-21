package com.github.since1986.assa;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class TestAdvancedSubStationAlpha {

    @Test
    void testToString() {
        var start = LocalTime.of(0, 0, 0);
        var end = start.plus(200, ChronoUnit.MILLIS);

        var advancedSubStationAlpha = new AdvancedSubStationAlpha(
                ScriptInfo.builder().build(),
                Style.builder().build(),
                new Event(
                        Dialogue
                                .builder()
                                .start(start)
                                .end(end)
                                .build(),
                        Dialogue.builder().build())
        );
        System.out.println(advancedSubStationAlpha);
    }
}
