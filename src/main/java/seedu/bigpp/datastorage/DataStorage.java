package seedu.bigpp.datastorage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.component.chassis.ChassisList;
import seedu.bigpp.component.cpu.CPU;
import seedu.bigpp.component.cpu.CPUList;
import seedu.bigpp.component.cpucooler.CPUCooler;
import seedu.bigpp.component.cpucooler.CPUCoolerList;
import seedu.bigpp.component.gpu.GPU;
import seedu.bigpp.component.gpu.GPUList;
import seedu.bigpp.component.motherboard.Motherboard;
import seedu.bigpp.component.motherboard.MotherboardList;
import seedu.bigpp.component.psu.PSU;
import seedu.bigpp.component.psu.PSUList;
import seedu.bigpp.component.ram.RAM;
import seedu.bigpp.component.ram.RAMList;
import seedu.bigpp.component.storage.Storage;
import seedu.bigpp.component.storage.StorageList;
import seedu.bigpp.pc.PC;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.component.ComponentList;
import static seedu.bigpp.ui.UI.out;

public class DataStorage {
    private static final String CHASSIS_PATH = "chassis.json";
    private static final String CPU_PATH = "cpu.json";
    private static final String CPU_COOLER_PATH = "cpucooler.json";
    private static final String GPU_PATH = "gpu.json";
    private static final String MOTHERBOARD_PATH = "motherboard.json";
    private static final String PSU_PATH = "psu.json";
    private static final String RAM_PATH = "ram.json";
    private static final String STORAGE_PATH = "storage.json";

    private static final String PREBUILT_PATH = "prebuilt.json";
    private static final String USER_PATH = "./user.json";

    private static final Gson GSON = new Gson();

    public Map<String, ComponentList<?>> stringToComponentListMap = new HashMap<String, ComponentList<?>>();
    public PCList pcList = new PCList();

    /**
     * Saves all the user's PCs to a json file.
     */
    public void saveUserPcs() {
        // open user file
        File file = new File(USER_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            out.println("Error creating user file, data will not be saved.");
        }

        try (FileWriter fileWriter = new FileWriter(file)) {

            // remove prebuilt PCs from list, we do not want prebuilts to be saved in the
            // user file
            for (int i = 0; i < pcList.size(); i++) {
                if (pcList.get(i).getIsPreBuilt()) {
                    pcList.remove(i);
                    i--;
                }
            }

            fileWriter.write(GSON.toJson(pcList));

        } catch (IOException e) {
            out.println("Error writing to user file, data will not be saved.");
        }

    }

    /**
     * Loads all the components from the json files.
     */
    public void loadAll() {
        // load prebuilt PCs, then load user PCs
        loadPrebuiltPcs(PREBUILT_PATH);
        loadUserPcs(USER_PATH);

        // load all components
        initStringToComponentListMap();
        loadChassis(CHASSIS_PATH);
        loadCPU(CPU_PATH);
        loadCPUCooler(CPU_COOLER_PATH);
        loadGPU(GPU_PATH);
        loadMotherboard(MOTHERBOARD_PATH);
        loadPSU(PSU_PATH);
        loadRAM(RAM_PATH);
        loadStorage(STORAGE_PATH);
    }

    public void initStringToComponentListMap() {
        stringToComponentListMap.put("cpu", new CPUList());
        stringToComponentListMap.put("gpu", new GPUList());
        stringToComponentListMap.put("chassis", new ChassisList());
        stringToComponentListMap.put("cpu-cooler", new CPUCoolerList());
        stringToComponentListMap.put("psu", new PSUList());
        stringToComponentListMap.put("ram", new RAMList());
        stringToComponentListMap.put("storage", new StorageList());
        stringToComponentListMap.put("motherboard", new MotherboardList());
    }

    /**
     * Loads all the chassis from the json file into ChassisList.
     */
    public void loadChassis(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Chassis> chassisMap = GSON.fromJson(json, new TypeToken<Map<String, Chassis>>() {
        }.getType());

        chassisMap.forEach((name, chassis) -> chassisMap.get(name).setName(name));

        // Add each chassis to the chassis list
        chassisMap.forEach((name, chassis) -> stringToComponentListMap.get("chassis").add(chassis));
    }

    /**
     * Loads all the CPUs from the json file into CPUList.
     */
    public void loadCPU(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, CPU> cpuMap = GSON.fromJson(json, new TypeToken<Map<String, CPU>>() {
        }.getType());

        cpuMap.forEach((name, cpu) -> cpuMap.get(name).setName(name));

        // Add each cpu to the cpu list
        cpuMap.forEach((name, cpu) -> stringToComponentListMap.get("cpu").add(cpu));
    }

