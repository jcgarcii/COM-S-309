package com.example.frontend.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
* Instantiates a new modified View class, presenting a new "paint" for our drawing method
* Our drawing class implements four required classes for a drawing method - a bitmap, Canvas, path, and a metrics method
* @author : Jose Carlos Garcia
*/
public class PaintView extends View
{
    // establishes variables to keep track of our user's movement, colors, and other options
    private float dX, dY; private Path pencil; private final Paint mPaint; private final ArrayList<FingerPath> paths = new ArrayList<>();
    // variables to collect user's inputs
    private int collectedColor; private int strokeWidth;
    //Required libraries to be used in a Drawing method and class
    private Bitmap mBitmap; private Canvas mCanvas; private final Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    /**
    * Instantiates a modified new View method, with context from our Activity
    * @param context from the activity
    */
    public PaintView(Context context)
    {
        this(context, null);
    }

    /**
    * Instantiates a modified new View method with an attribute from the XML file
    * and context from our Activity as well as instantiating most Paint methods from the Paint class/library
    * @param context from XML
    * @param attrs from Activity
    */  
    public PaintView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(collectedColor);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    /**
    * Collects color from the user baed on their input in-game
    * @param color - user's input based on buttons
    */
    public void setColor(int color)
    {
        collectedColor = color;
    }

    /**
    * Instantiates both the bitmap class and the Canvas class as required for a drawing class. 
    * also sets the color and stroke width based on user input (again, based on buttons)
    * @param metrics : used for keep track and avoid user from going out of bounds of their screen
    */
    public void init(DisplayMetrics metrics)
    {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        //SET STROKE WIDTH BASED ON COLOR (WHITE ACTS AS AN ERASER)
        if(collectedColor == Color.WHITE) { strokeWidth = 100; }
        else { strokeWidth = 20; }
    }

    /**
    * Overrides View's onDraw method with the previously instantiated Canvas. 
    * This method also instantiates the Paint method, feeding in the Android Path library into its values.  
    */
    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.save();
        mCanvas.drawColor(Color.WHITE);
        for (FingerPath fp : paths)
        {
            mPaint.setColor(fp.givenColor);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mCanvas.drawPath(fp.path, mPaint);
        }
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    /**
    * Establishes a new modified path class to keep track of the user's actions. 
    * For this method, we are interested in how the user interacts with the Canvas moves the "cursor" to their first position
    * @param x - tracks float x on the horizontal plane of the screen
    * @param y - tracks float y on the vertical plane of the screen
    */
    private void touchStart(float x, float y)
    {
        pencil = new Path();
        FingerPath fp = new FingerPath(collectedColor, strokeWidth, pencil);
        paths.add(fp);
        pencil.reset();
        pencil.moveTo(x, y);
        dX = x;
        dY = y;
    }

    /**
    * Track's the user's change of position on their screen (where they moved to, in terms of their finger)
    * This works so long that the touchsensitivity is more than 4, a standard in drawing classes
    * @param x - float x ports in the previous location 
    * @param y - float y ports in the previous location
    */
    private void touchMove(float x, float y)
    {
        float dx = Math.abs(x - dX);
        float dy = Math.abs(y - dY);

        if (dx >= 4 || dy >= 4)
        {
            pencil.quadTo(dX, dY, (x + dX) / 2, (y + dY) / 2);
            dX = x;
            dY = y;
        }
    }

    /*
    * Draws a stroke form the old position to the new Position
    *
    */
    private void touchUp()
    {
        pencil.lineTo(dX, dY);
    }

    /**
    * Overrides the onTouchEvent, on a new event (in this case the user looking to draw on the screen)
    * then tracks where the user first started drawing, to their changing x and y - presenting a stroke for every change. 
    * Antialaising techniques within the Bitmap allow for it to look smooth and silky. 
    * @param event - ports in a new event from the activity. 
    */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }
        return true;
    }
}