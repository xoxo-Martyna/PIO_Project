/*
#to_do:
- rysowanie planszy
- lol grafika testowa (obrazki lol pobraliście affinity?)
- łażenie po planszy
- lol kolizja
- drzwi
- pliki poziomów

Ola: drzwi 
Martyna - Let me speak to your manager... : łażenie po planszy //key listener i zmienia w graczu x i y i potem repaint()
Kacper S: plik poziomów i edytor poziomów
Kacper A: lol KOLIZJA
Hubert: Grafika testowa lol - gracz, podloge, sciana
Artur: rysowanie planszy

*/


public interface IItem {
    private String name;
    private Sprite sprite;

    private int durability;

    public String getName();

    public int getDurability();
}

public class Item implements IItem {
}

// LISTA BRONI
// miecz, łuk, ruszczka, dzida laserowa, tasak Magdy Gessler
public class OffenseItem extends Item {
    private int attackPoints;

    private void attack( Opponent opponent );
}

// LISTA LECZEŃ
// APAP, szałwia na dziąsła
public class HealthItem implements IItem {
    private int recoverPoints;

    public int getRecoverPoints();
}

// OBRONA
// czapka, zbroja, tarcza
public class DefenseItem implements IItem {
    private int protectPoints;

    public int getProtectPoints();
}

public class Fight {
    private Game game;
    
    private Opponent opponent;
    private boolean isPlayersTurn;

    public Opponent getOpponent();
    public boolean getIsPlayersTurn();
}

public interface ISpawner {
    private float spawnRate;
    private Opponent opponent;

    public boolean willSpawn();
    public Opponent getOpponent();
}

public class Floor {
    private String id;
    private Sprite sprite;
    private ISpawner spawner;


    public String getId();
    public Sprite getSprite();
    public ISpawner getSpawner();
}

public class Wall {
    private String id;
    private Sprite sprite;
    private boolean collideable;

    public String getId();
    public Sprite getSprite();
    public boolean getCollideable();
}

public class ILevelTile {
    private Floor floor;
    private Wall wall;

    public boolean canPlayerEnter();
    public void handlePlayerEnter( Game game );
}

public class Level {
    private String id;
    private ILevelTile[][] tiles;

    public String getId();
    public ILevelTile getTile( int x, int y);
}

public interface IFightMember {
    private int healthPoints;
    private int defensePoints;

    public int getHealthPoints();
    public int getDefensePoints();
}

public class Opponent extends IFightMember {
    private IItem[][] lootTable;
    private String name;

    public IItem[] getLoot();
    public String getName();
}

public class Player extends IFightMember {
    private int x;
    private int y;

    private List<IItem> items;
    
    public int getX();
    public int getY();
    public void setX( int x );
    public void setY( int y );

    public void move( Level level, int dx, int dy );

    public void addItem( IItem item );
    public void dropItem( IItem item );
    public void discardDestroyedItems();
}

enum GameState {
    exploration,
    justPickedUpItem,
    inFightTransition,
    inLevelTransition,

    waitingForPlayerMove,
    playerChoosingAttack,
    playerChoosingDefense,
    playerChoosingHeal,

    waitingForOpponentMove,
    opponentAttack,
    opponentDefense,
    opponentHeal,
    
    postWin,
    postFinalWin,
    postLose
}

public class Game {
    private GameState state;
    private boolean isInFight;

    private Player player;

    private Level[] levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    public void handleGameLoop();
    public int getTime();

    public Player getPlayer();

    public GameState getState();
    public void setState( GameState state );

    public Level getLevelFromId( String id );
    public Level getCurrentLevel();

    public Fight getCurrentFight();
    public void startFight( Fight fight );
    public void endFight();
}

public class Sprite {
    private BufferedImage sourceImage;
    private int frameCount;
}

public class Frame extends JFrame {}

public class Panel extends JPanel {}
