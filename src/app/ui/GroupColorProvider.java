package app.ui;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GroupColorProvider {

    private final Map<String, Color> groupColors = new HashMap<>();
    private final Random random = new Random();

    // Colors to avoid (too common, too dark, too bright, or undesired)
    private static final List<Color> FORBIDDEN_COLORS = List.of(
        Color.BLUE,
        Color.BLACK,
        Color.WHITE,
        Color.GRAY,
        Color.DARKBLUE,
        Color.NAVY,
        Color.DARKGRAY
    );

    /**
     * Returns a color assigned to the group, generating a new one if needed.
     */
    public Color getColorForGroup(String groupName) {
        if (groupName == null) return Color.GRAY;

        String key = groupName.toLowerCase();

        if (!groupColors.containsKey(key)) {
            Color newColor;
            int attempts = 0;

            do {
                newColor = generatePastelColor();
                attempts++;
            }
            while ((isTooCloseToExistingColors(newColor) || isTooCloseToForbiddenColors(newColor)) && attempts < 30);

            groupColors.put(key, newColor);
        }

        return groupColors.get(key);
    }

    private Color generatePastelColor() {
        double hue = random.nextDouble() * 360;
        double saturation = 0.4 + random.nextDouble() * 0.2;
        double brightness = 0.7 + random.nextDouble() * 0.2;
        return Color.hsb(hue, saturation, brightness);
    }

    private boolean isTooCloseToExistingColors(Color newColor) {
        for (Color existing : groupColors.values()) {
            if (colorDistance(existing, newColor) < 0.2) {
                return true;
            }
        }
        return false;
    }

    private boolean isTooCloseToForbiddenColors(Color newColor) {
        for (Color forbidden : FORBIDDEN_COLORS) {
            if (colorDistance(forbidden, newColor) < 0.25) {
                return true;
            }
        }
        return false;
    }

    private double colorDistance(Color c1, Color c2) {
        double dr = c1.getRed() - c2.getRed();
        double dg = c1.getGreen() - c2.getGreen();
        double db = c1.getBlue() - c2.getBlue();
        return Math.sqrt(dr * dr + dg * dg + db * db);
    }

    public void clear() {
        groupColors.clear();
    }
}
