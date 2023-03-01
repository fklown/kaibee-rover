package com.kaibee.karl.rover.movement.application.port;

import com.kaibee.karl.rover.movement.application.port.in.RoverCommandUseCase;
import com.kaibee.karl.rover.movement.domain.command.RoverCommand;
import com.kaibee.karl.rover.movement.domain.exception.InvalidActionCommandException;
import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;
import java.util.Arrays;
import java.util.List;

public class RoverCommandUseCaseService implements RoverCommandUseCase {

  @Override
  public LandVehicle executeCommandSequence(LandVehicle rover, Character[] commandSequence) {
    List<RoverCommand> roverCommandSequence = getActionSequence(commandSequence);

    roverCommandSequence.forEach(roverCommand -> roverCommand.getCommand().execute(rover));

    return rover;
  }

  private List<RoverCommand> getActionSequence(Character[] commandSequence) throws IllegalArgumentException {
    return Arrays.stream(commandSequence).map(this::getActionFromCommand).toList();
  }

  private RoverCommand getActionFromCommand(Character command) {
    return RoverCommand.getRoverCommand(command).orElseThrow(() -> new InvalidActionCommandException(command));
  }


}
