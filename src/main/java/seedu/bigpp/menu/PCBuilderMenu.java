package seedu.bigpp.menu;

import seedu.bigpp.datastorage.DataStorage;

import static seedu.bigpp.ui.UI.out;

public class PCBuilderMenu extends Menu {

    private int pcIndex;

    public PCBuilderMenu(int pcIndex) {
        this.pcIndex = pcIndex;
    }

    public int getPCIndex() {
        return pcIndex;
    }

    public void printMenu(DataStorage dataStorage) {
        out.println("PC builder");
        out.println(dataStorage.pcList.get(pcIndex).viewComponents());
        out.println("What would you like to do?");
        out.println("Edit name");
        out.println("Set budget");
        out.println("List components");
        out.println("Select components");
        out.println("back");
    }
}
