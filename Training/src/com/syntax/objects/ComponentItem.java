package com.syntax.objects;

import com.syntax.common.constants.Constants;

/**
 * Component item class.
 * <p>
 * Characteristics common to all components.
 */
public class ComponentItem {

    //region Properties.
    public String Name = Constants.EMPTY_STRING;
    public String Description = Constants.EMPTY_STRING;
    public double Length_M = Constants.ZERO;
    public double Width_M = Constants.ZERO;
    public double Height_M = Constants.ZERO;
    public double Weight_M = Constants.ZERO;
    //endregion

    //region Constructors.
    public ComponentItem() {
    }

    public ComponentItem(String name, String description, double length_M, double width_M, double height_M, double weight_M) {
        Name = name;
        Description = description;
        Length_M = length_M;
        Width_M = width_M;
        Height_M = height_M;
        Weight_M = weight_M;
    }
    //endregion
}
