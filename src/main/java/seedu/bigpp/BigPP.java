package seedu.bigpp;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.parser.Parser;
import seedu.bigpp.ui.UI;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BigPP {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static DataStorage dataStorage;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new BigPP().run();
    }

    public void run() {
        // Initialize the DataStorage
        dataStorage = new DataStorage();

        dataStorage.loadAll();
        UI.showWelcome();
        UI.updateUI(true, dataStorage);
        runLoopUntilExit();
    }

    /**
     * Runs the program until the user issues the exit command.
     */
    private void runLoopUntilExit() {

        LOGGER.setLevel(Level.INFO);
        try {
            FileHandler handler = new FileHandler("BigPP.log", true);
            handler.setFormatter(new SimpleFormatter());
            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(handler);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File logger not working.", e);
        }
        Command command;
        do {
            String userInput = UI.getInput();
            LOGGER.info("User input: " + userInput);
            command = new Parser().parseCommand(userInput);
            String result = "";

            try {
                result = command.executeCommand(dataStorage);
                LOGGER.info("Command executed: " + command.getClass().getName());
                LOGGER.info("Result: " + result);
            } catch (PPException e) {
                result = e.getMessage();
                LOGGER.warning(e.getMessage());
            }

            assert command != null : "Command should not be null";

            assert command != null : "Command should not be null";

            UI.updateUI(false, dataStorage);
            UI.showResult(result);
        } while (!(command instanceof ByeCommand));
    }

}
