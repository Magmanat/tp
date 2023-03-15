package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.builderexceptions.BuilderIncorrectComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingListException;
import seedu.bigpp.ui.UI;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingComponentException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderListComponentCommandTest {
    private static final String EXPECTED_OUTPUT = "Here are all available components of type 'cpu': \n" + "1.\n"
            + "NAME: Intel core i3-10100\n" + "BRAND: Intel\n" + "PRICE: 99.5\n" + "CORES: 4\n" + "THREADS: 8\n"
            + "BASECLOCK: 3.6\n" + "BOOSTCLOCK: 4.3\n" + "POWER: 65.0\n" + "SOCKET: LGA1200\n" + "================\n"
            + "2.\n" + "NAME: Intel core i5-10600k\n" + "BRAND: Intel\n" + "PRICE: 182.47\n" + "CORES: 6\n"
            + "THREADS: 12\n" + "BASECLOCK: 4.1\n" + "BOOSTCLOCK: 4.8\n" + "POWER: 125.0\n" + "SOCKET: LGA1200\n"
            + "================\n" + "3.\n" + "NAME: Intel core i7-10700k\n" + "BRAND: Intel\n" + "PRICE: 269.95\n"
            + "CORES: 8\n" + "THREADS: 16\n" + "BASECLOCK: 3.8\n" + "BOOSTCLOCK: 5.1\n" + "POWER: 125.0\n"
            + "SOCKET: LGA1200\n" + "================\n" + "4.\n" + "NAME: Intel core i3-12100f\n" + "BRAND: Intel\n"
            + "PRICE: 106.93\n" + "CORES: 4\n" + "THREADS: 8\n" + "BASECLOCK: 3.3\n" + "BOOSTCLOCK: 4.3\n"
            + "POWER: 58.0\n" + "SOCKET: LGA1700\n" + "================\n" + "5.\n" + "NAME: Intel core i5-12600k\n"
            + "BRAND: Intel\n" + "PRICE: 213.82\n" + "CORES: 10\n" + "THREADS: 20\n" + "BASECLOCK: 3.7\n"
            + "BOOSTCLOCK: 4.9\n" + "POWER: 125.0\n" + "SOCKET: LGA1700\n" + "================\n" + "6.\n"
            + "NAME: Intel core i7-12700k\n" + "BRAND: Intel\n" + "PRICE: 312.88\n" + "CORES: 12\n" + "THREADS: 24\n"
            + "BASECLOCK: 3.6\n" + "BOOSTCLOCK: 5.0\n" + "POWER: 125.0\n" + "SOCKET: LGA1700\n" + "================\n"
            + "7.\n" + "NAME: AMD Ryzen 3 3200G\n" + "BRAND: AMD\n" + "PRICE: 97.5\n" + "CORES: 4\n" + "THREADS: 4\n"
            + "BASECLOCK: 3.6\n" + "BOOSTCLOCK: 4.0\n" + "POWER: 65.0\n" + "SOCKET: AM4\n" + "================\n"
            + "8.\n" + "NAME: AMD Ryzen 5 5600X\n" + "BRAND: AMD\n" + "PRICE: 156.64\n" + "CORES: 6\n" + "THREADS: 12\n"
            + "BASECLOCK: 3.7\n" + "BOOSTCLOCK: 4.6\n" + "POWER: 65.0\n" + "SOCKET: AM4\n" + "================\n"
            + "9.\n" + "NAME: AMD Ryzen 7 5800X\n" + "BRAND: AMD\n" + "PRICE: 211.33\n" + "CORES: 8\n" + "THREADS: 16\n"
            + "BASECLOCK: 3.8\n" + "BOOSTCLOCK: 4.7\n" + "POWER: 105.0\n" + "SOCKET: AM4\n" + "================\n"
            + "10.\n" + "NAME: AMD Ryzen 5 7600X\n" + "BRAND: AMD\n" + "PRICE: 240.86\n" + "CORES: 6\n"
            + "THREADS: 12\n" + "BASECLOCK: 4.7\n" + "BOOSTCLOCK: 5.3\n" + "POWER: 105.0\n" + "SOCKET: AM5\n"
            + "================\n" + "11.\n" + "NAME: AMD Ryzen 7 7700X\n" + "BRAND: AMD\n" + "PRICE: 341.29\n"
            + "CORES: 8\n" + "THREADS: 16\n" + "BASECLOCK: 4.5\n" + "BOOSTCLOCK: 5.4\n" + "POWER: 105.0\n"
            + "SOCKET: AM5\n" + "================\n" + "12.\n" + "NAME: AMD Ryzen 9 7950X\n" + "BRAND: AMD\n"
            + "PRICE: 589.0\n" + "CORES: 16\n" + "THREADS: 32\n" + "BASECLOCK: 4.5\n" + "BOOSTCLOCK: 5.7\n"
            + "POWER: 170.0\n" + "SOCKET: AM5\n" + "================\n";

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_listComponentCommand_success() throws BuilderMissingListException,
            BuilderIncorrectComponentException,
            BuilderMissingComponentException {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        String listComponentCommandResult = new BuilderListComponentCommand("cpu").executeCommand(dataStorage);
        assertEquals(EXPECTED_OUTPUT, listComponentCommandResult);
    }
}
