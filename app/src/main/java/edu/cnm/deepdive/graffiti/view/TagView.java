package edu.cnm.deepdive.graffiti.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.graffiti.R;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;
import java.util.LinkedList;
import java.util.function.Consumer;

public class TagView extends View implements OnTouchListener {


  private Tag tag;
  private Consumer<Tag> tagConsumer;
  private MediaPlayer mediaPlayer;
  private Paint paint;
  private int style;

  {
    this.setOnTouchListener(this);
    paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setStrokeWidth(2);
  }

  public TagView(Context context) {
    super(context);
  }

  public TagView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    boolean handled = handleMotionEvent(event);
    if(handled){
      invalidate();
    }
    return handled;
  }

  @Override
  protected void onDraw(@NonNull Canvas canvas) {
    super.onDraw(canvas);
    if(tag != null) {

      Point prev = null;
      for (Point point : tag.getPoints()) {
        if (prev != null) {
          canvas.drawLine(prev.getX(), prev.getY(), point.getX(), point.getY(), paint);
        }
        prev = point;
      }
    }
  }

  public void setTagConsumer(Consumer<Tag> tagConsumer) {
    this.tagConsumer = tagConsumer;
  }

  public void setStrokeColor(int color) {
    paint.setColor(color);
    invalidate();
  }

  public void setStrokeWidth(float width) {
    paint.setStrokeWidth(width);
    invalidate();
  }

  public void setStrokeStyle(int style) {
    this.style = style;
    if(style == 1){
      paint.setStrokeCap(Cap.ROUND); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
      paint.setStrokeJoin(Join.ROUND);
    } else if (style == 2) {
      paint.setStrokeCap(Cap.SQUARE); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
      paint.setStrokeJoin(Join.ROUND);
    } else {
      paint.setStrokeCap(Cap.BUTT); //Cap.ROUND Join.ROUND -- Cap.SQUARE Join.ROUND -- Cap.BUTT Join.ROUND
      paint.setStrokeJoin(Join.ROUND);
    }
    invalidate();
  }


  private boolean handleMotionEvent(MotionEvent event) {
    boolean handled = true;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN -> {
        tag = new Tag();
        tag.getPoints().add(getPoint(event));
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.spray3);
        mediaPlayer.start();

      }
      case MotionEvent.ACTION_MOVE -> {
        tag.getPoints().add(getPoint(event));
        //mediaPlayer.setLooping(true);

      }
      case MotionEvent.ACTION_UP -> {
        tag.getPoints().add(getPoint(event));
        tag.setColor(paint.getColor());
        tag.setStroke((int) paint.getStrokeWidth());
        tag.setStyle(style);
        if (tagConsumer != null) {
          tagConsumer.accept(tag);
        }
        tag = null;
        mediaPlayer.release();
        mediaPlayer = null;
      }
      default -> {
        handled = false;
      }
    }
    return handled;
  }

  private static Point getPoint(MotionEvent event) {
    Point point = new Point();
    point.setX(Math.round(event.getX()));
    point.setY(Math.round(event.getY()));
    return point;
  }
}
