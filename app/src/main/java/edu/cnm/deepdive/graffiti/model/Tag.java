package edu.cnm.deepdive.graffiti.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Tag {

  @Expose(deserialize = true, serialize = false)
  private final String id = null;

  @Expose(deserialize = true, serialize = false)
  private final Date created = new Date();

  @Expose
  private final List<Point> points = new LinkedList<>();

  @Expose
  private int color;

  @Expose
  private int stroke;

  public String getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public List<Point> getPoints() {
    return points;
  }

  public int getStroke() {
    return stroke;
  }

  public void setStroke(int stroke) {
    this.stroke = stroke;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format("%1$s[id=%2$s, created=%3$s, points=%4$s]", getClass().getSimpleName(), id, created, points);
  }
}
