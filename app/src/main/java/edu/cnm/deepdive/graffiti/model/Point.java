package edu.cnm.deepdive.graffiti.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.time.Instant;
import java.util.UUID;

public class Point {

  @Expose
  private int x;

  @Expose
  private int y;

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



  @SuppressLint("DefaultLocale")
  @NonNull
  @Override
  public String toString() {
    return String.format("%1$s[x=%2$d, y=%3$d]", getClass().getSimpleName(), x, y);
  }
}
