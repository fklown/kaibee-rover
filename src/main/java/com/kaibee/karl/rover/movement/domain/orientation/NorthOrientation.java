package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;

public class NorthOrientation implements Orientation {
  @Override
  public Orientation rotateLeft() {
    return new WestOrientation();
  }

  @Override
  public Orientation rotateRight() {
    return new EastOrientation();
  }

  @Override
  public CartesianCoordinates moveBackward(Movable movable) {
    return new CartesianCoordinates(movable.getCoordinates().x(), movable.getCoordinates().y() - 1);
  }

  @Override
  public CartesianCoordinates moveForward(Movable movable) {
    return new CartesianCoordinates(movable.getCoordinates().x(), movable.getCoordinates().y() + 1);
  }
}
