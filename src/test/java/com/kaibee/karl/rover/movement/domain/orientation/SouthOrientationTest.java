package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.movable.RoverTestHelper.buildValidRover;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SouthOrientationTest {

  @Test
  void shouldRotateLeftToTheEast() {
    Orientation eastOrientation = getSouthOrientation();
    Orientation actualOrientation = eastOrientation.rotateLeft();

    assertEquals(EastOrientation.class, actualOrientation.getClass());
  }

  @Test
  void shouldRotateRightToTheWest() {
    Orientation eastOrientation = getSouthOrientation();
    Orientation actualOrientation = eastOrientation.rotateRight();

    assertEquals(WestOrientation.class, actualOrientation.getClass());
  }

  @Test
  void shouldMoveBackwardOnlyOnceAndPreserveOrientation() {
    Movable rover = buildValidRover(2, 1);
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2, 1 + 1);

    Orientation eastOrientation = getSouthOrientation();
    CartesianCoordinates actualCoordinates = eastOrientation.moveBackward(rover);

    assertEquals(expectedCoordinates, actualCoordinates);
  }

  @Test
  void shouldMoveForwardOnlyOnceAndPreserveOrientation() {
    Movable rover = buildValidRover(2, 1);
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2, 1 - 1);

    Orientation eastOrientation = getSouthOrientation();
    CartesianCoordinates actualCoordinates = eastOrientation.moveForward(rover);

    assertEquals(expectedCoordinates, actualCoordinates);
  }

  private static Orientation getSouthOrientation() {
    return new SouthOrientation();
  }
}