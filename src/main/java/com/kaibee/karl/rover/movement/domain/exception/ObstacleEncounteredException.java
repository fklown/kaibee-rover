package com.kaibee.karl.rover.movement.domain.exception;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;

public class ObstacleEncounteredException extends RuntimeException {
  public ObstacleEncounteredException(CartesianCoordinates coordinates) {
    super(String.format("Encountered a grid obstacle at (%s,%s), aborting sequence",
      coordinates.x(),
      coordinates.y()));
  }
}
