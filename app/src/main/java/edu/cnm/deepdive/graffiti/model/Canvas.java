package edu.cnm.deepdive.graffiti.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Canvas {

  @Expose
  private String name;

  @Expose(deserialize = true, serialize = false)
  private final String id = null;

  @Expose
  private final Date created = new Date();

  @Expose(deserialize = true, serialize = false)
  private final List<Tag> tags = new LinkedList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public List<Tag> getTags() {
    return tags;
  }

  @NonNull
  @Override
  public String toString() {
    return String.format("%1$s[id=%2$s, name=%3$s, created=%4$s, tags=%5$s]",
        getClass().getSimpleName(), id, name, created, tags);
  }
}
