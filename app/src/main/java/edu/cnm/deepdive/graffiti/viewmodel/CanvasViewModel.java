package edu.cnm.deepdive.graffiti.viewmodel;

import android.content.Context;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.graffiti.service.CanvasRepository;
import javax.inject.Inject;

@HiltViewModel
public class CanvasViewModel extends ViewModel implements DefaultLifecycleObserver {

  private final CanvasRepository canvasRepository;

  @Inject
  public CanvasViewModel(@ApplicationContext Context context, CanvasRepository canvasRepository) {
    this.canvasRepository = canvasRepository;
  }

  public void add(String name) {
    canvasRepository.add(name);
  }


}
