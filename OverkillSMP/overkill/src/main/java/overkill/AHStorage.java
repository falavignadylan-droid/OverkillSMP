package overkill.ah;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class AHStorage {

    private final File file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<AHItem> items = new ArrayList<>();

    public AHStorage(File file) {
        this.file = file;
        load();
    }

    public void load() {
        if (!file.exists()) return;
        try (FileReader reader = new FileReader(file)) {
            AHItem[] array = gson.fromJson(reader, AHItem[].class);
            if (array != null) items = new ArrayList<>(Arrays.asList(array));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void save() {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(items, writer);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<AHItem> getItems() { return items; }
}
