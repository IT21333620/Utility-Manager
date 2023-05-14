package com.example.utilitymanager

class CalculateWater {

    fun calculateTotalWaterConsumed(drinking:Double,showering:Double,washing:Double,gardening:Double,other:Double):Double{
        return drinking+showering+washing+gardening+other
    }

    fun calculateTotalWaterUnit(totalWaterConsumed:Double):Double{
        return totalWaterConsumed/1000.00
    }

    fun calculateWeeklyCost(totalWaterUnit: Double): Double {
        return when {
            totalWaterUnit in 0.0..5.0 -> totalWaterUnit * 8.0
            totalWaterUnit in 6.0..10.0 -> totalWaterUnit * 11.0
            totalWaterUnit in 11.0..15.0 -> totalWaterUnit * 20.0
            totalWaterUnit in 16.0..20.0 -> totalWaterUnit * 40.0
            totalWaterUnit in 21.0..25.0 -> totalWaterUnit * 58.0
            totalWaterUnit in 26.0..30.0 -> totalWaterUnit * 88.0
            totalWaterUnit in 31.0..40.0 -> totalWaterUnit * 105.0
            totalWaterUnit in 41.0..50.0 -> totalWaterUnit * 120.0
            totalWaterUnit in 51.0..75.0 -> totalWaterUnit * 130.0
            else -> totalWaterUnit * 140.0
        }
    }
}