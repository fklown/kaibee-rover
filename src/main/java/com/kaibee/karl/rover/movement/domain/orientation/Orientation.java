package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;

public interface Orientation {
  Orientation rotateLeft();

  Orientation rotateRight();

  CartesianCoordinates moveBackward(Movable vehicle);

  CartesianCoordinates moveForward(Movable vehicle);
}
