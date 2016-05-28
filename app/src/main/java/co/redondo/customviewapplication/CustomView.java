package co.redondo.customviewapplication;

import android.content.Context;
import android.content.res.TypedArray;
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
}