    /**
     * Loads all the CPU coolers from the json file into CPUCoolerList.
     */
    public void loadCPUCooler(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, CPUCooler> cpuCoolerMap = GSON.fromJson(json, new TypeToken<Map<String, CPUCooler>>() {
        }.getType());

        cpuCoolerMap.forEach((name, cpuCooler) -> cpuCoolerMap.get(name).setName(name));

        // Add each cpu cooler to the cpu cooler list
        cpuCoolerMap.forEach((name, cpuCooler) -> stringToComponentListMap.get("cpu-cooler").add(cpuCooler));
    }

    /**
     * Loads all the GPUs from the json file into GPUList.
     */
    public void loadGPU(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, GPU> gpuMap = GSON.fromJson(json, new TypeToken<Map<String, GPU>>() {
        }.getType());

        gpuMap.forEach((name, gpu) -> gpuMap.get(name).setName(name));

        // Add each gpu to the gpu list
        gpuMap.forEach((name, gpu) -> stringToComponentListMap.get("gpu").add(gpu));
    }

    /**
     * Loads all the motherboards from the json file into MotherboardList.
     */
    public void loadMotherboard(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Motherboard> motherboardMap = GSON.fromJson(json, new TypeToken<Map<String, Motherboard>>() {
        }.getType());

        motherboardMap.forEach((name, motherboard) -> motherboardMap.get(name).setName(name));

        // Add each motherboard to the motherboard list
        motherboardMap.forEach((name, motherboard) -> stringToComponentListMap.get("motherboard").add(motherboard));
    }

    /**
     * Loads all the PSUs from the json file into PSUList.
     */
    public void loadPSU(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, PSU> psuMap = GSON.fromJson(json, new TypeToken<Map<String, PSU>>() {
        }.getType());

        psuMap.forEach((name, psu) -> psuMap.get(name).setName(name));

        // Add each psu to the psu list
        psuMap.forEach((name, psu) -> stringToComponentListMap.get("psu").add(psu));
    }

    /**
     * Loads all the RAMs from the json file into RAMList.
     */
    public void loadRAM(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, RAM> ramMap = GSON.fromJson(json, new TypeToken<Map<String, RAM>>() {
        }.getType());

        ramMap.forEach((name, ram) -> ramMap.get(name).setName(name));

        // Add each ram to the ram list
        ramMap.forEach((name, ram) -> stringToComponentListMap.get("ram").add(ram));
    }

    /**
     * Loads all the storage devices from the json file into StorageList.
     */
    public void loadStorage(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Storage> storageMap = GSON.fromJson(json, new TypeToken<Map<String, Storage>>() {
        }.getType());

        storageMap.forEach((name, storage) -> storageMap.get(name).setName(name));

        // Add each storage to the storage list
        storageMap.forEach((name, storage) -> stringToComponentListMap.get("storage").add(storage));
    }

    /**
     * Loads all the user's PCs from the json file into PCList.
     * This will append all user's PCs to the PCList.
     */
    public void loadUserPcs(String path) {
        File userFile = new File(path);

        // If file does not exist, create user file
        try {
            if (!userFile.exists()) {
                out.println("User file does not exist. Creating new user file.");
                userFile.createNewFile();

                FileWriter writer = new FileWriter(userFile);
                writer.write("[]");
                writer.close();
            }
        } catch (IOException e) {
            out.println("Error creating user file. Please try again.");
            System.exit(1);
        }

        // If file exists, but is empty, add empty array
        if (userFile.length() == 0) {
            try {
                FileWriter writer = new FileWriter(userFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                out.println("Error creating user file. Please try again.");
                System.exit(1);
            }
        }

        // Read entire file into string
        String json = "";
        try {
            json = Files.readString(userFile.toPath());
        } catch (IOException e) {
            out.println("Error reading user file. Please try again.");
            System.exit(1);
        }

        out.println("User PCs found, loading...");

        pcList.addAll(GSON.fromJson(json, new TypeToken<ArrayList<PC>>() {
        }.getType()));

    }

    /**
     * Loads all the prebuilt PCs from the json file into PCList.
     * Important to call this first as this will overwrite the user's PCs.
     */
    public void loadPrebuiltPcs(String path) {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        pcList = (GSON.fromJson(json, new TypeToken<PCList>() {
        }.getType()));

        // If there are no prebuilt PCs, ensure that PCList is not null
        if (pcList == null) {
            pcList = new PCList();
        }
    }

}
