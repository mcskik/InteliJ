package com.syntax.objects;

import com.syntax.common.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor vehicle class.
 * <p>
 * The most generic of all the motor vehicle classes.
 */
public class MotorVehicle {

    //region Enumerations.
    public enum LicenceEnum {
        Car,
        MotorCycle,
        PSV,
        HGV
    }

    public enum ColorEnum {
        Red,
        Blue,
        Green
    }
    //endregion

    //region Member Variables.
    protected LicenceEnum _licence;
    //endregion

    //region Properties.
    public LicenceEnum getLicence() {
        return _licence;
    }

    public String Make = Constants.EMPTY_STRING;
    public String Model = Constants.EMPTY_STRING;
    public ColorEnum Color = ColorEnum.Red;
    public int EngineCC = Constants.ZERO;
    public int NumberOfSeats = Constants.ZERO;
    public int NumberOfWheels = Constants.ZERO;
    public double MaximumSpeed_MPH = Constants.ZERO;
    public double FuelEfficiency_MPG = Constants.ZERO;
    public double FuelTankCapacity_Gallons = Constants.ZERO;
    public double Length_M = Constants.ZERO;
    public double Width_M = Constants.ZERO;
    public double Height_M = Constants.ZERO;
    public double Weight_KG = Constants.ZERO;
    public List<ComponentPart> ComponentsParts = new ArrayList<ComponentPart>();
    //endregion

    //region Constructors.
    public MotorVehicle() {
    }

    public MotorVehicle(String make, String model, ColorEnum color, int engineCC, int numberOfSeats, int numberOfWheels, double maximumSpeed_MPH, double fuelEfficiency_MPG, double fuelTankCapacity_Gallons, double length_M, double width_M, double height_M, double weight_KG) {
        Make = make;
        Model = model;
        Color = color;
        EngineCC = engineCC;
        NumberOfSeats = numberOfSeats;
        NumberOfWheels = numberOfWheels;
        MaximumSpeed_MPH = maximumSpeed_MPH;
        FuelEfficiency_MPG = fuelEfficiency_MPG;
        FuelTankCapacity_Gallons = fuelTankCapacity_Gallons;
        Length_M = length_M;
        Width_M = width_M;
        Height_M = height_M;
        Weight_KG = weight_KG;
    }
    //endregion
}
