

public class Opponent implements IFightMember {
    private int healthPoints;
    private int defensePoints;

    private String name;

    public Opponent(String id, String name, int healthPoints, int defensePoints) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.defensePoints = defensePoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public int getDefensePoints() {
        return defensePoints;
    }

    public String getName() {
        return "";
    }
}