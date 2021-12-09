package com.example.frontend.model;

import android.graphics.Path;

   /**
    * Instantiates a modified Path, a requirement for a drawing method. This draws a new "path" based on location
    * This is a helper class to the PaintView Class
    * @author : Jose Carlos Garcia
    */

public class FingerPath {

    public int givenColor;
    public int strokeWidth;
    public Path path;

    /**
    *  This draws a new "path" based on location
    * This is a helper class to the PaintView Class with the given inputs (color, strokewidth, and the current path)
    * @param givenColor - ports in retrieved color from the User/view class
    * @param strokeWidth - ports in the retrieved width from the user/View class
    * @param path - ports in established path
    */
    public FingerPath(int givenColor, int strokeWidth, Path path) {
        this.givenColor = givenColor;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}