//Hadar Pinchasi


/**
 * An interface for objects that notify listeners of hit events.
 * Classes implementing this interface allow other objects to register
 * as listeners to receive notifications about hit events.
 */

public interface HitNotifier {
    /**
     * Adds the specified HitListener as a listener to hit events.
     *
     * @param hl the HitListener to be added to the list of listeners.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified HitListener from the list of listeners.
     *
     * @param hl the HitListener to be removed from the list.
     */
    void removeHitListener(HitListener hl);

    }

