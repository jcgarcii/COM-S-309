package com.example.doodlwithpalscanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import java.util.ArrayList;

public class field {
    //establish different pencil size options:
    public static final int PEN_SIZE0 = 25; public static final int PEN_SIZE1 = 20; public static final int PEN_SIZE2 = 15;
    //establish a few colors:
    public static final int RED_COLOR = Color.RED; public static final int BLUE_COLOR = Color.BLUE; public static final int BLACK_COLOR = Color.BLACK;
    public static final int GREEN_COLOR = Color.GREEN; public static final int WHITE_COLOR = Color.WHITE; public static final int YELLOW_COLOR = Color.YELLOW;
    public static final int PURPLE_COLOR = Color.MAGENTA; public static final int GRAY_COLOR = Color.GRAY;
    //establish a touch senstivity to color:
    public static final float TOUCH_SENSITIVITY= 4;
    //establish a location on our canvas:
    private float pX, pY; private Path ppath; private Paint ppaint;
    private int background;
    //and finally, our field:
    private ArrayList<Draw> drawPad = new ArrayList<>();
    //These next few variables will serve as our "pointer" variables, to keep track of the current colors, stroke width, and such.
    private int currColor; private int thiccness; private Bitmap pBitmap; private Canvas pcanvas;
    private Paint pField = new Paint(Paint.DITHER_FLAG);

    public field(Context c, AttributeSet at)
    {
        //defines a new "paint" or input taken in from user
        ppaint = new Paint();
        //sets the input's color to black
        ppaint.setColor(BLACK_COLOR);
        //specifies how the stroke is to be filled (for this, we decide a stroke - of the previosly specified color
        ppaint.setStyle(Paint.Style.STROKE);
        //sets how we will treat lines and curve segments along the stroked path
        ppaint.setStrokeJoin(Paint.Join.ROUND);
        //set the way to "cap off" the end of a stroke series
        ppaint.setStrokeCap(Paint.Cap.ROUND);
        //sts or clears our transfer mode object (stroke from end to end)
        ppaint.setXfermode(null);
        //assigns a color's alpha values (trasnparency essentially).
        ppaint.setAlpha(0xff);
        //sets antiAliasing on for a buttery smooth stroke
        ppaint.setAntiAlias(true);
        //helps bleed the colors together to add to the drawing illusion
        ppaint.setDither(true);
    }

    private void startTouch(float x, float y)
    {
        ppath = new Path();
        Draw l = new Draw()
    }

    private void onTouch(Float x, Float y)
    {
        //tracks X + Y as it users drags finger
        float changingX = Math.abs(x-pX);
        float changingY = Math.abs(y -pY);

        //decides when to register touches
        if(changingX >= TOUCH_SENSITIVITY || changingY >= TOUCH_SENSITIVITY)
        {
            //as with any game on android, curves using a quadratic line (this is for smoothness when drawing)
            ppath.quadTo(pX, pY, (x + pX) / 2, (y+pY) /2);
            pX = x; pY = y;
        }
    }


    public void sketch(DisplayMetrics metrics)
    {
        //retrives device's width (pixels)
        int width = metrics.widthPixels;
        //retireves a device's height (pixels)
        int height = metrics.heightPixels;
        //creates a bitmap with specified width and height, each pixel value set to the  included color array
        pBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //creates a bitmap Canvas element for our using
        pcanvas = new Canvas(pBitmap);
        //sets default values for our pen + color values (black and a mid thickness for the storke)
        currColor = BLACK_COLOR;
        thiccness = PEN_SIZE0;
    }

    @Override
    public void drawing(Canvas canvas)
    {
     //TODO
    }

}

