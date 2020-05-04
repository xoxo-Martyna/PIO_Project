
public class Fight {
    private Game game;
     
    private Opponent opponent;
    private Player player;

    private boolean isPlayerTurn;

    public Fight( Opponent opponent, Game game ){
        this.opponent = opponent;
        this.game = game;
        player = game.getPlayer();
        isPlayerTurn = true;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void changeTurn(){
        isPlayerTurn = !isPlayerTurn;
    }

    public boolean isPlayerTurn(){
        return isPlayerTurn;
    }

    public void playerMove(){
        opponent.setHealthPoints(opponent.getHealthPoints()-player.getAttackPoints());
        checkEndFight();
    }

    public void opponentMove(){
        int slap;
        int tripleP = player.getDefensePoints();
        int ciabasAttack = 1; // do testu
        int oneShot = 10; // do testu
        if(oneShot - tripleP > 0)
            slap = oneShot - tripleP;
        else 
            slap = 0;
        player.setHPpoints(slap);

        System.out.println("Przeciwnik wykonał bitch slapa! HP GRACZA = " + player.getHealthPoints()); // do wywalenia pozniej
        
        checkEndFight();
    }

    private void checkEndFight(){
        //to do hubert
        //game.endFight();
    }
}