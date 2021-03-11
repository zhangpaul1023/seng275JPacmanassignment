package nl.tudelft.jpacman.game;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class GameTest {
    private Player mockPlayer;
    private Level mockLevel;
    private PointCalculator mockPointCalculator;
    private SinglePlayerGame mockGame;

    @Test
    void Gamestart(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        mapParser.parseMap(List.of("############",
                                    "#P.......C..",
                                    "############"));
        Ghost mockedGhost = mock(Ghost.class);
        when(mockedLevelFactory.create()).thenReturn(mockedGhost);
        game.start();
        assertFalse(game.isInProgress());
    }

}