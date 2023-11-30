package edu.cnm.deepdive.graffiti.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.graffiti.databinding.ActivityCanvasBinding;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.service.CanvasRepository;
import edu.cnm.deepdive.graffiti.service.GoogleSignInService;
import edu.cnm.deepdive.graffiti.service.GrafittiProxy;
import edu.cnm.deepdive.graffiti.viewmodel.CanvasViewModel;
import edu.cnm.deepdive.graffiti.viewmodel.LoginViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.UUID;

@AndroidEntryPoint
public class CanvasActivity extends AppCompatActivity  {


  private CanvasViewModel canvasViewModel;
  private ActivityCanvasBinding binding;
  private OnTouchListener listener = new OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      Log.i("GameActivity", event.toString());
//      Log.i("ACTION_DOWN",String.valueOf(event.getActionIndex()));
      Log.i("ACTION_DOWN_X",String.valueOf(event.getX()));
      Log.i("ACTION_DOWN_Y",String.valueOf(event.getY()));
      //Log.i("ACTION_MOVE",String.valueOf(event.getActionMasked()));
      return true;
    }
  };


  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    canvasViewModel = new ViewModelProvider(this).get(CanvasViewModel.class);
    binding = ActivityCanvasBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.canvas.setOnTouchListener(listener);

   binding.createCanvas.setOnClickListener(

       (v) -> {
         Canvas canvas = new Canvas();
         canvas.setCanvasName(String.valueOf(binding.canvasName.getText()));
         canvasViewModel.add(canvas);
       }
       );
  }

}
