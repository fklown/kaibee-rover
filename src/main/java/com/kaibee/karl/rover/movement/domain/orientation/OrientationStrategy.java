package com.kaibee.karl.rover.movement.domain.orientation;

public enum OrientationStrategy {
  NORTH(new NorthOrientation()),
  SOUTH(new SouthOrientation()),
  EAST(new EastOrientation()),
  WEST(new WestOrientation());

  private final Orientation orientation;

  OrientationStrategy(Orientation orientation) {
    this.orientation = orientation;
  }

  public Orientation getOrientation() {
    return orientation;
  }
}
