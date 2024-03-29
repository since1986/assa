package io.github.since1986.assa;

import java.util.Arrays;
import java.util.stream.Collectors;

record NamedField<T>(String name, T value, String... comments) {

    @Override
    public String toString() {
        return value == null ? "" : comments == null || comments.length == 0 ? "%s: %s".formatted(name, value) : """
                %s
                %s: %s
                """.formatted(Arrays.stream(comments).map(item -> "; " + item).collect(Collectors.joining("\n")), name, value);
    }
}
