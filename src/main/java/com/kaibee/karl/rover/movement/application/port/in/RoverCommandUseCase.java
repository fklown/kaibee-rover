package com.kaibee.karl.rover.movement.application.port.in;

import com.kaibee.karl.rover.movement.domain.movable.LandVehicle;

public interface RoverCommandUseCase {
  LandVehicle executeCommandSequence(LandVehicle rover, Character[] commandSequence);
}
