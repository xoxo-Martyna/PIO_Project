
public interface IItem {
    private int durability;

    public void pickUpItem( Player player );
    public void dropItem();
    public void useItem();

    public boolean isDestroyed();
}

public class Item implements IItem {
    private String name;
    private Sprite sprite;

    public String getName();
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

    public static HealthItem( int recoverPoints );

    public int getRecoverPoints();
}

// OBRONA
// czapka, zbroja, tarcza
public class DefenseItem implements IItem {
    private int protectPoints;

    public static DefenseItem( int protectPoints );

    public int getProtectPoints();
}

public class Fight {
    private Game game;
    
    private Player player;
    private Opponent opponent;
    private boolean isPlayersTurn;
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
}

public class Opponent extends IFightMember {
    private IItem[][] lootTable;
    private String name;

    public IItem[] getLoot();

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

    private Level[] levels;
    private Level currentLevel;
    private Level nextLevel;

    private Fight currentFight;

    private int currentTime;
    private int referenceTime;

    public GameState getState();
    public void setState(GameState state);

    public Level getLevelFromId(String id);
    public Level getCurrentLevel();

    public void handleGameLoop();
    public int getTime();
}

public class Sprite {
    private BufferedImage sourceImage;
    private int frameCount;
}

public class Frame extends JFrame {}

public class Panel extends JPanel {}

/*
diagram klas - KACPER SEREDYNEK <3 I ARTUR 

// LOGIKA GRY
zawodnik, przeciwnik, poziom, tok walki, przedmioty

// GRAFIKA
Frame, Panel, Sprite


diagram sekwencji 𝓴𝓪𝓬𝓹𝓮𝓻 𝓪 & 𝓸𝓵𝓪 𝓴
1. ruch - przemieszczanie 
2. walka / wybrany fragment 


diagram stanów 𝓱𝓾𝓫𝓮𝓻𝓽 𝓷 𝓲 𝓶𝓪𝓻𝓽𝔂𝓷𝓪 𝓳

1. przejście z eksploracji do walki 
2. zmiana tury gracza do przeciwnika  tzn pojawia się opcja jak w herosach
 czekaj, obrona, atak, leczenie się itp
 */

/*
// STANY EKSPLORACJI
exploring
justPickedUpItem
inLevelTransition
inFightTransition

// STANY WALKI
waitingForPlayerMove
playerChoosingAttack
playerChoosingDefense
playerChoosingHeal
waitingForOpponentMove
opponentAttack
opponentDefense
opponentHeal
postWin
postFinalWin
postLose
*/