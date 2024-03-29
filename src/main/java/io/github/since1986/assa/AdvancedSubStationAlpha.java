package io.github.since1986.assa;

import java.util.Optional;

public record AdvancedSubStationAlpha(ScriptInfo scriptInfo, Style style, Event event) {

    @Override
    public String toString() {
        return """
                %s
                %s
                %s
                """.formatted(scriptInfo, style, Optional.ofNullable(event).map(Event::toString).orElse(""));
    }
}
