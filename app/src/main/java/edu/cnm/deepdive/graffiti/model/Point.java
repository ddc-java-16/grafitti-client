package edu.cnm.deepdive.graffiti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.time.Instant;
import java.util.UUID;

public class Point {


//  @Expose(deserialize = true, serialize = false)
//  @SerializedName(value = "id", alternate = {"key"})
//  private Long id;
//  @Expose
//  private Instant created;

  @Expose
  private int x;

  @Expose
  private int y;


  @Expose
  private UUID canvasKey;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public UUID getCanvasKey() {
    return canvasKey;
  }

  public void setCanvasKey(UUID canvasKey) {
    this.canvasKey = canvasKey;
  }
}
