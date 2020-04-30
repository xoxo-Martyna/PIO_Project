
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
}