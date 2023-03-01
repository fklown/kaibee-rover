package com.kaibee.karl.rover.movement.domain.movable;

import com.kaibee.karl.rover.movement.domain.grid.CartesianCoordinates;
import com.kaibee.karl.rover.movement.domain.grid.Grid;
import com.kaibee.karl.rover.movement.domain.grid.MarsPlateau;
import com.kaibee.karl.rover.movement.domain.orientation.Orientation;
import com.kaibee.karl.rover.movement.domain.orientation.OrientationStrategy;
import java.util.Set;

public class RoverTestHelper {
  public static LandVehicle buildValidRover() {
    CartesianCoordinates coordinates = new CartesianCoordinates(4, 4);
    Orientation orientation = OrientationStrategy.NORTH.getOrientation();
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Orientation orientation = OrientationStrategy.NORTH.getOrientation();
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y, OrientationStrategy orientationStrategy) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Orientation orientation = orientationStrategy.getOrientation();
    Grid grid = new MarsPlateau(10, 10);

    return new Rover(coordinates, grid, orientation);
  }

  public static LandVehicle buildValidRover(int x, int y, OrientationStrategy orientationStrategy, Set<CartesianCoordinates> obstacles) {
    CartesianCoordinates coordinates = new CartesianCoordinates(x, y);
    Orientation orientation = orientationStrategy.getOrientation();
    Grid grid = new MarsPlateau(10, 10, obstacles);

    return new Rover(coordinates, grid, orientation);
  }
}
