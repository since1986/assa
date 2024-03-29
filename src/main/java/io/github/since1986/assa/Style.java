package io.github.since1986.assa;

import lombok.Builder;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public final class Style {

    /**
     * The name of the Style. Case sensitive. Cannot include commas.
     */
    @Builder.Default
    private String name = "Default";

    /**
     * The fontname as used by Windows. Case-sensitive.
     */
    @Builder.Default
    private String fontname = "Arial";

    @Builder.Default
    private int fontsize = 20;

    /**
     * A long integer BGR (blue-green-red)  value. ie. the byte order in the hexadecimal equivelent of this number is BBGGRR
     * This is the colour that a subtitle will normally appear in.
     */
    @Builder.Default
    private String primaryColour = "&H00FFFFFF";

    /**
     * A long integer BGR (blue-green-red)  value. ie. the byte order in the hexadecimal equivelent of this number is BBGGRR
     * This colour may be used instead of the Primary colour when a subtitle is automatically shifted to prevent an onscreen collsion, to distinguish the different subtitles.
     */
    @Builder.Default
    private String secondaryColour = "&H000000FF";

    /**
     * A long integer BGR (blue-green-red)  value. ie. the byte order in the hexadecimal equivelent of this number is BBGGRR
     * This colour may be used instead of the Primary or Secondary colour when a subtitle is automatically shifted to prevent an onscreen collsion, to distinguish the different subtitles.
     */
    @Builder.Default
    private String outlineColour = "&H00000000";

    /**
     * This is the colour of the subtitle outline or shadow, if these are used. A long integer BGR (blue-green-red)  value. ie. the byte order in the hexadecimal equivelent of this number is BBGGRR.
     * The color format contains the alpha channel, too. (AABBGGRR)
     */
    @Builder.Default
    private String backColour = "&H00000000";

    /**
     * This defines whether text is bold (true) or not (false). -1 is True, 0 is False. This is independant of the Italic attribute - you can have have text which is both bold and italic.
     */
    @Builder.Default
    private Boolean bold = false;

    /**
     * This defines whether text is italic (true) or not (false). -1 is True, 0 is False. This is independant of the bold attribute - you can have have text which is both bold and italic.
     */
    @Builder.Default
    private Boolean italic = false;

    /**
     * [-1 or 0]
     */
    @Builder.Default
    private Boolean underline = false;

    /**
     * [-1 or 0]
     */
    @Builder.Default
    private Boolean strikeOut = false;

    /**
     * ScaleX. Modifies the width of the font. [percent]
     */
    @Builder.Default
    private Integer scaleX = 100;

    /**
     * ScaleY. Modifies the height of the font. [percent]
     */
    @Builder.Default
    private Integer scaleY = 100;

    /**
     * Spacing. Extra space between characters. [pixels]
     */
    @Builder.Default
    private Integer spacing = 0;

    /**
     * The origin of the rotation is defined by the alignment. Can be a floating point number. [degrees]
     */
    @Builder.Default
    private Float angle = 0f;

    /**
     * 1=Outline + drop shadow, 3=Opaque box
     */
    @Builder.Default
    private BorderStyle borderStyle = BorderStyle.OUTLINE_AND_DROP_SHADOW;
    /**
     * If BorderStyle is 1,  then this specifies the width of the outline around the text, in pixels.
     * Values may be 0, 1, 2, 3 or 4.
     */
    @Builder.Default
    private Integer outline = 2;
    /**
     * If BorderStyle is 1,  then this specifies the depth of the drop shadow behind the text, in pixels. Values may be 0, 1, 2, 3 or 4. Drop shadow is always used in addition to an outline - SSA will force an outline of 1 pixel if no outline width is given.
     */
    @Builder.Default
    private Integer shadow = 2;
    /**
     * This sets how text is "justified" within the Left/Right onscreen margins, and also the vertical placing. Values may be 1=Left, 2=Centered, 3=Right. Add 4 to the value for a "Toptitle". Add 8 to the value for a "Midtitle".
     * eg. 5 = left-justified toptitle
     * <br>
     * <br>
     * TL;DR:
     * <pre>
     * 7 8 9
     * 4 5 6
     * 1 2 3
     * </pre>
     */
    @Builder.Default
    private Alignment alignment = Alignment.MID_BOTTOM;
    /**
     * This defines the Left Margin in pixels. It is the distance from the left-hand edge of the screen.The three onscreen margins (MarginL, MarginR, MarginV) define areas in which the subtitle text will be displayed.
     */
    @Builder.Default
    private Integer marginL = 10;
    /**
     * This defines the Right Margin in pixels. It is the distance from the right-hand edge of the screen. The three onscreen margins (MarginL, MarginR, MarginV) define areas in which the subtitle text will be displayed.
     */
    @Builder.Default
    private Integer marginR = 10;
    /**
     * This defines the vertical Left Margin in pixels.
     * For a subtitle, it is the distance from the bottom of the screen.
     * For a toptitle, it is the distance from the top of the screen.
     * For a midtitle, the value is ignored - the text will be vertically centred
     */
    @Builder.Default
    private Integer marginV = 10;
    /**
     * This defines the transparency of the text. SSA does not use this yet.
     *
     * @deprecated
     */
    @Deprecated
    private Float alphaLevel;
    /**
     * This specifies the font character set or encoding and on multi-lingual Windows installations it provides access to characters used in multiple than one languages. It is usually 0 (zero) for English (Western, ANSI) Windows.
     * When the file is Unicode, this field is useful during file format conversions.
     */
    @Builder.Default
    private Integer encoding = 1;

    @Override
    public String toString() {
        var collected = Stream.of(
                new NamedField<>("Name", name),
                new NamedField<>("Fontname", fontname),
                new NamedField<>("Fontsize", fontsize),
                new NamedField<>("PrimaryColour", primaryColour),
                new NamedField<>("SecondaryColour", secondaryColour),
                new NamedField<>("OutlineColour", outlineColour),
                new NamedField<>("BackColour", backColour),
                new NamedField<>("Bold", Optional.ofNullable(bold).map(v -> v ? -1 : 0).orElse(null)),
                new NamedField<>("Italic", Optional.ofNullable(italic).map(v -> v ? -1 : 0).orElse(null)),
                new NamedField<>("Underline", Optional.ofNullable(underline).map(v -> v ? -1 : 0).orElse(null)),
                new NamedField<>("StrikeOut", Optional.ofNullable(strikeOut).map(v -> v ? -1 : 0).orElse(null)),
                new NamedField<>("ScaleX", scaleX),
                new NamedField<>("ScaleY", scaleY),
                new NamedField<>("Spacing", spacing),
                new NamedField<>("Angle", angle),
                new NamedField<>("BorderStyle", borderStyle),
                new NamedField<>("Outline", outline),
                new NamedField<>("Shadow", shadow),
                new NamedField<>("Alignment", alignment),
                new NamedField<>("MarginL", marginL),
                new NamedField<>("MarginR", marginR),
                new NamedField<>("MarginV", marginV),
                new NamedField<>("Encoding", encoding)
        ).filter(item -> !item.toString().isEmpty()).toList();

        var namesJoin = collected.stream().map(NamedField::name).collect(Collectors.joining(","));
        var valuesJoin = collected.stream().map(item -> String.valueOf(item.value())).collect(Collectors.joining(","));

        return """
                [V4+ Styles]
                Format: %s
                Style: %s
                """.formatted(namesJoin, valuesJoin);
    }

    public enum BorderStyle implements IntValueBased {
        OUTLINE_AND_DROP_SHADOW {
            @Override
            public int intValue() {
                return 1;
            }
        },
        OPAQUE_BOX {
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

    public enum Alignment implements IntValueBased {
        LEFT_TOP {
            @Override
            public int intValue() {
                return 7;
            }
        },
        MID_TOP {
            @Override
            public int intValue() {
                return 8;
            }
        },
        RIGHT_TOP {
            @Override
            public int intValue() {
                return 9;
            }
        },
        LEFT_CENTER {
            @Override
            public int intValue() {
                return 4;
            }
        },
        MID_CENTER {
            @Override
            public int intValue() {
                return 5;
            }
        },
        RIGHT_CENTER {
            @Override
            public int intValue() {
                return 6;
            }
        },
        LEFT_BOTTOM {
            @Override
            public int intValue() {
                return 1;
            }
        },
        MID_BOTTOM {
            @Override
            public int intValue() {
                return 2;
            }
        },
        RIGHT_BOTTOM {
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
