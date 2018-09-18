package org.hacking.nessieproj

import android.databinding.InverseMethod


object Converter{

    fun convertStringToInt(value: String): Int {
        return try {
            Integer.parseInt(value)
        } catch (e: NumberFormatException) {
            -1
        }

    }

    @InverseMethod("convertStringToInt")
    fun convertIntToString(value: Int): String {
        return value.toString()
    }
}