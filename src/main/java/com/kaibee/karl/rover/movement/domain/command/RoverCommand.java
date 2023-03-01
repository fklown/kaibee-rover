package com.kaibee.karl.rover.movement.domain.command;

import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;
import java.util.Arrays;
import java.util.Optional;

public enum RoverCommand {
  B(LandVehicle::moveBackward),
  F(LandVehicle::moveForward),
  L(LandVehicle::rotateLeft),
  R(LandVehicle::rotateRight);

  private final Command command;

  RoverCommand(Command command) {
    this.command = command;
  }

  public Command getCommand() {
    return command;
  }

  public static Optional<RoverCommand> getRoverCommand(Character command) {
    String commandToCheck = String.valueOf(command).toUpperCase();
    return Arrays.stream(RoverCommand.values())
      .filter(roverCommand -> roverCommand.name().equals(commandToCheck))
      .findAny();
  }
}
