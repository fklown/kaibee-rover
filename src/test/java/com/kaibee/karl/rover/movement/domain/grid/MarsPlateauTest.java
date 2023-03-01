package com.kaibee.karl.rover.movement.domain.grid;

import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarsPlateauTest {
  @Test
  void shouldNotInstantiatePlateauWithNegativeBounds() {
    String expectedMessage = "Error creating Mars plateau - invalid bounds";

    Exception e1 = assertThrows(IllegalArgumentException.class, () -> new MarsPlateau(-1, 230));
    Exception e2 = assertThrows(IllegalArgumentException.class, () -> new MarsPlateau(23, -23));
    Exception e3 = assertThrows(IllegalArgumentException.class, () -> new MarsPlateau(0, -2));

    assertTrue(e1.getMessage().contentEquals(expectedMessage));
    assertTrue(e2.getMessage().contentEquals(expectedMessage));
    assertTrue(e3.getMessage().contentEquals(expectedMessage));
  }

  @Test
  void shouldBeOnGrid() {
    assertTrue(new MarsPlateau(2, 5).isOnGrid(new CartesianCoordinates(1, 4)));
    assertTrue(new MarsPlateau(1, 5).isOnGrid(new CartesianCoordinates(1, 3)));
    assertTrue(new MarsPlateau(9, 1).isOnGrid(new CartesianCoordinates(2, 1)));
  }

  @Test
  void shouldNotBeOnGridWhenOutOfBounds() {
    assertFalse(new MarsPlateau(2, 5).isOnGrid(new CartesianCoordinates(3, 4)));
    assertFalse(new MarsPlateau(1, 5).isOnGrid(new CartesianCoordinates(1, 7)));
    assertFalse(new MarsPlateau(9, 1).isOnGrid(new CartesianCoordinates(-1, -9)));
  }

  @Test
  void shouldNotInstantiateWithOutOfGridObstacles() {
    CartesianCoordinates outOfGridObstacle = new CartesianCoordinates(10, 10);
    Set<CartesianCoordinates> obstacleCoordinates = Set.of(outOfGridObstacle);
    String expectedMessage = "Error creating Mars plateau - invalid obstacle bounds";

    Exception e1 = assertThrows(IllegalArgumentException.class, () -> new MarsPlateau(1, 20, obstacleCoordinates));
    Exception e2 = assertThrows(IllegalArgumentException.class, () -> new MarsPlateau(20, 1, obstacleCoordinates));

    assertTrue(e1.getMessage().contentEquals(expectedMessage));
    assertTrue(e2.getMessage().contentEquals(expectedMessage));
  }

  @Test
  void shouldResolveGridObstacles() {
    CartesianCoordinates expectedObstacle = new CartesianCoordinates(10, 10);
    Set<CartesianCoordinates> obstacleCoordinates = Set.of(expectedObstacle);
    MarsPlateau marsPlateau1 = new MarsPlateau(10, 230, obstacleCoordinates);

    assertTrue(marsPlateau1.isObstaclePresent(expectedObstacle));
  }

}