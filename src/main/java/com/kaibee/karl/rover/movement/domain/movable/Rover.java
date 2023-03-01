package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.grid.Grid;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import java.util.Objects;

public class Rover implements LandVehicle {
  private CartesianCoordinates coordinates;
  private Orientation orientation;
  private final Grid grid;

  public Rover(CartesianCoordinates coordinates, Grid grid, Orientation orientation) {
    validateRover(coordinates, grid, orientation);

    this.grid = grid;
    this.orientation = orientation;
    this.coordinates = getFinalPosition(coordinates);
  }

  private static void validateRover(CartesianCoordinates coordinates, Grid grid, Orientation orientation) {
    if (Objects.isNull(coordinates) || Objects.isNull(grid) || Objects.isNull(orientation)) {
      throw new IllegalArgumentException("Error creating Rover - invalid argument(s)");
    }
  }

  @Override
  public CartesianCoordinates getCoordinates() {
    return this.coordinates;
  }

  private void setCoordinates(CartesianCoordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  private void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  @Override
  public void rotateLeft() {
    setOrientation(getOrientation().rotateLeft());
  }

  @Override
  public void rotateRight() {
    setOrientation(getOrientation().rotateRight());
  }

  @Override
  public void moveBackward() {
    CartesianCoordinates newCoordinates = getOrientation().moveBackward(this);
    CartesianCoordinates finalCoordinates = getFinalPosition(newCoordinates);

    setCoordinates(finalCoordinates);
  }

  @Override
  public void moveForward() {
    CartesianCoordinates newCoordinates = getOrientation().moveForward(this);
    CartesianCoordinates finalCoordinates = getFinalPosition(newCoordinates);

    setCoordinates(finalCoordinates);
  }

  private CartesianCoordinates getFinalPosition(CartesianCoordinates arrivalCoordinates) {
    return grid.resolveCoordinates(arrivalCoordinates);
  }
}
