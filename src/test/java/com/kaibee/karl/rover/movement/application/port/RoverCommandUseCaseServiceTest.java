package com.kaibee.karl.rover.movement.application.port;

import com.kaibee.karl.rover.movement.application.port.in.RoverCommandUseCase;
import com.kaibee.karl.rover.movement.domain.exception.InvalidActionCommandException;
import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;
import com.kaibee.karl.rover.movement.domain.orientation.EastOrientation;
import com.kaibee.karl.rover.movement.domain.orientation.NorthOrientation;
import org.junit.jupiter.api.Test;

import static com.kaibee.karl.rover.movement.domain.movable.RoverTestHelper.buildValidRover;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverCommandUseCaseServiceTest {

  RoverCommandUseCase roverCommandUseCaseService = new RoverCommandUseCaseService();

  private static final Character ERRONEOUS_COMMAND_1 = 'z';
  private static final Character ERRONEOUS_COMMAND_2 = '1';
  private static final Character LEFT_ROTATION_COMMAND = 'l';
  private static final Character RIGHT_ROTATION_COMMAND = 'r';
  private static final Character BACKWARD_MOVEMENT_COMMAND = 'b';
  private static final Character FORWARD_MOVEMENT_COMMAND = 'f';

  @Test
  void shouldNotApplyErroneousCommandsToRover() {
    Character[] actionSequence1 = new Character[]{ERRONEOUS_COMMAND_1};
    Character[] actionSequence2 = new Character[]{ERRONEOUS_COMMAND_2};
    LandVehicle rover = buildValidRover(3, 3);
    String expectedMessage1 = String.format("Error reading unsupported action command %s", actionSequence1[0]);
    String expectedMessage2 = String.format("Error reading unsupported action command %s", actionSequence2[0]);

    Exception resultingException1 = assertThrows(InvalidActionCommandException.class, () -> roverCommandUseCaseService.executeCommandSequence(rover, actionSequence1));
    Exception resultingException2 = assertThrows(InvalidActionCommandException.class, () -> roverCommandUseCaseService.executeCommandSequence(rover, actionSequence2));

    assertEquals(expectedMessage1, resultingException1.getMessage());
    assertEquals(expectedMessage2, resultingException2.getMessage());
  }

  @Test
  void shouldApplyValidCommandsToRover() {
    Character[] actionSequence1 = validCommandCharacters();
    Character[] actionSequence2 = new Character[]{LEFT_ROTATION_COMMAND, LEFT_ROTATION_COMMAND, LEFT_ROTATION_COMMAND, FORWARD_MOVEMENT_COMMAND};
    LandVehicle rover1 = buildValidRover(2, 2);
    LandVehicle rover2 = buildValidRover(2, 2);
    CartesianCoordinates expectedCoordinates1 = new CartesianCoordinates(2, 2);
    CartesianCoordinates expectedCoordinates2 = new CartesianCoordinates(3, 2);

    LandVehicle actual1 = roverCommandUseCaseService.executeCommandSequence(rover1, actionSequence1);
    LandVehicle actual2 = roverCommandUseCaseService.executeCommandSequence(rover2, actionSequence2);

    assertEquals(NorthOrientation.class, actual1.getOrientation().getClass());
    assertEquals(expectedCoordinates1, actual1.getCoordinates());
    assertEquals(EastOrientation.class, actual2.getOrientation().getClass());
    assertEquals(expectedCoordinates2, actual2.getCoordinates());
  }

  private static Character[] validCommandCharacters() {
    return new Character[]{LEFT_ROTATION_COMMAND, RIGHT_ROTATION_COMMAND, BACKWARD_MOVEMENT_COMMAND, FORWARD_MOVEMENT_COMMAND};
  }
}