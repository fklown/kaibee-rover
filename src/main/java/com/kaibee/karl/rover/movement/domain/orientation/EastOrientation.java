package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;

public class EastOrientation implements Orientation {
  @Override
  public Orientation rotateLeft() {
    return new NorthOrientation();
  }

  @Override
  public Orientation rotateRight() {
    return new SouthOrientation();
  }

  @Override
  public CartesianCoordinates moveBackward(Movable movable) {
    return new CartesianCoordinates(movable.getCoordinates().x() - 1, movable.getCoordinates().y());
  }

  @Override
  public CartesianCoordinates moveForward(Movable movable) {
    return new CartesianCoordinates(movable.getCoordinates().x() + 1, movable.getCoordinates().y());
  }
}
