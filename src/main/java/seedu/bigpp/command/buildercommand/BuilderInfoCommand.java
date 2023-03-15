package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.builderexceptions.BuilderIncorrectComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderNoInfoException;
import seedu.bigpp.ui.UI;

public class BuilderInfoCommand extends Command {

    public BuilderInfoCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * View all the specifications of a component in the pc build
     * @return the specifications of the component
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws BuilderMissingComponentException, BuilderIncorrectComponentException, BuilderNoInfoException {

        String componentTypeString = getArguments();
        componentTypeString = componentTypeString.toLowerCase();
        componentTypeString = componentTypeString.trim();

        if (componentTypeString.equals("")) {
            throw new BuilderMissingComponentException();
        }

        // throw exception if component type is not valid eg. "list jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new BuilderIncorrectComponentException();
        }

        int pcIndex = UI.builderMenu.getPCIndex();
        if (dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null) {
            throw new BuilderNoInfoException();
        }

        String outputString = "Here is the specifications of your selected " + componentTypeString + ": \n";
        return outputString + dataStorage.pcList.get(pcIndex).getComponent(componentTypeString);

    }
}
