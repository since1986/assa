package io.github.since1986.assa;

import lombok.Builder;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
public final class ScriptInfo {

    /**
     * This is a description of the script. If the original author(s) did not provide this information then <untitled> is automatically substituted.
     */
    private String title;

    /**
     * The original author(s) of the script. If the original author(s) did not provide this information then <unknown> is automatically substituted.
     */
    private String originalScript;

    /**
     * (optional) The original translator of the dialogue. This entry does not appear if no information was entered by the author.
     */
    private String originalTranslation;

    /**
     * (optional) The original script editor(s), typically whoever took the raw translation and turned it into idiomatic english and reworded for readability. This entry does not appear if no information was entered by the author.
     */
    private String originalEditing;

    /**
     * (optional) Whoever timed the original script. This entry does not appear if no information was entered by the author.
     */
    private String originalTiming;

    /**
     * (optional) Description of where in the video the script should begin playback. This entry does not appear if no information was entered by the author.
     */
    private String synchPoint;

    /**
     * (optional) Names of any other subtitling groups who edited the original script. This entry does not appear if subsequent editors did not enter the information.
     */
    private String scriptUpdatedBy;

    /**
     * The details of any updates to the original script - made by other subtitling groups. This entry does not appear if subsequent editors did not enter any information.
     */
    private String updateDetails;

    /**
     * This is the SSA script format version eg. "V4.00". It is used by SSA to give a warning if you are using a version of SSA older than the version that created the script.
     * ASS version is “V4.00+”.
     */
    @Builder.Default
    private String scriptType = "V4.00+";

    /**
     * This determines how subtitles are moved, when automatically preventing onscreen collisions.
     * If the entry says "Normal" then SSA will attempt to position subtitles in the position specified by the "margins". However, subtitles can be shifted vertically to prevent onscreen collisions. With "normal" collision prevention, the subtitles will "stack up" one above the other - but they will always be positioned as close the vertical (bottom) margin as possible - filling in "gaps" in other subtitles if one large enough is available.
     * If the entry says "Reverse" then subtitles will be shifted upwards to make room for subsequent overlapping subtitles. This means the subtitles can nearly always be read top-down - but it also means that the first subtitle can appear half way up the screen before the subsequent overlapping subtitles appear. It can use a lot of screen area.
     */
    private Collisions collisions;

    /**
     * This is the height of the screen used by the script's author(s) when playing the script. SSA v4 will automatically select the nearest enabled setting, if you are using Directdraw playback.
     */
    private Integer playResX;

    /**
     * This is the width of the screen used by the script's author(s) when playing the script. SSA will automatically select the nearest enabled, setting if you are using Directdraw playback.
     */
    private Integer playResY;

    /**
     * This is the colour depth used by the script's author(s) when playing the script. SSA will automatically select the nearest enabled setting if you are using Directdraw playback.
     */
    private String playDepth;

    /**
     * This is the Timer Speed for the script, as a percentage.
     * eg. "100.0000" is exactly 100%. It has four digits following the decimal point.
     * The timer speed is a time multiplier applied to SSA's clock to stretch or compress the duration of a script. A speed greater than 100% will reduce the overall duration, and means that subtitles will progressively appear sooner and sooner. A speed less than 100% will increase the overall duration of the script means subtitles will progressively appear later and later (like a positive ramp time).
     * The stretching or compressing only occurs during script playback - this value does not change the actual timings for each event listed in the script.
     * Check the SSA user guide if you want to know why "Timer Speed" is more powerful than "Ramp Time", even though they both achieve the same result.
     */
    private Float timer;

    /**
     * Defines the default wrapping style.
     * 0: smart wrapping, lines are evenly broken
     * 1: end-of-line word wrapping, only \N breaks
     * 2: no word wrapping, \n \N both breaks
     * 3: same as 0, but lower line gets wider.
     */
    @Builder.Default
    private WrapStyle wrapStyle = WrapStyle.SMART;

