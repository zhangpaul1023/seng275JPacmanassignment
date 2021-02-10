package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */
public class DirectionTest {
    /**
     * Do we get the correct delta when moving north?
     */
    @Test
    void testNorth() {
        Direction north = Direction.valueOf("NORTH");  //arrange
        assertThat(north.getDeltaY()).isEqualTo(-1);   //assert
    }

    @Test
    void testSouth() {
        Direction south = Direction.valueOf("SOUTH");  //arrange
        assertThat(south.getDeltaY()).isEqualTo(1);   //assert
    }

    @Test
    void testWest() {
        Direction west = Direction.valueOf("WEST");  //arrange
        assertThat(west.getDeltaX()).isEqualTo(-1);   //assert
    }

    @Test
    void testEast() {
        Direction east = Direction.valueOf("EAST");  //arrange
        assertThat(east.getDeltaX()).isEqualTo(1);   //assert
    }
}
