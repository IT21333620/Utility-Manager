package com.example.utilitymanager

import java.math.BigDecimal

class CalculateUnit {
    fun testCalculateUnit(wattsStr:String?,itemsStr: String?, hoursStr: String?):String{
        val watts = wattsStr!!.toDoubleOrNull() ?: return "Invalid Watts" // convert to Double
        val items = itemsStr!!.toIntOrNull() ?: return "Invalid Items" // convert to Int
        val hours = hoursStr!!.toIntOrNull() ?: return "Invalid Hours" // convert to Int
        val unitPrice = (watts * hours * items) / 1000.0 // calculate unit price
        return String.format("%.2f/unit", unitPrice) // format and return unit price string
    }

    class Item(val watts: String, val number: String, val hours: String)

    val itemList = listOf(
        Item("60", "2", "4"),  // Total price for this item: (60 * 2 * 4) * 30 = 14,400
        Item("75", "3", "5"),  // Total price for this item: (75 * 3 * 5) * 30 = 33,750
        Item("100", "1", "8")  // Total price for this item: (100 * 1 * 8) * 30 = 24,000
    )
    fun getTotalUnitPrice(): Double {
        var totalUnitPrice = 0.0
        for (item in itemList) {
            val totPrice = testCalculateUnit(item.watts, item.number, item.hours)
            val totalItemPrice = try {
                BigDecimal(totPrice).multiply(BigDecimal(30)).toDouble()
            } catch (e: NumberFormatException) {
                // handle invalid input, e.g. return 0.0 or throw an exception
                0.0
            }
            totalUnitPrice += totalItemPrice
        }
        return totalUnitPrice
    }


}

