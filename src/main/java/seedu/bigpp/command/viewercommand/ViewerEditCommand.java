package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerInvalidTypeException;
import seedu.bigpp.ui.UI;

public class ViewerEditCommand extends Command {
    public ViewerEditCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Edit a PC of the given index from the PC list
     * @return the name of the PC created
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws ViewerMissingIndexException, ViewerInvalidTypeException,
            PPIndexOutOfBoundsException {
        String argument = super.getArguments();

        // throw exception if no index is selected
        if (argument.equals("")) {
            throw new ViewerMissingIndexException();
        }

        if (argument.matches(".*\\D.*")) {
            throw new ViewerInvalidTypeException();
        }

        int pcIndex = Integer.parseInt(argument) - 1;

        // throw exception if index selected is out of the PCList range
        if (pcIndex < 0 || pcIndex >= dataStorage.pcList.size()) {
            throw new PPIndexOutOfBoundsException();
        }

        UI.setPCBuilderMode(pcIndex);
        return "Currently editing PC: " + dataStorage.pcList.get(pcIndex).getName();
    }
}
