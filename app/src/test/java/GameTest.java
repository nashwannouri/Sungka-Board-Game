import com.example.k1465128.sungka.Game;
import com.example.k1465128.sungka.RealPlayer;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nashwan on 11/13/2015.
 */
public class GameTest {

    Game game= new Game();
    @Test
    public void testfirstTurn(){
        assertTrue(game.getIsFirstTime());
    }

    @Test
    public void testSteal() {
        RealPlayer p1= new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        p1.get_traySet().get(5).setValue(0);  //5th tray is empty
        p1.get_traySet().get(4).setValue(1); //4th tray has 1 shell
        p1.get_traySet().get(7).setValue(10);  //the head has 10shells
        p1.set_selectedTray(4);                  //4th tray is picked
        p2.get_traySet().get(1).setValue(6);     //the tray opposite the 5th tray(6-index) has 6 shells
        game.setSimultaneousTurn(false);                //to be considered as a normal move
        game.processMove(p1, p2);                   //p1 makes the move
        assertTrue(p1.get_traySet().get(7).getShells()==17); //the steal method should return 17 in p1 head
    }

    @Test
    public void testSimultanousWaitp1(){
        RealPlayer p1 = new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        p1.set_selectedTray(6);
        game.processMove(p1, p2);
        assertTrue(p1.get_traySet().get(6).getShells()==7); //should remain same as p2 has not selected tray yet.
    }

    @Test
    public void testSimultanousWaitp2(){
        RealPlayer p1 = new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        p1.set_selectedTray(4);
        game.processMove(p2, p1);
        assertTrue(p2.get_traySet().get(4).getShells()==7); //should remain same as p1 has not selected tray yet.
    }

    @Test
    public void testSimultaneousTurn(){
        RealPlayer p1  = new RealPlayer();
        RealPlayer p2  =new RealPlayer();
        p1.set_selectedTray(5);
        p2.set_selectedTray(1);
        game.processMove(p1, p2);
        game.processMove(p2, p1);
        boolean check =p1.getIsTurn()&&p2.get_traySet().get(1).getShells()==1;
        assertTrue(check);   //Simultaneous first Turn;
    }

    @Test
    public void testSimultanousSingleBonusTurn(){
        RealPlayer p1  = new RealPlayer();
        RealPlayer p2  =new RealPlayer();
        p1.set_selectedTray(5);
        p2.set_selectedTray(0);
        game.processMove(p1, p2);
        game.processMove(p2, p1);
        assertTrue(p2.isTurn()&&!p1.getIsTurn());


    }
    @Test
    public void testSimultanousDoubleBonusTurn(){
        RealPlayer p1  = new RealPlayer();
        RealPlayer p2  =new RealPlayer();
        p1.set_selectedTray(0);
        p2.set_selectedTray(0);
        game.processMove(p1, p2);
        game.processMove(p2, p1);
        assertTrue(p2.isTurn()&&p1.getIsTurn());
    }

    /**
     * to see if correct players turn after double simultaneous turn
     */
    @Test
    public void testafterDoubleSimultaneousTurn(){
        RealPlayer p1  = new RealPlayer();
        RealPlayer p2  =new RealPlayer();
        p1.set_selectedTray(0);
        p2.set_selectedTray(0);
        game.processMove(p1, p2);
        game.processMove(p2, p1);
        p1.set_selectedTray(2);
        p2.set_selectedTray(2);
        game.processMove(p1, p2);
        game.processMove(p2, p1);
        assertTrue(p1.isTurn()&&!p2.isTurn());
    }

    @Test
    public void testGameOver(){
        RealPlayer p1 =new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        game.setSimultaneousTurn(false);
        for(int i =0;i<6;++i){
            p1.get_traySet().get(i).setValue(0);
            p2.get_traySet().get(i).setValue(0);
        }
        p1.get_traySet().get(6).setValue(1);
        p2.get_traySet().get(6).setValue(0);
        p1.setIsTurn(true);
        p1.set_selectedTray(6);

        p1.get_traySet().get(7).setValue(80);// p1 should win with head being 81
        p2.get_traySet().get(7).setValue(17);//p2 should lose
        game.processMove(p1, p2);
        assertTrue(game.isGameOver()&&p1.get_traySet().get(7).getShells()==81);
    }

