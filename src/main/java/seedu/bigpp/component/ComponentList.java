package seedu.bigpp.component;

import java.util.ArrayList;

public class ComponentList<T> extends ArrayList<Component> {

    public String getListString() {
        String outputString = "";

        for (int i = 0; i < super.size(); i++) {
            outputString += (i + 1) + "." + "\n" + super.get(i).toString() + "\n" + "================\n";
        }

        return outputString;
    }
}
