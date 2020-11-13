package com.syntax.objects;

/**
 * Privately owned vehicle class.
 * <p>
 * Vehicles which are used by private owners for non-commercial purposes.
 */
public class PrivateVehicle extends MotorVehicle {

    //region Constructors.
    public PrivateVehicle() {
    }

    public PrivateVehicle(String make, String model, ColorEnum color, int engineCC, int numberOfSeats, int numberOfWheels, double maximumSpeed_MPH, double fuelEfficiency_MPG, double fuelTankCapacity_Gallons, double length_M, double width_M, double height_M, double weight_KG) {
        super(make, model, color, engineCC, numberOfSeats, numberOfWheels, maximumSpeed_MPH, fuelEfficiency_MPG, fuelTankCapacity_Gallons, length_M, width_M, height_M, weight_KG);
    }
    //endregion
}
