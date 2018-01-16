package code.matthew.psc.utils.strings;

/**
 * A utility  class for strings
 */
public class StringUtil {

    /**
     * Remove the last character from a strong
     *
     * @param str The string to edit
     * @return The string provided, minus the last character
     */
    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
