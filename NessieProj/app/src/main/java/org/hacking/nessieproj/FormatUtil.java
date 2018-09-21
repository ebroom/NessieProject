package org.hacking.nessieproj;

public class FormatUtil {
    public static String formatAccountName(String format, String name, String number) {
        int length = number.length();
        String str = String.format(format, name, number.substring(length-4));
        return str;
    }
}
