package com.kaibee.karl.rover.movement.domain.grid;

public interface Grid {
  int getOrigin();

  int getXBound();

  int getYBound();

  boolean isOnGrid(CartesianCoordinates cartesianCoordinates);

  boolean isObstaclePresent(CartesianCoordinates cartesianCoordinates);

  CartesianCoordinates resolveCoordinates(CartesianCoordinates arrivalCoordinates);
}
