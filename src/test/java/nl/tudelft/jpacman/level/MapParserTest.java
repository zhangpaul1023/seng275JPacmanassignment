package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class MapParserTest {

    @Test
    void  oneSpace(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        mapParser.parseMap(List.of(" "));
        verify(mockedBoardFactory).createGround();
        verify(mockedBoardFactory).createBoard(any());
        verify(mockedLevelFactory).createLevel(any(), anyList(), anyList());
    }

    @Test
    void  oneWall(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        mapParser.parseMap(List.of("#"));
        verify(mockedBoardFactory, times(1)).createWall();
        verify(mockedBoardFactory, times(1)).createBoard(any());
        verify(mockedLevelFactory, times(1)).createLevel(any(), anyList(), anyList());
    }

    @Test
    void  onePlayer(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        mapParser.parseMap(List.of("P "));
        verify(mockedBoardFactory, times(2)).createGround();
    }

    @Test
    void  onePellet(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        Pellet mockedPellet = mock(Pellet.class);
        when(mockedLevelFactory.createPellet()).thenReturn(mockedPellet);
        mapParser.parseMap(List.of("."));
        verify(mockedLevelFactory).createPellet();
        verify(mockedPellet).occupy(any());
    }

    @Test
    void oneGhost(){
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);
        Ghost mockedGhost = mock(Ghost.class);
        when(mockedLevelFactory.createGhost()).thenReturn(mockedGhost);
        Square mockedSquare = mock(Square.class);
        when(mockedBoardFactory.createGround()).thenReturn(mockedSquare);
        mapParser.parseMap(List.of("G"));
        verify(mockedGhost).occupy(mockedSquare);
        verifyZeroInteractions(mockedSquare);
    }

    @Test
    void oneSpaceNoOtherMethod(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);

        mapParser.parseMap(List.of(" "));

        verify(mockedBoardFactory).createGround();
        verify(mockedBoardFactory).createBoard(any());
        verify(mockedLevelFactory).createLevel(any(), anyList(), anyList());

        verifyNoMoreInteractions(mockedBoardFactory);
        verifyNoMoreInteractions(mockedLevelFactory);
    }

    @Test
    void bad_weather_one(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);

        assertThrows(PacmanConfigurationException.class, ()->mapParser.parseMap(anyList()));

    }

    @Test
    void bad_weather_two(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);

        assertThrows(PacmanConfigurationException.class, ()->mapParser.parseMap("NIUWUHEQAWELIIWJE"));
    }

    @Test
    void bad_weather_three(){
        BoardFactory mockedBoardFactory = mock(BoardFactory.class);
        LevelFactory mockedLevelFactory = mock(LevelFactory.class);
        MapParser mapParser = new MapParser(mockedLevelFactory, mockedBoardFactory);

        assertThrows(PacmanConfigurationException.class, ()->mapParser.parseMap(List.of("")));
    }

}