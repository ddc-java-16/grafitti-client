package edu.cnm.deepdive.graffiti.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;

public class CanvasView extends View {

  private Canvas canvas;
  private Paint paint;


  {
    paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setStrokeWidth(2);
  }

  public void setColor(int color) {
    paint.setColor(color);
  }

  public CanvasView(Context context) {
    super(context);
  }

  public CanvasView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onDraw(@NonNull android.graphics.Canvas canvas) {
    super.onDraw(canvas);
    if (this.canvas != null) {
      for (Tag tag : this.canvas.getTags()) {
        paint.setColor(tag.getColor());
        paint.setStrokeWidth(tag.getStroke());
        if(tag.getStyle() == 1){
          paint.setStrokeCap(Cap.ROUND); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
          paint.setStrokeJoin(Join.ROUND);
        } else if (tag.getStyle() == 2) {
          paint.setStrokeCap(Cap.SQUARE); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
          paint.setStrokeJoin(Join.ROUND);
        } else {
          paint.setStrokeCap(Cap.BUTT); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
          paint.setStrokeJoin(Join.ROUND);
        }
        Point prev = null;
        for (Point point : tag.getPoints()) {
          if (prev != null) {
            canvas.drawLine(prev.getX(), prev.getY(), point.getX(), point.getY(), paint);
          }
          prev = point;
        }
      }
    }
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
    invalidate();
  }
}
