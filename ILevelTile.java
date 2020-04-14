//w interfejsie wszystkie metody i zmienne muszą byc publiczne 

public class ILevelTile {
    private Floor floor;
    private Wall wall;

    public boolean canPlayerEnter();
    public void handlePlayerEnter( Game game );
}