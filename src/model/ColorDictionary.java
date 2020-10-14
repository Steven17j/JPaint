package model;

import java.awt.*;
import model.ShapeColor;

public class ColorDictionary {
    ShapeColor shapeColor;
    Color color;

    public ColorDictionary(ShapeColor shapeColor) { this.shapeColor = shapeColor; }

    public Color getColor() {
        switch (shapeColor) {
            case BLUE:
                color = Color.blue;
                break;
            case BLACK:
                color = Color.black;
                break;
            case CYAN:
                color = Color.CYAN;
                break;
            case DARK_GRAY:
                color = Color.DARK_GRAY;
                break;
            case GRAY:
                color = Color.GRAY;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            case LIGHT_GRAY:
                color = Color.LIGHT_GRAY;
                break;
            case MAGENTA:
                color = Color.MAGENTA;
                break;
            case ORANGE:
                color = Color.ORANGE;
                break;
            case RED:
                color = Color.RED;
                break;
            case PINK:
                color = Color.PINK;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            case YELLOW:
                color = Color.YELLOW;
                break;
        }
        return color;
        }
    }



