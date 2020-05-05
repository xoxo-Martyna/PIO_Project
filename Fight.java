import java.util.Random;

public class Fight {
    private Game game;

    private Opponent opponent;
    private Player player;

    private int xP;
    private int xO;
    private int xCursor;

    private boolean isPlayerTurn;

    public Fight(Opponent opponent, Game game) {
        this.opponent = opponent;
        this.game = game;
        player = game.getPlayer();
        isPlayerTurn = true;
        xP = 1;
        xO = 1;
        xCursor = 1;
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
        Random random = new Random();
        xO = random.nextInt(3);

        if (xCursor != xO) {
            opponent.setHealthPoints(opponent.getHealthPoints() - player.getAttackPoints());
        }

        checkEndFight();
    }

    public void opponentMove() {
        Random random = new Random();
        int attackX = random.nextInt(3);

        if (attackX != xP) {

            int slap;
            int tripleP = player.getDefensePoints();
            int ciabasAttack = 1; // do testu
            int oneShot = 10; // do testu
            if (oneShot - tripleP > 0)
                slap = oneShot - tripleP;
            else
                slap = 0;
            player.setHPpoints(slap);

            System.out.println("Przeciwnik wykonał bitch slapa! HP GRACZA = " + player.getHealthPoints()); // do wywaenia pozniej
            
            checkEndFight();
        }
    }

    private void checkEndFight() {
        // to do hubert
        // game.endFight();
    }
}