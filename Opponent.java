

public class Opponent implements IFightMember {
    private IItem[][] lootTable;
    private String name;

    public int getHealthPoints() {
        return 0;
    }
    public int getDefensePoints() {
        return 0;
    }

    public IItem[] getLoot() {
        return null;
    }
    public String getName() {
        return "";
    }
}