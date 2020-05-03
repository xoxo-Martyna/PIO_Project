
public class Fight {
    private Game game;
     
    private Opponent opponent;
    private Player player;

    public Fight( Opponent opponent, Game game ){
        this.opponent = opponent;
        this.game = game;
        player = game.getPlayer();
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void playerMove(){
        System.out.println("Gracz wykonal ruch!"); // do wywalenia pozniej
      
        //to do martyna  

        checkEndFight();
    }

    public void opponentMove(){
        int slap;
        int tripleP = player.getDefensePoints();
        int ciabasAttack = 1; // do testu
        int oneShot = 60; // do testu
        if(oneShot - tripleP > 0)
            slap = oneShot - tripleP;
        else 
            slap = 0;
        player.setHPpoints(slap);

        System.out.println("Przeciwnik wykonał bitch slapa!\n" + player.getHealthPoints()); // do wywalenia pozniej

     
        
        checkEndFight();
    }

    private void checkEndFight(){
        //to do hubert
        //game.endFight();
    }
}