package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingListException;
import seedu.bigpp.exceptions.builderexceptions.BuilderIncorrectComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingComponentException;
import seedu.bigpp.ui.UI;
import seedu.bigpp.ui.UIState;

public class BuilderListComponentCommand extends Command {

    public BuilderListComponentCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Change the budget of the current PC that the builder is working on
     * @return the new budget of the PC
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws BuilderMissingListException,
            BuilderIncorrectComponentException, BuilderMissingComponentException {

        assert UI.getUiState() == UIState.PCBUILDER : "UI state should be PCBUILDER";

        String componentTypeString = getArguments();
        if (componentTypeString.equals("")) {
            throw new BuilderMissingComponentException();
        }
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new BuilderIncorrectComponentException();
        }

        componentTypeString = componentTypeString.toLowerCase();

        // throw exception if component type is not valid eg. "list jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new BuilderIncorrectComponentException();
        }

        String outputString = "Here are all available components of type '" + componentTypeString + "': \n";

        return outputString + dataStorage.stringToComponentListMap.get(componentTypeString).getListString();
    }
}
