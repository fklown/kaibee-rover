package com.kaibee.karl.rover.movement.domain.grid;

import com.kaibee.karl.rover.movement.domain.exception.ObstacleEncounteredException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class MarsPlateau implements Grid {
  private static final int ORIGIN = 1;
  private final Set<CartesianCoordinates> obstacles;
  private final int xBound;
  private final int yBound;

  /**
   * Since I chose to go without the
   * polar coordinates approach for now,
   * this grid is meant to behave like a
   * plateau wrapped around Mars.
   * -
   * As such, for it to "exist" it shall not
   * accept negative cartesian coordinates as bounds.
   * -
   * Grid coordinates intersections are rather a space
   * than a point on a plane.
   * Thus, their origin is the space located at (1,1).
   *
   * @param xBound
   *   the maximum range on X (horizontal) axis
   * @param yBound
   *   the maximum range on Y (vertical) axis
   * @param obstacles
   *   coordinates where a rover shall
   *   encounter obstacles
   */
  public MarsPlateau(int xBound, int yBound, Set<CartesianCoordinates> obstacles) {
    validateBounds(xBound, yBound);
    this.xBound = xBound;
    this.yBound = yBound;

    validateObstacles(obstacles);
    this.obstacles = obstacles;
  }

  public MarsPlateau(int xBound, int yBound) {
    validateBounds(xBound, yBound);
    this.xBound = xBound;
    this.yBound = yBound;

    this.obstacles = Collections.emptySet();
  }

  private static void validateBounds(int xBound, int yBound) {
    if (xBound < 1 || yBound < 1) {
      throw new IllegalArgumentException("Error creating Mars plateau - invalid bounds");
    }
  }

  private void validateObstacles(Set<CartesianCoordinates> obstacles) {
    Optional<CartesianCoordinates> obstacleOutOfGrid = obstacles.stream()
      .filter(cartesianCoordinates -> !isOnGrid(cartesianCoordinates))
      .findFirst();

    if (obstacleOutOfGrid.isPresent()) {
      throw new IllegalArgumentException("Error creating Mars plateau - invalid obstacle bounds");
    }
  }

  @Override
  public boolean isOnGrid(CartesianCoordinates coordinates) {
    return isOnAxis(coordinates.x(), getXBound()) && isOnAxis(coordinates.y(), getYBound());
  }

  private Set<CartesianCoordinates> getObstacles() {
    return this.obstacles;
  }

  @Override
  public int getOrigin() {
    return ORIGIN;
  }

  @Override
  public int getXBound() {
    return this.xBound;
  }

  @Override
  public int getYBound() {
    return this.yBound;
  }

  private boolean isOnAxis(int coordinate, int axisMaxBound) {
    return coordinate >= getOrigin() && coordinate <= axisMaxBound;
  }

  @Override
  public boolean isObstaclePresent(CartesianCoordinates coordinates) {
    CartesianCoordinates positionToCheck = new CartesianCoordinates(coordinates.x(), coordinates.y());
    return getObstacles().contains(positionToCheck);
  }

  @Override
  public CartesianCoordinates resolveCoordinates(CartesianCoordinates arrivalCoordinates) {
    int xPosition = resolveAxisPosition(arrivalCoordinates.x(), getXBound());
    int yPosition = resolveAxisPosition(arrivalCoordinates.y(), getYBound());

    CartesianCoordinates finalCoordinates = new CartesianCoordinates(xPosition, yPosition);

    if (isObstaclePresent(finalCoordinates)) {
      throw new ObstacleEncounteredException(finalCoordinates);
    }

    return finalCoordinates;
  }

  private int resolveAxisPosition(int position, int axisBound) {
    if (isOnAxis(position, axisBound)) {
      return position;
    }
    return wrapPositionAroundAxis(axisBound, position);
  }

  private int wrapPositionAroundAxis(int axisBound, int position) {
    if (position <= 0) {
      return axisBound + position;
    }
    return position - axisBound;
  }
}
