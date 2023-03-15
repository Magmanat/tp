package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.ui.UI;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingNameException;
import seedu.bigpp.ui.UIState;

public class BuilderEditNameCommand extends Command {

    public BuilderEditNameCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Change the name of the current PC that the builder is working on
     * @return the new name of the PC
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        assert UI.getUiState() == UIState.PCBUILDER : "UI state should be PCBUILDER";

        String name = super.getArguments();
        if (name.equals("")) {
            throw new BuilderMissingNameException();
        }

        int pcIndex = UI.builderMenu.getPCIndex();
        dataStorage.pcList.get(pcIndex).setName(name);
        return "Current build name is now: " + name;
    }
}
