package edu.cnm.deepdive.graffiti.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.graffiti.databinding.ActivityCanvasBinding;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;
import java.util.LinkedList;
import java.util.List;

@AndroidEntryPoint
public class CanvasActivity extends AppCompatActivity {


  private CanvasViewModel canvasViewModel;
  private ActivityCanvasBinding binding;
  private LoadCanvasFragment loadCanvasFragment;
  private boolean canvasCreated;
  private List<Point> points;

  private OnTouchListener listener = new OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      if (canvasCreated) {
        handleMotionEvent(event);
      }
      return true;
    }
  };

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loadCanvasFragment = new LoadCanvasFragment();
    FragmentManager manager = getSupportFragmentManager();
    canvasViewModel = new ViewModelProvider(this).get(CanvasViewModel.class);
    binding = ActivityCanvasBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.canvas.setOnTouchListener(listener);

    binding.createCanvas.setOnClickListener(
        (v) -> {
          Canvas canvas = new Canvas();
          canvas.setName(String.valueOf(binding.canvasName.getText()));
          canvasViewModel.add(canvas);
        }
    );

    binding.selectCanvas.setOnClickListener(
        (v) -> loadCanvasFragment.show(manager,"")
    );
    binding.refreshCanvas.setOnClickListener((v) -> canvasViewModel.refresh());
    canvasViewModel
        .getCanvas()
        .observe(this, (canvas) -> {
          canvasCreated = true;
          // TODO: 11/30/23 Draw points on canvas.
          binding.canvas.setCanvas(canvas);
          binding.canvas.invalidate();
        });
  }

  private void handleMotionEvent(MotionEvent event) {
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN -> {
        points = new LinkedList<>();
        points.add(getPoint(event));
      }
      case MotionEvent.ACTION_MOVE -> {
        points.add(getPoint(event));
      }
      case MotionEvent.ACTION_UP -> {
        points.add(getPoint(event));
        Tag tag = new Tag();
        tag.getPoints().addAll(points);
        canvasViewModel.add(tag);
      }
    }
  }

  @NonNull
  private static Point getPoint(MotionEvent event) {
    Point point = new Point();
    point.setX(Math.round(event.getX()));
    point.setY(Math.round(event.getY()));
    return point;
  }

}