    @Test
    public void testAddingtoOpponenttrays(){
        RealPlayer p1 =new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        game.setSimultaneousTurn(false);
        p1.setIsTurn(true);
        p1.set_selectedTray(6);
        game.processMove(p1, p2);
        boolean check =true;
        for(int i=0;i<5;++i){//should distrubite to the first 5 shells of opponent and make them 8
            if(p2.get_traySet().get(i).getShells()!=8){
                check=false;
                break;
            }
        }
        assertTrue(check);
    }


    @Test
    public void testBonusTurn(){
        RealPlayer p1= new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        p1.get_traySet().get(5).setValue(2);         //enough shells to get to head (index 7)
        p1.set_selectedTray(5);                      //tray 5 is selected
        p1.setIsTurn(true);
        p2.setIsTurn(false);
        assertTrue(p1.isTurn()&&!p2.isTurn());
    }

    @Test
    public void testAvoidAddingToOpponentHead(){
        RealPlayer p1 = new RealPlayer();
        RealPlayer p2 = new RealPlayer();
        game.setSimultaneousTurn(false);               //considdering normal turns
        p1.setIsTurn(true);                     //its player 1 turn
        p2.setIsTurn(false);
        p1.set_selectedTray(5);                 //player1 selects tray index 5
        game.processMove(p1, p2);                  //move is done
        p1.setIsTurn(true);                    //make it the p1 turn again
        p2.setIsTurn(false);
        p1.set_selectedTray(6);                 //this tray should have 8 shells now which is enough to reach opponents head
        game.processMove(p1, p2);                   //move is done
        assertTrue(p2.get_traySet().get(7).getShells()==0);  //check if opponent head has 0.
    }

    @Test
    public void testStealAfterFullLoop(){
        RealPlayer p1 = new RealPlayer();
        RealPlayer p2 = new RealPlayer();

        //getting p1 final small tray to 15 shells(perfect loop)
        for(int i=5;i>=0;i--){
          p1.set_selectedTray(i);
            p2.set_selectedTray(i);
            game.processMove(p1, p2);
            game.processMove(p2, p1);
        }
        //p1 head is currently 6, final small tray is 13,opposite tray is  0

        //final moves to set up game
        p1.set_selectedTray(5); //has 10 shells
        game.processMove(p1, p2);
        p2.set_selectedTray(6); //has 14 shells
        game.processMove(p2, p1);
        //p1 head is currently 7, final small tray is 15, opposite tray is 2

        p1.set_selectedTray(6);
        game.processMove(p1,p2);

        //head should now have 7+1+3+1 =12
        assertTrue(p1.get_traySet().get(7).getShells()==12);

    }
    @Test
    public void testBonusAfterFullLoop(){
        RealPlayer p1 = new RealPlayer();
        RealPlayer p2 = new RealPlayer();

        //getting p1 final small tray to 16 shells(reaches head)
        for(int i=5;i>=0;i--){
            p1.set_selectedTray(i);
            p2.set_selectedTray(i);
            game.processMove(p1, p2);
            game.processMove(p2,p1);
        }
        // final small tray is 13

        //
        p1.set_selectedTray(5); //has 10 shells
        game.processMove(p1, p2);
        p2.set_selectedTray(6); //has 14 shells
        game.processMove(p2, p1);
        // final small tray is 15

        p1.set_selectedTray(5);
        game.processMove(p1, p2);
        p1.set_selectedTray(0);
        game.processMove(p1, p2);
        //final small tray for p1 now has 16

        p1.set_selectedTray(6);
        game.processMove(p1,p2);
        assertTrue(p1.isTurn()&&!p2.isTurn());

    }

}
