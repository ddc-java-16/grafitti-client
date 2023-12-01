package edu.cnm.deepdive.graffiti.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
  }
}
