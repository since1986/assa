package io.github.since1986.assa;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public record Event(Dialogue... items) {

    @Override
    public String toString() {
        var collected = Arrays.stream(Optional.ofNullable(items).orElse(new Dialogue[]{}))
                .map(Dialogue::toString)
                .collect(Collectors.joining("\n"));

        return """
                [Events]
                Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
                %s
                """.formatted(collected);
    }
}
