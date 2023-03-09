package com.kaibee.karl.rover.movement.domain.orientation;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.movable.Movable;

public enum OrientationStrategy implements Orientation {
  NORTH("WEST", "EAST", new CartesianCoordinates(0, 1), new CartesianCoordinates(0, -1)),
  SOUTH("EAST", "WEST", new CartesianCoordinates(0, -1), new CartesianCoordinates(0, 1)),
  EAST("NORTH", "SOUTH", new CartesianCoordinates(1, 0), new CartesianCoordinates(-1, 0)),
  WEST("SOUTH", "NORTH", new CartesianCoordinates(-1, 0), new CartesianCoordinates(1, 0));

  private final String leftRotation;
  private final String rightRotation;
  private final CartesianCoordinates forwardMovement;
  private final CartesianCoordinates backwardMovement;

  OrientationStrategy(
    String leftRotation,
    String rightRotation,
    CartesianCoordinates forwardMovement,
    CartesianCoordinates backwardMovement) {
    this.leftRotation = leftRotation;
    this.rightRotation = rightRotation;
    this.forwardMovement = forwardMovement;
    this.backwardMovement = backwardMovement;
  }

  public OrientationStrategy rotateLeft() {
    return OrientationStrategy.valueOf(leftRotation);
  }

  public OrientationStrategy rotateRight() {
    return OrientationStrategy.valueOf(rightRotation);
  }

  public CartesianCoordinates moveForward(Movable movable) {
    return mergeCoordinates(movable.getCoordinates(), forwardMovement);
  }

  public CartesianCoordinates moveBackward(Movable movable) {
    return mergeCoordinates(movable.getCoordinates(), backwardMovement);
  }

  private CartesianCoordinates mergeCoordinates(CartesianCoordinates position, CartesianCoordinates movement) {
    int xAxis = position.x() + movement.x();
    int yAxis = position.y() + movement.y();

    return new CartesianCoordinates(xAxis, yAxis);
  }
}
