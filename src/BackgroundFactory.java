import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BackgroundFactory is a factory class for creating various background designs
 * for different levels of a game. Each background is represented as a Sprite
 * that implements dynamic and static graphical elements.
 */
public class BackgroundFactory {
    // X-coordinate of the sun for the "Wide Easy" level background
    private static int sunX = 100;

    // List of raindrops for the "It's raining outside" level background
    private static final List<Raindrop> raindrops = new ArrayList<>();

    // Static initializer to populate the raindrops list
    static {
        // Initialize raindrops with random positions
        for (int i = 0; i < 60; i++) {
            double rainX = Math.random() * 800; // Random x-coordinate
            double rainY = Math.random() * 600; // Random y-coordinate
            raindrops.add(new Raindrop(rainX, rainY, 10, 110));
        }
    }

    /**
     * Creates a background for the specified level name.
     *
     * @param levelName The name of the level.
     * @return A Sprite representing the background for the level.
     */
    public static Sprite createBackground(String levelName) {
        // Background for the "Direct Hit" level
        if (levelName.equals("Direct Hit")) {
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    int[] radii = {60, 100, 140};

                    // Draw black background
                    d.setColor(Color.black);
                    d.fillRectangle(0, 0, 800, 600);

                    // Draw red circles at the center
                    d.setColor(Color.red);
                    int centerX = 422;
                    int centerY = 224;
                    for (int radius : radii) {
                        d.drawCircle(centerX, centerY, radius);
                    }

                    // Draw crosshair lines extending from the center
                    int[][] lineCoordinates = {
                            {centerX + 160, centerY},
                            {centerX - 162, centerY},
                            {centerX, centerY + 162},
                            {centerX, centerY - 162}
                    };
                    for (int[] coords : lineCoordinates) {
                        d.drawLine(centerX, centerY, coords[0], coords[1]);
                    }
                }

                @Override
                public void timePassed() {
                    // No animation for this background
                }
            };
        }

        // Background for the "Wide Easy" level
        if (levelName.equals("Wide Easy")) {
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface surface) {
                    // Draw cyan background
                    surface.setColor(Color.cyan);
                    surface.fillRectangle(0, 0, 800, 600);

                    // Draw the sun
                    int sunY = 150;
                    surface.setColor(Color.yellow);
                    surface.fillCircle(sunX, sunY, 45);

                    // Draw sun rays
                    for (int i = 0; i < 200; i += 10) {
                        surface.drawLine(sunX, sunY + 20, i * 8, 300);
                    }
                }

                @Override
                public void timePassed() {
                    // Animate the sun moving to the right
                    sunX += 1;
                    if (sunX > 800) { // Reset position if sun moves out of view
                        sunX = 0;
                    }
                }
            };
        }

        // Background for the "It's raining outside" level
        if (levelName.equals("It's raining outside")) {
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    // Draw blue background
                    d.setColor(new Color(0x1B77DC));
                    d.fillRectangle(0, 0, 800, 600);

                    // Draw raindrops
                    d.setColor(new Color(200, 200, 255));
                    for (Raindrop drop : raindrops) {
                        d.drawLine((int) drop.getX(), (int) drop.getY(),
                                (int) (drop.getX() - 10), (int) (drop.getY() + 20));
                    }

                    // Draw cloud groups
                    drawCloudGroup(d, 100, 80); // First cloud group
                    drawCloudGroup(d, 550, 400); // Second cloud group
                }

                @Override
                public void timePassed() {
                    // Animate raindrops falling
                    for (Raindrop drop : raindrops) {
                        drop.move();
                    }
                }

                private void drawCloudGroup(DrawSurface d, int x, int y) {
                    // Draw a group of overlapping circles to create a cloud
                    d.setColor(new Color(0xE0E0E0));
                    d.fillCircle(x, y, 20);
                    d.fillCircle(x + 20, y + 20, 20);

                    d.setColor(new Color(0xC0C0C0));
                    d.fillCircle(x + 30, y - 12, 20);

                    d.setColor(new Color(0xA0A0A0));
                    d.fillCircle(x + 35, y + 20, 20);
                    d.fillCircle(x + 50, y, 20);
                    d.fillCircle(x + 60, y + 15, 20);
                }
            };
        }

        // Background for the "Working Hard" level
        if (levelName.equals("Working Hard")) {
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    // Draw blue background
                    d.setColor(new Color(0x1B98DC));
                    d.fillRectangle(0, 0, 800, 600);

                    // Draw buildings and antennas
                    drawBuildings(d);
                }

                private void drawBuildings(DrawSurface d) {
                    int[][] buildingProperties = {
                            {100, 200, 100, 400, 0x333333},
                            {360, 250, 90, 350, 0x222222},
                            {470, 350, 140, 250, 0x555555},
                            {630, 280, 110, 320, 0x333333}
                    };

                    for (int i = 0; i < buildingProperties.length; i++) {
                        int x = buildingProperties[i][0];
                        int y = buildingProperties[i][1];
                        int width = buildingProperties[i][2];
                        int height = buildingProperties[i][3];
                        int color = buildingProperties[i][4];

                        // Draw building
                        d.setColor(new Color(color));
                        d.fillRectangle(x, y, width, height);

                        // Draw windows for all except the third building
                        if (i != 2) {
                            drawWindows(d, x, y, width, height);
                        }
                    }
                }

                private void drawWindows(DrawSurface d, int buildingX, int buildingY, int buildingWidth, int buildingHeight) {
                    d.setColor(new Color(0xFFFF99));
                    int windowWidth = 15;
                    int windowHeight = 25;
                    int windowSpacingY = 40;
                    int windowSpacingX = 25;

                    for (int y = buildingY + 20; y < buildingY + buildingHeight - windowHeight; y += windowSpacingY) {
                        for (int x = buildingX + 5; x < buildingX + buildingWidth - windowWidth; x += windowSpacingX) {
                            d.fillRectangle(x, y, windowWidth, windowHeight);
                        }
                    }
                }

                @Override
                public void timePassed() {
                    // No animation for this background
                }
            };
        }

        // Default gray background for unknown levels
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.GRAY);
                d.fillRectangle(0, 0, 800, 600);
            }

            @Override
            public void timePassed() {
                // No animation in default background
            }
        };
    }
}
