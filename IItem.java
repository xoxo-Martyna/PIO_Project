//w interfejsie wszystkie metody i zmienne muszą byc publiczne 

public interface IItem {
    private String name;
    private Sprite sprite;

    private int durability;

    public String getName();

    public int getDurability();
}