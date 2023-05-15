package com.example.utilitymanager

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testCalculateUnit() {
        val calculateUnit = CalculateUnit()
        assertEquals("0.36/unit", calculateUnit.testCalculateUnit("60", "2", "3"))
        assertEquals("Invalid Watts", calculateUnit.testCalculateUnit("abc", "2", "3"))
        assertEquals("Invalid Items", calculateUnit.testCalculateUnit("60", "xyz", "3"))
        assertEquals("Invalid Hours", calculateUnit.testCalculateUnit("60", "2", "xyz"))
    }

    @Test
    fun testGetTotalUnitPrice(){
        val calculateUnit = CalculateUnit()
        assertEquals(72000.0, calculateUnit.getTotalUnitPrice())
    }
}