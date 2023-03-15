package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.builderexceptions.BuilderIncorrectComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderNullComponentException;
import seedu.bigpp.ui.UI;

public class BuilderUnselectCommand extends Command {

    public BuilderUnselectCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Change the Component of the PC to null
     * @return component removed message
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws BuilderIncorrectComponentException, BuilderMissingComponentException, BuilderNullComponentException {

        String componentTypeString = getArguments();
        // throw exception if no component is selected .eg. "Unselect"
        if (componentTypeString.equals("")) {
            throw new BuilderMissingComponentException();
        }

        componentTypeString = componentTypeString.toLowerCase();
        componentTypeString = componentTypeString.trim();

        // throw exception if component type is not valid .eg. "unselect jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new BuilderIncorrectComponentException();
        }

        int pcIndex = UI.builderMenu.getPCIndex();

        if (dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null) {
            throw new BuilderNullComponentException();
        }

        dataStorage.pcList.get(pcIndex).setNullComponent(componentTypeString);

        assert dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null : "Component should be null";

        return componentTypeString + " removed!";
    }
}
