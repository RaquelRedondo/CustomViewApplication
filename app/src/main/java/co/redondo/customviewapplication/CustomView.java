package co.redondo.customviewapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View{

    //Declare color variables
    private int circleColor, labelColor;

    //Declare label variable
    private String circleLabel;

    //Declare Paint variable for painting
    private Paint circlePaint;

    //Declare rotation variable
    private float rotation = 0;

    public CustomView (Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint object for drawing in onDraw
        circlePaint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);


        try {
            circleColor = a.getInteger(R.styleable.CustomView_circleColor, 0); //0 is default
            labelColor = a.getInteger(R.styleable.CustomView_labelColor, 0);
            circleLabel = a.getString(R.styleable.CustomView_circleLabel);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Lets get some information about the available space
        int heightHalf  = this.getMeasuredHeight()/2;
        int widthHalf = this.getMeasuredWidth()/2;

        //Calculate the radius
        int radius = 500;
        if (widthHalf>heightHalf){
            radius = widthHalf - 200;
        }else {
            radius = heightHalf - 10;
        }

        //Set some properties for painting
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        //Set the circle color
        circlePaint.setColor(circleColor);

        //Do the rotation before drawing into the canvas
        canvas.save();
        canvas.rotate(rotation, widthHalf,heightHalf);

        //Draw the circle
        canvas.drawCircle(widthHalf, heightHalf, radius/2, circlePaint);

        //Set the color, size and alignment of text
        circlePaint.setColor(labelColor);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(100);

        //Draw the text
        canvas.drawText(circleLabel,widthHalf,heightHalf,circlePaint);

        //I don't know what this method does
        canvas.restore();
    }

    //Create setters and getters
    public int getCircleColor(){
        return circleColor;
    }

    public int getLabelColor(){
        return labelColor;
    }

    public String getLabelText(){
        return circleLabel;
    }

    public void setCircleColor(int newColor){
        //update the instance variable
        circleColor=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }
    public void setLabelColor(int newColor){
        //update the instance variable
        labelColor=newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel){
        //update the instance variable
        circleLabel=newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void rotate(){
        rotation += 45;
        invalidate();
        requestLayout();
    }

}
