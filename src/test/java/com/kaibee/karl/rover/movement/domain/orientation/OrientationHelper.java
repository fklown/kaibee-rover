package com.kaibee.karl.rover.movement.domain.orientation;

public final class OrientationHelper {
  public static Orientation east() {
    return OrientationStrategy.EAST;
  }

  public static Orientation north() {
    return OrientationStrategy.NORTH;
  }

  public static Orientation south() {
    return OrientationStrategy.SOUTH;
  }

  public static Orientation west() {
    return OrientationStrategy.WEST;
  }
}
