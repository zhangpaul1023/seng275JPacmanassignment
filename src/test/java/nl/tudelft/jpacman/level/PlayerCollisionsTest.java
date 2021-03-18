package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class PlayerCollisionsTest {
    PlayerCollisions cmap;
    PointCalculator pc;

    @BeforeEach
    void setUp() {
        pc = mock(PointCalculator.class);
        cmap = new PlayerCollisions(pc);
    }

    @Test
    void  PelletCollideGhost(){
        Pellet mockedPellet = mock(Pellet.class);
        Ghost mockedGhost = mock(Ghost.class);
        cmap.collide(mockedPellet, mockedGhost);
        verifyZeroInteractions(pc);
        verifyZeroInteractions(mockedGhost);
        verifyZeroInteractions(mockedPellet);
    }

    @Test
    void  PelletCollidePellet(){
        Pellet mockedPellet1 = mock(Pellet.class);
        Pellet mockedPellet2 = mock(Pellet.class);
        cmap.collide(mockedPellet1, mockedPellet2);
        verifyZeroInteractions(pc);
        verifyZeroInteractions(mockedPellet1);
        verifyZeroInteractions(mockedPellet2);
    }

    @Test
    void  PelletCollidePlayer(){
        Pellet mockedPellet = mock(Pellet.class);
        Player mockedPlayer = mock(Player.class);
        cmap.collide(mockedPellet, mockedPlayer);
        verify(pc).consumedAPellet(any(), any());
        verify(mockedPellet).leaveSquare();
        verifyZeroInteractions(mockedPlayer);
    }

    @Test
    void  GhostCollideGhost(){
        Ghost mockedGhost1 = mock(Ghost.class);
        Ghost mockedGhost2 = mock(Ghost.class);
        cmap.collide(mockedGhost1, mockedGhost2);
        verifyZeroInteractions(pc);
        verifyZeroInteractions(mockedGhost1);
        verifyZeroInteractions(mockedGhost2);
    }

    @Test
    void  GhostCollidePlayer(){
        Ghost mockedGhost = mock(Ghost.class);
        Player mockedPlayer = mock(Player.class);
        cmap.collide(mockedPlayer, mockedGhost);
        verify(pc).collidedWithAGhost(mockedPlayer, mockedGhost);
        verify(mockedPlayer).setKiller(mockedGhost);
        verify(mockedPlayer).setAlive(false);
    }

    @Test
    void  PlayerCollidePlayer(){
        Player mockedPlayer1 = mock(Player.class);
        Player mockedPlayer2 = mock(Player.class);
        cmap.collide(mockedPlayer1, mockedPlayer2);
        verifyZeroInteractions(pc);
        verifyZeroInteractions(mockedPlayer1);
        verifyZeroInteractions(mockedPlayer2);
    }
}