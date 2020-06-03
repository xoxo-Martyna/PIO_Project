package tests;

import org.junit.jupiter.api.Test;
import src.*;

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


    @Test
    void useHealthItem(){
        Game game = new Game();
        Player player = new Player(game);
        HealthItem hi = (HealthItem) Item.create("herb1");
        int health = player.getHealthPoints();

        player.hurt(10);
        player.useHealthItem(hi);

        assertNotNull(hi);
        assertEquals(health, player.getHealthPoints());

    }
}
