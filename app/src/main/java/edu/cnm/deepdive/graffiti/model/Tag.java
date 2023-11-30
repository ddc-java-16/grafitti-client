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

  public String getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public List<Point> getPoints() {
    return points;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format("%1$s[id=%2$s, created=%3$s, points=%4$s]", getClass().getSimpleName(), id, created, points);
  }
}
