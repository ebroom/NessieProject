package org.hacking.nessieproj

import android.databinding.InverseMethod

class Converter{

    @InverseMethod("toInt")
    fun toString(value: Int): String {
        return "" + value
    }

    fun toInt(value: String): Int {
        return Integer.parseInt(value)
    }
}