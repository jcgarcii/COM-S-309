package com.example.doodlwithpalscanvas;

import android.graphics.Path;


public class Draw
{
    public int color;
    public int stroke;
    public Path path;

    public Draw(int color, int Stroke, Path path)
    {
     this.color = color;
     this.stroke = stroke;
     this.path = path;

    }
}
