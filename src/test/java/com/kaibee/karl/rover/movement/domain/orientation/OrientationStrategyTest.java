package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.movable.RoverHelper.buildValidRover;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.east;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.north;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.south;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.west;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationStrategyTest {
  @Test
  void shouldRotateLeftToTheNorthWhenEastbound() {
    Orientation actualOrientation = east().rotateLeft();

    assertEquals(north(), actualOrientation);
  }

  @Test
  void shouldRotateRightToTheSouthWhenEastbound() {
    Orientation actualOrientation = east().rotateRight();

    assertEquals(south(), actualOrientation);
  }

  @Test
  void shouldRotateLeftToTheWestWhenNorthbound() {
    Orientation actualOrientation = north().rotateLeft();

    assertEquals(west(), actualOrientation);
  }

  @Test
  void shouldRotateRightToTheEastWhenNorthbound() {
    Orientation actualOrientation = north().rotateRight();

    assertEquals(east(), actualOrientation);
  }

  @Test
  void shouldMoveBackwardOnlyOnceAndPreserveOrientation() {
    Movable rover = buildValidRover(2, 1);
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2 - 1, 1);

    CartesianCoordinates actualCoordinates = east().moveBackward(rover);

    assertEquals(expectedCoordinates, actualCoordinates);
  }

  @Test
  void shouldMoveForwardOnlyOnceAndPreserveOrientation() {
    Movable rover = buildValidRover(2, 1);
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2 + 1, 1);

    CartesianCoordinates actualCoordinates = east().moveForward(rover);

    assertEquals(expectedCoordinates, actualCoordinates);
  }
}