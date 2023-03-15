package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerInvalidTypeException;
import seedu.bigpp.pc.PC;

public class ViewerDeleteCommand extends Command {

    public ViewerDeleteCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Delete a PC of a given index from the PC list
     * @return if the index is a Prebuilt PC, inform the user that the PC of that index cannot be deleted. Or else,
     *         print the name of the PC deleted
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        // throw exception if no index is selected
        String argument = super.getArguments();
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
        PC pc = (dataStorage.pcList).get(pcIndex);
        if (pc.getIsPreBuilt()) {
            return "Unable to delete Prebuilt PC";
        }
        dataStorage.pcList.remove(pcIndex);
        return "Custom PC: [ " + pc.getName() + " ] has been deleted";
    }
}
