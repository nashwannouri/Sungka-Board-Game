import com.example.k1465128.sungka.AIplayer;
import com.example.k1465128.sungka.RealPlayer;


import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


/**
 * Created by nashwan on 11/13/2015.
 */
public class PlayerLogicTest {

    RealPlayer player = new RealPlayer();
    @Test
    public void RealPlayerNotNull(){
        assertNotNull(player);
    }

    @Test
    public void playerHasCorrectShellsStart() throws Exception {
        //TESTS IN HERE
        assertTrue(player.checkstartTray());
    }
    @Test
    public void testRealPlayerisNotAi() throws Exception{
        assertFalse(player.isAi());
    }

    AIplayer playerAi = new AIplayer();
    @Test
    public void AiPlayerNotNull(){
        assertNotNull(playerAi);
    }

    @Test
    public void testAiplayerHasCorrectShellsStart() throws Exception {
        //TESTS IN HERE
        assertTrue(playerAi.checkStartTray());
    }
    @Test
    public void testAIPlayerisAi() throws Exception{
        assertTrue(playerAi.isAi());
    }

    @Test
    public void testAiCheckBonusTurnOpptunity() throws Exception{

        assertTrue(playerAi.testBonusTurnStart() ==0);
    }

    @Test
    public void testAiStealOppotunity() throws Exception{
        playerAi.get_traySet().get(3).setValue(0);
        playerAi.get_traySet().get(1).setValue(2);
        playerAi.calibrateAiMoves();
        assertTrue(playerAi.getTrayScore()[1]==200);
    }
}
