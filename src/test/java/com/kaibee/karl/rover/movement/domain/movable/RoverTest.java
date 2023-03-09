package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.exception.ObstacleEncounteredException;
import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.grid.Grid;
import com.kaibee.karl.rover.movement.domain.grid.MarsPlateau;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.east;
import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.west;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoverTest {
  @Test
  void shouldInstantiateRoverWithValidData() {
    CartesianCoordinates coordinates = new CartesianCoordinates(1, 1);
    Grid grid = new MarsPlateau(18, 18);
    Orientation orientation = east();
    Movable rover = new Rover(coordinates, grid, orientation);

    assertNotNull(rover);
    assertEquals(coordinates, rover.getCoordinates());
    assertEquals(orientation, rover.getOrientation());
  }

  @Test
  void shouldNotInstantiateInvalidRover() {
    CartesianCoordinates coordinates = new CartesianCoordinates(1, 1);
    Grid grid = new MarsPlateau(18, 18);
    Orientation orientation = east();
    String expectedMessage = "Error creating Rover - invalid argument(s)";

    Exception rover1 = assertThrows(IllegalArgumentException.class, () -> new Rover(null, grid, orientation));
    Exception rover2 = assertThrows(IllegalArgumentException.class, () -> new Rover(coordinates, null, orientation));
    Exception rover3 = assertThrows(IllegalArgumentException.class, () -> new Rover(coordinates, grid, null));
    Exception rover4 = assertThrows(IllegalArgumentException.class, () -> new Rover(null, null, null));

    assertTrue(rover1.getMessage().contentEquals(expectedMessage));
    assertTrue(rover2.getMessage().contentEquals(expectedMessage));
    assertTrue(rover3.getMessage().contentEquals(expectedMessage));
    assertTrue(rover4.getMessage().contentEquals(expectedMessage));
  }

  @Test
  void shouldNotInstantiateRoverWithFunnyPosition() {
    CartesianCoordinates expectedCoordinates1 = new CartesianCoordinates(7, 2);
    CartesianCoordinates expectedCoordinates2 = new CartesianCoordinates(1, 1);
    CartesianCoordinates expectedCoordinates3 = new CartesianCoordinates(10, 10);
    CartesianCoordinates funnyCoordinates1 = new CartesianCoordinates(127, 12);
    CartesianCoordinates funnyCoordinates2 = new CartesianCoordinates(11, 11);
    CartesianCoordinates funnyCoordinates3 = new CartesianCoordinates(0, 0);
    Grid grid = new MarsPlateau(10, 10);
    Orientation orientation = east();

    Movable rover1 = new Rover(funnyCoordinates1, grid, orientation);
    Movable rover2 = new Rover(funnyCoordinates2, grid, orientation);
    Movable rover3 = new Rover(funnyCoordinates3, grid, orientation);

    assertEquals(expectedCoordinates1, rover1.getCoordinates());
    assertEquals(expectedCoordinates2, rover2.getCoordinates());
    assertEquals(expectedCoordinates3, rover3.getCoordinates());
  }

  @Test
  void shouldSetOrientationOrSetCoordinatesWhenExecutingCommand() {
    LandVehicle rover1 = RoverHelper.buildValidRover();
    LandVehicle rover2 = RoverHelper.buildValidRover();
    LandVehicle rover3 = RoverHelper.buildValidRover();
    LandVehicle rover4 = RoverHelper.buildValidRover();

    CartesianCoordinates rover1ActualCoordinates = rover1.getOrientation().moveForward(rover1);
    CartesianCoordinates rover2ActualCoordinates = rover2.getOrientation().moveBackward(rover2);
    Orientation rover3ActualOrientation = rover3.getOrientation().rotateLeft();
    Orientation rover4ActualOrientation = rover4.getOrientation().rotateRight();

    CartesianCoordinates expectedCoordinatesAfterMovingForward = new CartesianCoordinates(4, 5);
    CartesianCoordinates expectedCoordinatesAfterMovingBackward = new CartesianCoordinates(4, 3);
    boolean expectedOrientationAfterRotatingLeft = rover3ActualOrientation.equals(west());
    boolean expectedOrientationAfterRotatingRight = rover4ActualOrientation.equals(east());

    assertEquals(expectedCoordinatesAfterMovingForward, rover1ActualCoordinates);
    assertEquals(expectedCoordinatesAfterMovingBackward, rover2ActualCoordinates);
    assertTrue(expectedOrientationAfterRotatingLeft);
    assertTrue(expectedOrientationAfterRotatingRight);
  }

  @Test
  void shouldNotPerformMoveAndAbortSequenceWhenObstacleIsPresent() {
    Set<CartesianCoordinates> obstacles = Set.of(new CartesianCoordinates(5, 8));
    Orientation orientationStrategy = east();
    LandVehicle rover = RoverHelper.buildValidRover(4, 8, orientationStrategy, obstacles);
    String expectedMessage = "Encountered a grid obstacle at (5,8), aborting sequence";

    Exception actualException = assertThrows(ObstacleEncounteredException.class, rover::moveForward);

    assertTrue(actualException.getMessage().contentEquals(expectedMessage));
  }

  @Test
  void shouldWrapAroundAxisEdgesWhenMovingGoesBeyondMinOrMaxPoints() {
    LandVehicle xAxisMinimumRover = RoverHelper.buildValidRover(1, 7, west());
    LandVehicle xAxisMaximumRover = RoverHelper.buildValidRover(10, 7, east());
    CartesianCoordinates expectedXMinRoverCoordinates = new CartesianCoordinates(10, 7);
    CartesianCoordinates expectedXMaxRoverCoordinates = new CartesianCoordinates(1, 7);

    xAxisMinimumRover.moveForward();
    xAxisMaximumRover.moveForward();

    assertEquals(expectedXMinRoverCoordinates, xAxisMinimumRover.getCoordinates());
    assertEquals(expectedXMaxRoverCoordinates, xAxisMaximumRover.getCoordinates());
  }
}