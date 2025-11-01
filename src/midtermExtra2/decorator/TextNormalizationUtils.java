package midtermExtra2.decorator;

import java.util.regex.Pattern;

public final class TextNormalizationUtils {
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\p{Punct}\\p{Pd}]");

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    private TextNormalizationUtils() {
    }

    public static String collapseWhitespace(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        return WHITESPACE_PATTERN.matcher(s).replaceAll(" ").trim();
    }

    public static String toLowerCase(String s) {
        return (s == null || s.isEmpty()) ? s : s.toLowerCase();
    }

    public static String stripPunctuation(String s) {
        return (s == null || s.isEmpty()) ? s : PUNCTUATION_PATTERN.matcher(s).replaceAll("");
    }

    public static String stripEmoji(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return s.codePoints()
                .filter(cp -> !isEmoji(cp))
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    private static boolean isEmoji(int cp) {
        if ((cp >= 0x1F600 && cp <= 0x1F64F) ||
                (cp >= 0x1F300 && cp <= 0x1F5FF) ||
                (cp >= 0x1F680 && cp <= 0x1F6FF) ||
                (cp >= 0x2600 && cp <= 0x26FF) ||
                (cp >= 0x2700 && cp <= 0x27BF) ||
                (cp >= 0x1F900 && cp <= 0x1F9FF) ||
                (cp >= 0x1FA70 && cp <= 0x1FAFF)) {
            return true;
        }
        if (cp == 0xFE0F || cp == 0x200D) {
            return true;
        }
        return false;

    }

}