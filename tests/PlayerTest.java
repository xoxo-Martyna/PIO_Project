package tests;

import org.junit.jupiter.api.Test;
import src.AttackItem;
import src.Game;
import src.Item;
import src.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void useAttackItem() {
        Game game = new Game();
        Player player = new Player(game);
        AttackItem at = (AttackItem) Item.create("sword");


        player.items[1][1] = at;
        player.moveEQ(1,1);
        player.useAttackItem(at);

        assertNotNull(player.items[0][0]);
        assertEquals(player.items[0][0].getId(), at.getId());

        assertEquals(at.getId(), "sword");
        assertEquals(at.getAttackPoints(), 20);
        assertEquals(at.getName(), "SWORD");
    }
}
