package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerInvalidTypeException;
import seedu.bigpp.pc.PC;

public class ViewerViewCommand extends Command {

    public ViewerViewCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Display all the components of the PC of a given index
     * @return the components of the PC of that index
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws ViewerMissingIndexException,
            ViewerInvalidTypeException, PPIndexOutOfBoundsException {

        String argument = super.getArguments();

        // throw exception if no index is selected
        if (argument.equals("")) {
            throw new ViewerMissingIndexException();
        }

        // throw exception if index selected is not an integer
        if (argument.matches(".*\\D.*")) {
            throw new ViewerInvalidTypeException();
        }

        int pcIndex = Integer.parseInt(argument) - 1;
        // throw exception if index selected is out of the PCList range
        if (pcIndex < 0 || pcIndex >= dataStorage.pcList.size()) {
            throw new PPIndexOutOfBoundsException();
        }

        PC pc = (dataStorage.pcList).get(pcIndex);
        return pc.viewComponents();
    }
}
