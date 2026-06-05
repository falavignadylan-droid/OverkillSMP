package overkill.ah;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.inventory.ItemStack;


public class AHManager {

    private final AHStorage storage;

    public AHManager(AHStorage storage) {
        this.storage = storage;
    }
    

    public AHItem getByItem(ItemStack stack) {
    for (AHItem i : storage.getItems()) {
        if (i.getItem().isSimilar(stack)) return i;
    }
    return null;
}

    public List<AHItem> searchItems(String query) {
    return storage.getItems().stream()
            .filter(i -> i.getItem().getType().name().toLowerCase().contains(query.toLowerCase()))
            .toList();
}

    public List<AHItem> getPage(int page, int perPage) {
        int start = (page - 1) * perPage;
        List<AHItem> all = storage.getItems();

        if (start >= all.size()) return new ArrayList<>();

        int end = Math.min(start + perPage, all.size());
        return all.subList(start, end);
    }

    public void addItem(AHItem item) {
        storage.getItems().add(item);
        storage.save();
    }

    public void removeItem(UUID id) {
        storage.getItems().removeIf(i -> i.getId().equals(id));
        storage.save();
    }
}
