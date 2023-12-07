package edu.cnm.deepdive.graffiti.controller;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.graffiti.R;
import edu.cnm.deepdive.graffiti.databinding.ActivityCanvasBinding;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@AndroidEntryPoint
public class CanvasActivity extends AppCompatActivity {


  private CanvasViewModel canvasViewModel;
  private ActivityCanvasBinding binding;
  MediaPlayer mediaPlayer;
  private ColorPickerFragment colorPickerFragment;
  private boolean canvasCreated;
  private List<Point> points;
  private Timer timer;
  private FragmentTransaction transaction;

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    timer = new Timer();
    FragmentManager manager = getSupportFragmentManager();
    canvasViewModel = new ViewModelProvider(this).get(CanvasViewModel.class);
    binding = ActivityCanvasBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        canvasViewModel.refresh();
//        binding.canvas.invalidate();
      }
    }, 2000, 2000);
    binding.tagView.setTagConsumer((tag) -> canvasViewModel.add(tag));
    canvasViewModel
        .getCanvas()
        .observe(this, (canvas) -> {
          canvasCreated = true;
          // TODO: 11/30/23 Draw points on canvas.
          binding.canvasView.setCanvas(canvas);
        });
    canvasViewModel.fetch(getIntent().getStringExtra(StartActivity.CANVAS_ID_KEY));
    binding.selectBrush.setOnClickListener((v) -> {
      canvasViewModel.getColor().observe(this, (color) -> {
        binding.canvas.setColor(color);
        binding.canvas.invalidate();
      });
      if (savedInstanceState == null) {
        colorPickerFragment = new ColorPickerFragment();
      }
      transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.fragmentcontainerview, ColorPickerFragment.class, null).commit();

    });
  }

  @Override
  protected void onDestroy() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
      mediaPlayer = null;
    }
    timer.cancel(); // FIXME: 12/6/23
    super.onDestroy();
  }

  @Override
  protected void onPause() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
      mediaPlayer = null;
    }
    super.onPause();
  }

  @NonNull
  private static Point getPoint(MotionEvent event) {
    Point point = new Point();
    point.setX(Math.round(event.getX()));
    point.setY(Math.round(event.getY()));
    return point;
  }

}
