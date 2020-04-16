//w interfejsie wszystkie metody i zmienne muszą byc publiczne 

public interface ISpawner {
    // private float spawnRate;
    // private Opponent opponent;

    public boolean willSpawn();
    public Opponent getOpponent();
}