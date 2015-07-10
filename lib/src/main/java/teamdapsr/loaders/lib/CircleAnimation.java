package teamdapsr.loaders.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import teamdapsr.loaders.lib.utils.MeasureUtils;

/**
 * Created by rajanmaurya on 9/7/15.
 */
public class CircleAnimation extends View{


    int rectside;
    private Paint mSquarePaint;
    private Paint mSquarePaintFill;
    RectF rect1 = new RectF(50, 50, 150, 150);
    private float mProgress = 0.0f;
    public CircleAnimation(Context context, int rectside) {
        super(context);
        this.rectside = rectside;
    }

    public CircleAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleAnimation,
                0, 0
        );

        try
        {
            rectside = a.getInt(R.styleable.CircleAnimation_rectside, 10);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            a.recycle();
        }
        init();

    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Get the width measurement
        int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, getDesiredWidth());

        // Get the height measurement
        int heightSize = MeasureUtils.getMeasurement(heightMeasureSpec, getDesiredHeight());

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize+10, heightSize+10);
    }

    private int getDesiredWidth()
    {
        // TO-DO Calculate width from child components.

        return 2*rectside+200;
    }

    private int getDesiredHeight()
    {
        // TO-DO Calculate height from chile components.
        return 2*rectside+200;
    }


    private void init()
    {
        mSquarePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSquarePaint.setColor(getResources().getColor(R.color.Material));
        mSquarePaint.setStyle(Paint.Style.STROKE);
        mSquarePaint.setStrokeWidth(5);
        mSquarePaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSquarePaintFill.setColor(0xffffffff);
        mSquarePaintFill.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        boolean big = true ,small = false ,medium = false;



        if(big){
            canvas.drawArc(rect1, 0, 360, false, mSquarePaint);
            small = true;
            big = false;
            invalidate();
        }else if (small){
            canvas.drawArc(rect1, 0, 135, false, mSquarePaint);
            medium = true;
            small = false;
        }else if (medium){
            canvas.drawArc(rect1, 90, 135, false, mSquarePaint);
            medium = false;
            big = true;
        }



        invalidate();
       // requestLayout();
    }


    }

