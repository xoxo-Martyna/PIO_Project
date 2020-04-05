import org.graalvm.compiler.lir.LIRInstruction.Def;

public interface IItem {
    public void pickUpItem( Player player );
    public void dropItem();
    public void useItem();
}

public class Item implements IItem {
    public void pickUpItem( Player player );
    public void dropItem();
    public void useItem();
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
    private Player player;
    private Opponent opponent;
    private boolean isPlayersTurn;
}

public class Level {}

public interface IFightMember {
    private int healthPoints;
    private int defensePoints;
}

public class Opponent extends IFightMember {
    private IItem[][] lootTable;
}

public class Player extends IFightMember {
    private int x;
    private int y;
    
    public int getX();
    public int getY();
    public void setX( int x );
    public void setY( int y );

    public void move( Level level, int dx, int dy );
}

public class Game {
    private boolean isInFight;

    private Level[] levels;
    private Level currentLevel;

    private Fight currentFight;
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