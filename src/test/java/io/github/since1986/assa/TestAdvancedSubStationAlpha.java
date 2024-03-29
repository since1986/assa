package io.github.since1986.assa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

class TestAdvancedSubStationAlpha {

    @Test
    void testToString() {
        var start = LocalTime.of(0, 0, 0);

        var dialogues = new ArrayList<Dialogue>();
        for (int i = 0; i < 5; i++) {
            var end = start.plus(200, ChronoUnit.MILLIS);
            var payload = "test-item-%s\\N第(%s)个条目的一行文字\\N第(%s)个条目的又一行文字".formatted(
                    i,
                    i,
                    i
            );
            var dialogue = Dialogue.builder()
                    .start(start)
                    .end(end)
                    .tex(payload)
                    .build();

            dialogues.add(dialogue);
            start = end;
        }

        var advancedSubStationAlpha = new AdvancedSubStationAlpha(
                ScriptInfo.builder().build(),
                Style.builder()
                        .fontsize(12)
                        .primaryColour("&H000000FF")
                        .alignment(Style.Alignment.RIGHT_BOTTOM)
                        .build(),
                new Event(dialogues.toArray(new Dialogue[]{}))
        );

        var expected = """
                [Script Info]
                ; This is the SSA script format version eg. "V4.00".
                ; It is used by SSA to give a warning if you are using a version of SSA older than the version that created the script.
                ; ASS version is “V4.00+”.
                ScriptType: V4.00+
                ;
                ; Defines the default wrapping style.
                ; 0: smart wrapping, lines are evenly broken
                ; 1: end-of-line word wrapping, only \\N breaks
                ; 2: no word wrapping, \\n \\N both breaks
                ; 3: same as 0, but lower line gets wider.
                WrapStyle: 0
                                
                                
                [V4+ Styles]
                Format: Name,Fontname,Fontsize,PrimaryColour,SecondaryColour,OutlineColour,BackColour,Bold,Italic,Underline,StrikeOut,ScaleX,ScaleY,Spacing,Angle,BorderStyle,Outline,Shadow,Alignment,MarginL,MarginR,MarginV,Encoding
                Style: Default,Arial,12,&H000000FF,&H000000FF,&H00000000,&H00000000,0,0,0,0,100,100,0,0.0,1,2,2,3,10,10,10,1
                                
                [Events]
                Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
                Dialogue: 0,0:00:00.00,0:00:00.20,Default,,0,0,0,,test-item-0\\N第(0)个条目的一行文字\\N第(0)个条目的又一行文字
                Dialogue: 0,0:00:00.20,0:00:00.40,Default,,0,0,0,,test-item-1\\N第(1)个条目的一行文字\\N第(1)个条目的又一行文字
                Dialogue: 0,0:00:00.40,0:00:00.60,Default,,0,0,0,,test-item-2\\N第(2)个条目的一行文字\\N第(2)个条目的又一行文字
                Dialogue: 0,0:00:00.60,0:00:00.80,Default,,0,0,0,,test-item-3\\N第(3)个条目的一行文字\\N第(3)个条目的又一行文字
                Dialogue: 0,0:00:00.80,0:00:01.00,Default,,0,0,0,,test-item-4\\N第(4)个条目的一行文字\\N第(4)个条目的又一行文字
                                
                """;
        Assertions.assertEquals(advancedSubStationAlpha.toString(), expected);
        //Files.writeString(Path.of("input.ass"), advancedSubStationAlpha.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
