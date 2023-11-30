package edu.cnm.deepdive.graffiti.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.graffiti.model.Point;
import java.util.List;
import java.util.stream.IntStream;

public class CanvasView extends View {

  private List<Point> points;
  private Paint paint;
  private float[] drawPoints;


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
  protected void onDraw(@NonNull Canvas canvas) {
    super.onDraw(canvas);
    if (points != null) {
      Point prev = null;
      for (Point point : points) {
        if(prev != null) {
          canvas.drawLine(prev.getX(),prev.getY(),point.getX(),point.getY(), paint);
        }
        prev = point;
      }
    }
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
    drawPoints = new float[points.size() * 2];
    int[] counter = {0};
    points
        .stream()
        .flatMapToInt((p) -> IntStream.of(p.getX(), p.getY()))
        .forEach((v) -> drawPoints[counter[0]++] = v);
  }
}
