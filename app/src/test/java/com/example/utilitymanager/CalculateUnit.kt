package com.example.utilitymanager

class CalculateUnit {
    fun testCalculateUnit(wattsStr:String?,itemsStr: String?, hoursStr: String?):String{
        val watts = wattsStr!!.toDoubleOrNull() ?: return "Invalid Watts" // convert to Double
        val items = itemsStr!!.toIntOrNull() ?: return "Invalid Items" // convert to Int
        val hours = hoursStr!!.toIntOrNull() ?: return "Invalid Hours" // convert to Int
        val unitPrice = (watts * hours * items) / 1000.0 // calculate unit price
        return String.format("%.2f/unit", unitPrice) // format and return unit price string
    }

}