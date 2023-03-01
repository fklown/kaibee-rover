package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;

public interface Movable {
  CartesianCoordinates getCoordinates();

  Orientation getOrientation();
}
