package io.github.since1986.assa;

import lombok.Builder;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Builder
public final class Dialogue {

    /**
     * Marked
     * Marked=0 means the line is not shown as "marked" in SSA.
     * Marked=1 means the line is shown as "marked" in SSA.
     *
     * @deprecated
     */
    @Deprecated
    @Builder.Default
    private boolean marked = false;

    /**
     * Layer (any integer)
     * Subtitles having different layer number will be ignored during the collusion detection.
     * Higher numbered layers will be drawn over the lower numbered.
     */
    @Builder.Default
    private int layer = 0;

    /**
     * Start Time of the Event, in 0:00:00:00 format ie. Hrs:Mins:Secs:hundredths. This is the time elapsed during script playback at which the text will appear onscreen. Note that there is a single digit for the hours!
     */
    private LocalTime start;

    /**
     * End Time of the Event, in 0:00:00:00 format ie. Hrs:Mins:Secs:hundredths. This is the time elapsed during script playback at which the text will disappear offscreen. Note that there is a single digit for the hours!
     */
    private LocalTime end;

    /**
     * Style name. If it is "Default", then your own *Default style will be subtituted.
     * However, the Default style used by the script author IS stored in the script even though SSA ignores it - so if you want to use it, the information is there - you could even change the Name in the Style definition line, so that it will appear in the list of "script" styles.
     */
    @Builder.Default
    private String style = "Default";

    /**
     * Character name. This is the name of the character who speaks the dialogue. It is for information only, to make the script is easier to follow when editing/timing.
     */
    @Builder.Default
    private String name = "";

    /**
     * 4-figure Left Margin override. The values are in pixels. All zeroes means the default margins defined by the style are used.
     */
    @Builder.Default
    private int marginL = 0;

    /**
     * 4-figure Right Margin override. The values are in pixels. All zeroes means the default margins defined by the style are used.
     */
    @Builder.Default
    private int marginR = 0;

    /**
     * 4-figure Bottom Margin override. The values are in pixels. All zeroes means the default margins defined by the style are used.
     */
    @Builder.Default
    private int marginV = 0;

    /**
     * Transition Effect. This is either empty, or contains information for one of the three transition effects implemented in SSA v4.x
     * The effect names are case sensitive and must appear exactly as shown. The effect names do not have quote marks around them.
     * "Karaoke" means that the text will be successively highlighted one word at a time.
     * Karaoke as an effect type is obsolete.
     * "Scroll up;y1;y2;delay[;fadeawayheight]"means that the text/picture will scroll up the screen. The parameters after the words "Scroll up" are separated by semicolons.
     * The y1 and y2 values define a vertical region on the screen in which the text will scroll. The values are in pixels, and it doesn't matter which value (top or bottom) comes first. If the values are zeroes then the text will scroll up the full height of the screen.
     * The delay value can be a number from 1 to 100, and it slows down the speed of the scrolling - zero means no delay and the scrolling will be as fast as possible.
     * “Banner;delay” means that text will be forced into a single line, regardless of length, and scrolled from right to left accross the screen.
     * The delay value can be a number from 1 to 100, and it slows down the speed of the scrolling - zero means no delay and the scrolling will be as fast as possible.
     * "Scroll down;y1;y2;delay[;fadeawayheight]"
     * “Banner;delay[;lefttoright;fadeawaywidth]”
     * lefttoright 0 or 1. This field is optional.  Default value is 0 to make it backwards compatible.
     * When delay is greater than 0, moving one pixel will take (1000/delay) second.
     * (WARNING: Avery Lee’s “subtitler” plugin reads the “Scroll up” effect parameters as delay;y1;y2)
     * fadeawayheight and fadeawaywidth parameters can be used to make the scrolling text at the sides transparent.
     */
    @Builder.Default
    private Effect effect = new Effect.Undefined();

    /**
     * Subtitle Text. This is the actual text which will be displayed as a subtitle onscreen. Everything after the 9th comma is treated as the subtitle text, so it can include commas.
     * The text can include \n codes which is a line break, and can include Style Override control codes, which appear between braces { }.
     */
    @Builder.Default
    private String tex = "";

    @Override
    public String toString() {
        var formatter = DateTimeFormatter.ofPattern("H:mm:ss.SS");

        return "Dialogue: %d,%s,%s,%s,%s,%d,%d,%d,%s,%s".formatted(
                layer,
                Optional.ofNullable(start).map(v -> v.format(formatter)).orElse(""),
                Optional.ofNullable(end).map(v -> v.format(formatter)).orElse(""),
                style,
                name,
                marginL,
                marginR,
                marginV,
                effect,
                tex
        );
    }

    public interface Effect {
        final class Karaoke implements Effect {
            @Override
            public String toString() {
                return "Karaoke";
            }
        }

        record ScrollUp(int y1, int y2, int delay, Integer fadeAwayHeight) {
            @Override
            public String toString() {
                if (fadeAwayHeight == null) {
                    return "Scroll up;%d;%d;%d".formatted(y1, y2, delay);
                }
                return "Scroll up;%d;%d;%d;%d".formatted(y1, y2, delay, fadeAwayHeight);
            }
        }

        record ScrollDown(int y1, int y2, int delay, Integer fadeAwayHeight) {
            @Override
            public String toString() {
                if (fadeAwayHeight == null) {
                    return "Scroll down;%d;%d;%d".formatted(y1, y2, delay);
                }
                return "Scroll down;%d;%d;%d;%d".formatted(y1, y2, delay, fadeAwayHeight);
            }
        }

        record Banner(int delay, boolean leftToRight, Integer fadeAwayWidth) {
            @Override
            public String toString() {
                if (fadeAwayWidth == null) {
                    return "Banner;%d;%d".formatted(delay, leftToRight ? 1 : 0);
                }
                return "Banner;%d;%d;%d".formatted(delay, leftToRight ? 1 : 0, fadeAwayWidth);
            }
        }

        final class Undefined implements Effect {

            @Override
            public String toString() {
                return "";
            }
        }
    }
}
