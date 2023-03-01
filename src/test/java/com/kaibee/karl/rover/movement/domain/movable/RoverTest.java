package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.exception.ObstacleEncounteredException;
import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.grid.Grid;
import com.kaibee.karl.rover.movement.domain.grid.MarsPlateau;
import com.kaibee.karl.rover.movement.domain.orientation.EastOrientation;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import com.kaibee.karl.rover.movement.domain.orientation.OrientationStrategy;
import com.kaibee.karl.rover.movement.domain.orientation.WestOrientation;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoverTest {
  @Test
  void shouldInstantiateRoverWithValidData() {
    CartesianCoordinates coordinates = new CartesianCoordinates(1, 1);
    Grid grid = new MarsPlateau(18, 18);
    Orientation orientation = OrientationStrategy.EAST.getOrientation();
    Movable rover = new Rover(coordinates, grid, orientation);

    assertNotNull(rover);
    assertEquals(coordinates, rover.getCoordinates());
    assertEquals(orientation, rover.getOrientation());
  }

  @Test
  void shouldNotInstantiateInvalidRover() {
    CartesianCoordinates coordinates = new CartesianCoordinates(1, 1);
    Grid grid = new MarsPlateau(18, 18);
    Orientation orientation = OrientationStrategy.EAST.getOrientation();
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
    Orientation orientation = OrientationStrategy.EAST.getOrientation();

    Movable rover1 = new Rover(funnyCoordinates1, grid, orientation);
    Movable rover2 = new Rover(funnyCoordinates2, grid, orientation);
    Movable rover3 = new Rover(funnyCoordinates3, grid, orientation);

    assertEquals(expectedCoordinates1, rover1.getCoordinates());
    assertEquals(expectedCoordinates2, rover2.getCoordinates());
    assertEquals(expectedCoordinates3, rover3.getCoordinates());
  }

  @Test
  void shouldSetOrientationOrSetCoordinatesWhenExecutingCommand() {
    LandVehicle rover1 = RoverTestHelper.buildValidRover();
    LandVehicle rover2 = RoverTestHelper.buildValidRover();
    LandVehicle rover3 = RoverTestHelper.buildValidRover();
    LandVehicle rover4 = RoverTestHelper.buildValidRover();

    CartesianCoordinates rover1ActualCoordinates = rover1.getOrientation().moveForward(rover1);
    CartesianCoordinates rover2ActualCoordinates = rover2.getOrientation().moveBackward(rover2);
    Orientation rover3ActualOrientation = rover3.getOrientation().rotateLeft();
    Orientation rover4ActualOrientation = rover4.getOrientation().rotateRight();

    CartesianCoordinates expectedCoordinatesAfterMovingForward = new CartesianCoordinates(4, 5);
    CartesianCoordinates expectedCoordinatesAfterMovingBackward = new CartesianCoordinates(4, 3);
    boolean expectedOrientationAfterRotatingLeft = rover3ActualOrientation instanceof WestOrientation;
    boolean expectedOrientationAfterRotatingRight = rover4ActualOrientation instanceof EastOrientation;

    assertEquals(expectedCoordinatesAfterMovingForward, rover1ActualCoordinates);
    assertEquals(expectedCoordinatesAfterMovingBackward, rover2ActualCoordinates);
    assertTrue(expectedOrientationAfterRotatingLeft);
    assertTrue(expectedOrientationAfterRotatingRight);
  }

  @Test
  void shouldNotPerformMoveAndAbortSequenceWhenObstacleIsPresent() {
    Set<CartesianCoordinates> obstacles = Set.of(new CartesianCoordinates(5, 8));
    OrientationStrategy orientationStrategy = OrientationStrategy.EAST;
    LandVehicle rover = RoverTestHelper.buildValidRover(4, 8, orientationStrategy, obstacles);
    String expectedMessage = "Encountered a grid obstacle at (5,8), aborting sequence";

    Exception actualException = assertThrows(ObstacleEncounteredException.class, rover::moveForward);

    assertTrue(actualException.getMessage().contentEquals(expectedMessage));
  }

  @Test
  void shouldWrapAroundAxisEdgesWhenMovingGoesBeyondMinOrMaxPoints() {
    LandVehicle xAxisMinimumRover = RoverTestHelper.buildValidRover(1, 7, OrientationStrategy.WEST);
    LandVehicle xAxisMaximumRover = RoverTestHelper.buildValidRover(10, 7, OrientationStrategy.EAST);
    CartesianCoordinates expectedXMinRoverCoordinates = new CartesianCoordinates(10, 7);
    CartesianCoordinates expectedXMaxRoverCoordinates = new CartesianCoordinates(1, 7);

    xAxisMinimumRover.moveForward();
    xAxisMaximumRover.moveForward();

    assertEquals(expectedXMinRoverCoordinates, xAxisMinimumRover.getCoordinates());
    assertEquals(expectedXMaxRoverCoordinates, xAxisMaximumRover.getCoordinates());
  }
}