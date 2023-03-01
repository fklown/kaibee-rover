package com.kaibee.karl.rover.movement.domain.movable;

public interface LandVehicle extends Movable {
  void moveBackward();

  void moveForward();

  void rotateLeft();

  void rotateRight();

}
