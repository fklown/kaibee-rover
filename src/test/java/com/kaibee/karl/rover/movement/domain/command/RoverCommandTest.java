package com.kaibee.karl.rover.movement.domain.command;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.movable.RoverHelper.buildValidRover;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.east;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.north;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.south;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.west;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverCommandTest {
  @Test
  void shouldMoveBackwardOnceWithoutChangingOrientation() {
    LandVehicle rover = buildValidRover(3, 5, east());
    RoverCommand roverCommand = RoverCommand.B;
    Orientation expectedOrientation = east();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(2, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation, rover.getOrientation());
  }

  @Test
  void shouldMoveForwardOnceWithoutChangingOrientation() {
    LandVehicle rover = buildValidRover(3, 7, south());
    RoverCommand roverCommand = RoverCommand.F;
    Orientation expectedOrientation = south();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 6);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation, rover.getOrientation());
  }

  @Test
  void shouldRotateLeftWithoutMoving() {
    LandVehicle rover = buildValidRover(3, 5, north());
    RoverCommand roverCommand = RoverCommand.L;
    Orientation expectedOrientation = west();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation.getClass(), rover.getOrientation().getClass());
  }

  @Test
  void shouldRotateRightWithoutMoving() {
    LandVehicle rover = buildValidRover(3, 5, south());
    RoverCommand roverCommand = RoverCommand.R;
    Orientation expectedOrientation = west();
    CartesianCoordinates expectedCoordinates = new CartesianCoordinates(3, 5);

    roverCommand.getCommand().execute(rover);

    assertEquals(expectedCoordinates, rover.getCoordinates());
    assertEquals(expectedOrientation.getClass(), rover.getOrientation().getClass());
  }
}