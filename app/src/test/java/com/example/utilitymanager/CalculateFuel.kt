package com.example.utilitymanager

class CalculateFuel {

        fun calculateFuelUsed(distance: Double, fuelUsage: Double): Double {
            return distance / fuelUsage
        }

        fun calculateRemainingFuel(fuelAmount: Double, fuelUsed: Double): Double {
            return fuelAmount - fuelUsed
        }

        fun calculateTotalCost(fuelUsed: Double, fuelPrice: Double): Double {
            return fuelUsed * fuelPrice
        }

}
