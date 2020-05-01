
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
        System.out.println("Przeciwnik wykonal ruch!"); // do wywalenia pozniej

        //to do kacper
        
        checkEndFight();
    }

    private void checkEndFight(){
        //to do hubert
        //game.endFight();
    }
}