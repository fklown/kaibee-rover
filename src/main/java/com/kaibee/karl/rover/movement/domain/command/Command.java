package com.kaibee.karl.rover.movement.domain.command;

import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;

public interface Command {
  void execute(LandVehicle rover);
}
