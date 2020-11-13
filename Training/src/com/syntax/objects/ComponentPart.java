package com.syntax.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Component part class.
 * <p>
 * Any manufacturing item which contains sub components.
 */
public class ComponentPart extends ComponentItem {

    //region Properties.
    public List<ComponentItem> SubComponents = new ArrayList<ComponentItem>();
    //endregion

    //region Constructors.
    public ComponentPart() {
        SubComponents = new ArrayList<ComponentItem>();
    }

    public ComponentPart(String name, String description, double length_M, double width_M, double height_M, double weight_M) {
        super(name, description, length_M, width_M, height_M, weight_M);
    }
    //endregion

    //region Public Methods.
    public void Add(ComponentItem subComponent) {
        SubComponents.add(subComponent);
    }
    //endregion
}
