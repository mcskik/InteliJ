package com.syntax.objects;

import com.syntax.common.constants.Constants;

/**
 * Car class.
 * <p>
 * This class represents a car.
 */
public class Car extends PrivateVehicle {

    //region Properties.
    public int NumberOfDoors = Constants.ZERO;
    //endregion

    //region Constructors.
    public Car() {
    }

    public Car(String make, String model, ColorEnum color, int engineCC, int numberOfSeats, int numberOfWheels, double maximumSpeed_MPH, double fuelEfficiency_MPG, double fuelTankCapacity_Gallons, double length_M, double width_M, double height_M, double weight_KG, int numberOfDoors) {
        super(make, model, color, engineCC, numberOfSeats, numberOfWheels, maximumSpeed_MPH, fuelEfficiency_MPG, fuelTankCapacity_Gallons, length_M, width_M, height_M, weight_KG);
        {
            NumberOfDoors = numberOfDoors;
        }
        //endregion
    }
}
