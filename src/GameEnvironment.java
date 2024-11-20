//Hadar Pichasi

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the environment in which the game takes place.
 * This class manages a collection of collidable objects and provides methods
 * to interact with them, such as detecting collisions based on a given trajectory.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment instance.
     * Initializes an empty list of collidable objects that are part of the game.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Retrieves the list of collidable objects in the environment.
     *
     * @return a List of Collidable objects currently in the environment.
     */
    public List getColli() {
        return this.collidables;
    }


    /**
     * Adds a new collidable object to the game environment.
     *
     * @param c the Collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * Determines the closest collidable object that a given trajectory will hit.
     * The method checks all collidables in the environment to find the one with
     * the closest intersection point relative to the start of the trajectory.
     *
     * @param trajectory the Line object representing the trajectory of a moving ball.
     * @return a CollisionInfo object containing details about the closest collision.
     *         If no collisions are detected, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Collidable collidable : this.collidables) {
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());

            if (intersectionPoint != null) {
                double distance = trajectory.start().distance(intersectionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = intersectionPoint;
                    closestCollidable = collidable;
                }
            }
        }

        return closestPoint != null
                ? new CollisionInfo(closestPoint, closestCollidable)
                : null;
    }
}
