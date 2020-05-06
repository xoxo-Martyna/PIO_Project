import java.util.Random;

public class Fight {
    private Game game;

    private Opponent opponent;
    private Player player;

    private int xP;
    private int xO;
    private int xCursor;

    private boolean isPlayerTurn;
    private Random random;

    public Fight(Opponent opponent, Game game) {
        this.opponent = opponent;
        this.game = game;
        player = game.getPlayer();
        isPlayerTurn = true;
        xP = 1;
        xO = 1;
        xCursor = 1;
        random = new Random();
    }

    public int getXP() {
        return xP;
    }

    public int getXO() {
        return xO;
    }

    public int getXCursor() {
        return xCursor;
    }

    public void moveCursor(int dx) {
        if (xCursor + dx >= 0 && xCursor + dx <= 2)
            xCursor += dx;
    }

    public void movePlayer(int dx) {
        if (xP + dx >= 0 && xP + dx <= 2)
            xP += dx;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void changeTurn() {
        isPlayerTurn = !isPlayerTurn;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void playerMove() {
        xO = random.nextInt(3);

        if (xCursor != xO) {
            opponent.setHealthPoints(opponent.getHealthPoints() - player.getAttackPoints());
            damageAttack( (int)((float)player.getAttackPoints()*0.08) );
        }

        checkEndFight();
    }

    public void opponentMove() {
        int attackX = random.nextInt(3);

        if (attackX != xP) {

            int slap;
            int tripleP = player.getDefensePoints();
            int oneShot = opponent.getAttackPoints();

            damageDefense( (int)((float)oneShot*0.05) );

            if (oneShot - tripleP > 0)
                slap = oneShot - tripleP;
            else
                slap = 0;

            player.setHPpoints( player.getHealthPoints() - slap );
            
            checkEndFight();
        }
    }

    private void checkEndFight() {
        if(player.getHealthPoints() <= 0){
            game.endFight(false);
        } else if(opponent.getHealthPoints() <= 0){
            game.endFight(true);
        }
    }

    private void damageDefense( int damage ){
        DefenseItem item1 = (DefenseItem)player.getDefenseItem(1);
        DefenseItem item2 = (DefenseItem)player.getDefenseItem(2);

        if( item1 != null )
            item1.setProtectPoints( item1.getProtectPoints() - damage );
        else if( item2 != null )
            item2.setProtectPoints( item2.getProtectPoints() - damage );
    }

    private void damageAttack( int damage ){
        AttackItem item = (AttackItem)player.getAttackItem();

        if( item != null ){
            item.setAttackPoints( item.getAttackPoints() - damage );
        }
    }
}