    @Override
    public String toString() {
        var fields = List.of(
                new NamedField<>("Title", title, "This is a description of the script. If the original author(s) did not provide this information then <untitled> is automatically substituted."),
                new NamedField<>("OriginalScript", originalScript, "The original author(s) of the script. If the original author(s) did not provide this information then <unknown> is automatically substituted."),
                new NamedField<>("OriginalTranslation", originalTranslation, "(optional) The original translator of the dialogue. This entry does not appear if no information was entered by the author."),
                new NamedField<>("OriginalEditing", originalEditing, "(optional) The original script editor(s), typically whoever took the raw translation and turned it into idiomatic english and reworded for readability.", "This entry does not appear if no information was entered by the author."),
                new NamedField<>("OriginalTiming", originalTiming, "(optional) Whoever timed the original script. This entry does not appear if no information was entered by the author."),
                new NamedField<>("SynchPoint", synchPoint, "(optional) Description of where in the video the script should begin playback.", "This entry does not appear if no information was entered by the author."),
                new NamedField<>("ScriptUpdatedBy", scriptUpdatedBy, "(optional) Names of any other subtitling groups who edited the original script.", "This entry does not appear if subsequent editors did not enter the information."),
                new NamedField<>("UpdateDetails", updateDetails, "The details of any updates to the original script - made by other subtitling groups.", "This entry does not appear if subsequent editors did not enter any information."),
                new NamedField<>("ScriptType", scriptType, "This is the SSA script format version eg. \"V4.00\".", "It is used by SSA to give a warning if you are using a version of SSA older than the version that created the script.", "ASS version is “V4.00+”."),
                new NamedField<>("Collisions", collisions, "This determines how subtitles are moved, when automatically preventing onscreen collisions.", "If the entry says \"Normal\" then SSA will attempt to position subtitles in the position specified by the \"margins\". However, subtitles can be shifted vertically to prevent onscreen collisions. With \"normal\" collision prevention, the subtitles will \"stack up\" one above the other - but they will always be positioned as close the vertical (bottom) margin as possible - filling in \"gaps\" in other subtitles if one large enough is available.", "If the entry says \"Reverse\" then subtitles will be shifted upwards to make room for subsequent overlapping subtitles. This means the subtitles can nearly always be read top-down - but it also means that the first subtitle can appear half way up the screen before the subsequent overlapping subtitles appear. It can use a lot of screen area."),
                new NamedField<>("PlayResX", playResX, "This is the height of the screen used by the script's author(s) when playing the script.", "SSA v4 will automatically select the nearest enabled setting, if you are using Directdraw playback."),
                new NamedField<>("PlayResY", playResY, "This is the width of the screen used by the script's author(s) when playing the script.", "SSA will automatically select the nearest enabled, setting if you are using Directdraw playback."),
                new NamedField<>("PlayDepth", playDepth, "This is the colour depth used by the script's author(s) when playing the script. SSA will automatically select the nearest enabled setting if you are using Directdraw playback."),
                new NamedField<>("Timer", Optional.ofNullable(timer).map(value -> new DecimalFormat("#.####").format(value)).orElse(null), "This is the Timer Speed for the script, as a percentage. eg. \"100.0000\" is exactly 100%. It has four digits following the decimal point.", "The timer speed is a time multiplier applied to SSA's clock to stretch or compress the duration of a script.", "A speed greater than 100% will reduce the overall duration, and means that subtitles will progressively appear sooner and sooner.", "A speed less than 100% will increase the overall duration of the script means subtitles will progressively appear later and later (like a positive ramp time).", "The stretching or compressing only occurs during script playback - this value does not change the actual timings for each event listed in the script.", "Check the SSA user guide if you want to know why \"Timer Speed\" is more powerful than \"Ramp Time\", even though they both achieve the same result."),
                new NamedField<>("WrapStyle", wrapStyle, "Defines the default wrapping style.", "0: smart wrapping, lines are evenly broken", "1: end-of-line word wrapping, only \\N breaks", "2: no word wrapping, \\n \\N both breaks", "3: same as 0, but lower line gets wider.")
        );
        return """
                [Script Info]
                %s
                """.formatted(fields.stream().map(NamedField::toString).filter(item -> !item.isEmpty()).collect(Collectors.joining(";\n")));
    }

    public enum Collisions {
        Normal,

        Reverse,

        UnDefined {
            @Override
            public String toString() {
                return "";
            }
        }
    }

    public enum WrapStyle implements IntValueBased {
        SMART {
            @Override
            public int intValue() {
                return 0;
            }
        },
        END_OF_LINE {
            @Override
            public int intValue() {
                return 1;
            }
        },
        NO {
            @Override
            public int intValue() {
                return 2;
            }
        },
        LOWER_LINE_GETS_WIDER {
            @Override
            public int intValue() {
                return 3;
            }
        };

        @Override
        public String toString() {
            return String.valueOf(intValue());
        }
    }
}
