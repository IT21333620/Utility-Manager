package com.example.utilitymanager

import com.example.utilitymanager.CalculateWater
import org.junit.Assert.assertEquals
import org.junit.Test


class WaterUnitTest {

    @Test
    fun testCalculateTotalWaterConsumed_isCorrect(){

        val calculator = CalculateWater()
        val totalWaterConsumed = calculator.calculateTotalWaterConsumed(1000.0,550.0,250.0,100.0,60.0)

        assertEquals(1960.0, totalWaterConsumed, 0.0)

    }

    @Test
    fun testCalculateTotalWaterUnit_isCorrect(){

        val calculator = CalculateWater()
        val totalWaterUnit = calculator.calculateTotalWaterUnit(1960.0)

        assertEquals(1.96, totalWaterUnit, 0.0)

    }

    @Test
    fun testCalculateWeeklyCost_isCorrect(){

        val calculator = CalculateWater()
        val weeklyCost =  calculator.calculateWeeklyCost(1.96)

        assertEquals(15.68, weeklyCost, 0.0)
    }



}