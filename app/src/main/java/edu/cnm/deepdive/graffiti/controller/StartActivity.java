package edu.cnm.deepdive.graffiti.controller;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.graffiti.R;
import edu.cnm.deepdive.graffiti.databinding.ActivityStartBinding;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;

@AndroidEntryPoint
public class StartActivity extends AppCompatActivity {

  public static final String CANVAS_ID_KEY = "canvas_id";
  private CanvasViewModel canvasViewModel;
  MediaPlayer mediaPlayer;
  private LoadCanvasFragment loadCanvasFragment;
  private EditCanvasFragment editCanvasFragment;
  private ActivityStartBinding binding;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mediaPlayer = MediaPlayer.create(this, R.raw.start);
    mediaPlayer.start();
    binding = ActivityStartBinding.inflate(getLayoutInflater());
    canvasViewModel = new ViewModelProvider(this).get(CanvasViewModel.class);
    setContentView(binding.getRoot());
    loadCanvasFragment = new LoadCanvasFragment();
    editCanvasFragment = new EditCanvasFragment();
    FragmentManager manager = getSupportFragmentManager();

    canvasViewModel.getCanvas().observe(this, (canvas) ->
        close(canvas)
    );

    binding.createCanvas.setOnClickListener(
        (v) -> {
          editCanvasFragment.show(manager, "");

        }
    );

    binding.selectCanvas.setOnClickListener(
        (v) -> {
          loadCanvasFragment.show(manager, "");
        });
  }

  public void close(Canvas canvas) {
    if (canvas != null) {
      Intent intent = new Intent(this, CanvasActivity.class);
      intent.putExtra(CANVAS_ID_KEY, canvas.getId());
      startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }
  }

  @Override
  protected void onDestroy() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
      mediaPlayer = null;
    }
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
}
