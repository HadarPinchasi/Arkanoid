/**
 * A class representing a single raindrop
 */
class Raindrop {
    private double x;
    private double y;
    private final double speed;
    private final double angle;

    public Raindrop(double x, double y, double speed, double angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
    }

    public void move() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;

        // Reset position when drop goes below screen
        if (y > 600) {
            y = -20;
            x = Math.random() * 800;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
