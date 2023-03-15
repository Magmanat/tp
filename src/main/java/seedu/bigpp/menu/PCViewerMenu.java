package seedu.bigpp.menu;

import static seedu.bigpp.ui.UI.out;

import seedu.bigpp.datastorage.DataStorage;

public class PCViewerMenu extends Menu {

    public void printMenu(DataStorage dataStorage) {
        out.println("PC viewer");
        dataStorage.pcList.printPcList();
        out.println("What would you like to do?");
        out.println("Add PC");
        out.println("View PC");
        out.println("Edit PC");
        out.println("Delete PC");
        out.println("back");
        out.println("exit");
    }
}
