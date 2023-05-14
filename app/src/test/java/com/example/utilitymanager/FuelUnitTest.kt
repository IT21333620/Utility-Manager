import com.example.utilitymanager.CalculateFuel
import org.junit.Assert.assertEquals
import org.junit.Test

class FuelUnitTest {

    @Test
    fun testCalculateFuelUsed_isCorrect() {

        val calculator = CalculateFuel()
        val fuelUsed = calculator.calculateFuelUsed(100.0,10.0)

        assertEquals(10.0, fuelUsed, 0.0)
    }

    @Test
    fun testCalculateRemainingFuel_isCorrect() {

        val calculator = CalculateFuel()
        val remainingFuel = calculator.calculateRemainingFuel(50.0, 10.0)

        assertEquals(40.0, remainingFuel, 0.0)
    }

    @Test
    fun testCalculateTotalCost_isCorrect() {

        val calculator = CalculateFuel()
        val totalCost = calculator.calculateTotalCost(10.0, 3.0)

        assertEquals(30.0, totalCost, 0.0)
    }

}



