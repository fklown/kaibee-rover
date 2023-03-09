package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.grid.Grid;
import com.kaibee.karl.rover.movement.domain.grid.MarsPlateau;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import java.util.Set;

import static com.kaibee.karl.rover.movement.domain.orientation.OrientationHelper.north;

public final class RoverHelper {
  public static LandVehicle buildValidRover() {
    CartesianCoordinates coordinates = new CartesianCoordinates(4, 4);
    Orientation orientation = north();
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Orientation orientation = north();
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y, Orientation orientation) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y, Orientation orientation, Set<CartesianCoordinates> obstacles) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Grid grid = new MarsPlateau(10, 10, obstacles);

    return new Rover(coordinates, grid, orientation);
  }
}
