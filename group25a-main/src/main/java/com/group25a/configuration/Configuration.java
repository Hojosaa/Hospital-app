package com.group25a.configuration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Configuration {

    public static final Dimension WINDOW_DIMENSION = new Dimension(900, 500);
    public static final Dimension DIALOG_DIMENSION = new Dimension(600, 400);

    public static class ColorScheme {
        public static final Color BACKGROUND_COLOR = new Color(22, 22, 22);
    }

    public static class ForeGroundColor {
        public static final Color FOREGROUND_COLOR = new Color(245, 245, 245);
    }

    public static class BackGroundLabelColor {
        public static final Color BACKGROUNDLABEL_COLOR = new Color(90, 90, 90);
    }

    public static class ConfirmButtonColor {
        public static final Color CONFIRMBUTTON_COLOR = new Color(11, 132, 35);
    }

    public static class ButtonFont {
        public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    }

    public static class AvailabilityFont {
        public static final Font AVAILABILITY_FONT = new Font("Arial", Font.BOLD, 12);
    }

    public static class TableHeader {
        public static final Color HEADER_COLOR = new Color(40, 40, 40);
    }

    public static class BlueButton {
        public static final Color BLUEBUTTON_COLOR = new Color(3, 111, 252);
    }

    public static class GreenButton {
        public static final Color GREENBUTTON_COLOR = new Color(11, 132, 35);
    }
}
