package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;

import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderEditBudgetCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_editBudgetCommand_success() throws PPException {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);
        String editBudgetCommandResult = new BuilderEditBudgetCommand("1000").executeCommand(dataStorage);
        assertEquals("Current build budget is now: $1000.00", editBudgetCommandResult);
    }
}
