

public class Opponent implements IFightMember {
    private IItem[][] lootTable;
    private String name;

    public int getHealthPoints();
    public int getDefensePoints();

    public IItem[] getLoot();
    public String getName();
}