package nl.tudelft.jpacman.acceptance;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
public class AcceptanceTest {
    private Launcher launcher;
    private Game game;
    private Player player;
    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();
    }
    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    @Test
    public void SuspendTheGame(){
        launcher = launcher.withMapFile("/SuspendTheGame.txt");
        launcher.launch();
        game = launcher.getGame();
        game.start();
        game.stop();
        assertFalse(game.isInProgress());
    }

    @Test
    public void MoveThePlayerOne(){
        launcher = launcher.withMapFile("/MoveThePlayerOne.txt");
        launcher.launch();
        game = launcher.getGame();
        player = game.getPlayers().get(0);
        game.start();
        game.move(player, Direction.EAST);
        assertThat(player.getScore()).isEqualTo(10);
        game.move(player, Direction.WEST);
        Square target = player.getSquare().getSquareAt(Direction.EAST);
        assertTrue(target.getOccupants().isEmpty());
    }

    @Test
    public void MoveThePlayerTwo(){
        launcher = launcher.withMapFile("/MoveThePlayerTwo.txt");
        launcher.launch();
        game = launcher.getGame();
        player = game.getPlayers().get(0);
        game.start();
        Direction[] directionList = {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};
        for (int i = 0; i < 4; i++){
            Square origin = player.getSquare();
            Square direction = origin.getSquareAt(directionList[i]);
            assertFalse(direction.isAccessibleTo(player));
            game.move(player, directionList[i]);
            assertEquals(origin, player.getSquare());
        }
    }

    @Test
    public void MoveThePlayerThree(){
        launcher = launcher.withMapFile("/MoveThePlayerThree.txt");
        launcher.launch();
        game = launcher.getGame();
        player = game.getPlayers().get(0);
        game.start();
        game.move(player, Direction.EAST);
        assertFalse(player.isAlive());
        assertFalse(game.isInProgress());
    }
}