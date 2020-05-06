import java.util.Random;

public class Fight {
    private Game game;

    private Opponent opponent;
    private Player player;

    private int playerPosition;
    private int opponentPosition;
    private int xCursor;

    private boolean isPlayerTurn;
    private Random random;

    public Fight( Opponent opponent, Game game ) {
        this.opponent = opponent;
        this.game = game;
        player = game.getPlayer();
        isPlayerTurn = true;
        playerPosition = 1;
        opponentPosition = 1;
        xCursor = 1;
        random = new Random();
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public int getOpponentPosition() {
        return opponentPosition;
    }

    public int getXCursor() {
        return xCursor;
    }

    public void moveCursor( int dx ) {
        if ( xCursor + dx >= 0 && xCursor + dx <= 2 )
            xCursor += dx;
    }

    public void movePlayer( int dx ) {
        if ( playerPosition + dx >= 0 && playerPosition + dx <= 2 )
            playerPosition += dx;
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
        opponentPosition = random.nextInt(3);

        if (xCursor != opponentPosition) {
            opponent.setHealthPoints( opponent.getHealthPoints() - player.getAttackPoints() );
            damageAttack();
        }

        checkEndFight();
    }

    public void opponentMove() {
        int attackX = random.nextInt( 3 );

        if ( attackX != playerPosition ) {

            int harmPoints;
            int playerDefense = player.getDefensePoints();
            
            int attack = opponent.getAttackPoints();

            damageDefense( (int)((float) attack * 0.05 ) );

            if (attack - playerDefense > 0)
                harmPoints = attack - playerDefense;
            else
                harmPoints = 0;

            player.setHPpoints( player.getHealthPoints() - harmPoints );
            
            checkEndFight();
        }
    }

    private void checkEndFight() {
        if ( player.getHealthPoints() <= 0 ) {
            game.endFight( false );
        } else if ( opponent.getHealthPoints() <= 0 ) {
            game.endFight( true );
        }
    }

    private void damageDefense( int damage ){
        DefenseItem item1 = (DefenseItem)player.getDefenseItem( 1 );
        DefenseItem item2 = (DefenseItem)player.getDefenseItem( 2 );

        if( item1 != null )
            item1.setProtectPoints( item1.getProtectPoints() - damage );
        else if( item2 != null )
            item2.setProtectPoints( item2.getProtectPoints() - damage );
    }

    private void damageAttack() {
        AttackItem item = (AttackItem)player.getAttackItem();

        if( item != null ){
            int attackPoints = item.getAttackPoints();
            item.setAttackPoints( (int)((float) attackPoints * 0.92 ) );
        }
    }
}