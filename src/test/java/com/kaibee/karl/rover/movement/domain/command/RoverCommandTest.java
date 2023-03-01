package com.kaibee.karl.rover.movement.domain.command;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import com.kaibee.karl.rover.movement.domain.orientation.OrientationStrategy;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.movable.RoverTestHelper.buildValidRover;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverCommandTest {
  @Test
  void shouldMoveBackwardOnceWithoutChangingOrientation() {
    LandVehicle rover = buildValidRover(3, 5, OrientationStrategy.EAST);
    RoverCommand roverCommand = RoverCommand.B;
    Orientation expectedOrientation = OrientationStrategy.EAST.getOrientation();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation, rover.getOrientation());
  }

  @Test
  void shouldMoveForwardOnceWithoutChangingOrientation() {
    LandVehicle rover = buildValidRover(3, 7, OrientationStrategy.SOUTH);
    RoverCommand roverCommand = RoverCommand.F;
    Orientation expectedOrientation = OrientationStrategy.SOUTH.getOrientation();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 6);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation, rover.getOrientation());
  }

  @Test
  void shouldRotateLeftWithoutMoving() {
    LandVehicle rover = buildValidRover(3, 5, OrientationStrategy.NORTH);
    RoverCommand roverCommand = RoverCommand.L;
    Orientation expectedOrientation = OrientationStrategy.WEST.getOrientation();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation.getClass(), rover.getOrientation().getClass());
  }

  @Test
  void shouldRotateRightWithoutMoving() {
    LandVehicle rover = buildValidRover(3, 5, OrientationStrategy.SOUTH);
    RoverCommand roverCommand = RoverCommand.R;
    Orientation expectedOrientation = OrientationStrategy.WEST.getOrientation();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation.getClass(), rover.getOrientation().getClass());
  }
}