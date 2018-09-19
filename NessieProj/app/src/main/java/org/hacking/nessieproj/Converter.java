package org.hacking.nessieproj;

import android.databinding.InverseMethod;

public class Converter {

    @InverseMethod("toInt")
    public static String toString(Integer value) {
        if (value == null) {
            return "";
        }
        return "" + value;
    }
    public static Integer toInt(String value) {
        return Integer.parseInt(value);
    }

}
