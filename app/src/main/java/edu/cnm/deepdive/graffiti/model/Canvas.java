package edu.cnm.deepdive.graffiti.model;

import com.google.gson.annotations.Expose;

public class Canvas {


  @Expose(deserialize = true, serialize = false)
//  @SerializedName(value = "id", alternate = {"key"})
  private String canvasName;

  public String getCanvasName() {
    return canvasName;
  }

  public void setCanvasName(String canvasName) {
    this.canvasName = canvasName;
  }
}
