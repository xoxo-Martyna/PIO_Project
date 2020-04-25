import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemFactory {
    private List<String[]> items;

    public ItemFactory() {
        items = new ArrayList<String[]>();
        loadItems();
    }

    private void loadItems() {
        try {
            XaxaLoader loader = new XaxaLoader("res/items.xaxa");

            while(true) {
                String[] itemInfo = loader.fetch();
                if (itemInfo == null) break;

                items.add(itemInfo);
            }

            loader.close();
        } catch (IOException e) {
            System.out.println("No items loaded!");
        }
    }

    public String[] get(String id) {
        for (String[] item : items) {
            if (item[0].equals(id))
                return item;
        }

        return null;
    }
}