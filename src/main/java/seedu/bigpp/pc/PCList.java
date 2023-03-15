package seedu.bigpp.pc;

import java.util.ArrayList;

import static seedu.bigpp.ui.UI.out;

public class PCList extends ArrayList<PC> {

    public void printPcList() {
        if (this.size() > 0) {
            out.println("Here is the list of PC Builds:");
            for (int i = 0; i < this.size(); i++) {
                out.println((i + 1) + "." + this.get(i));
            }
        } else {
            out.println("List currently empty!");
        }
    }
}
