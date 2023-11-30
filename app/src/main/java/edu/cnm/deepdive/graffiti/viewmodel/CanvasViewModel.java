package edu.cnm.deepdive.graffiti.viewmodel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.service.CanvasRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import javax.inject.Inject;

@HiltViewModel
public class CanvasViewModel extends ViewModel implements DefaultLifecycleObserver {

  private final CanvasRepository canvasRepository;
  private final CompositeDisposable pending;
  private final MutableLiveData<Throwable> throwable;

  @Inject
  CanvasViewModel(@ApplicationContext Context context, CanvasRepository canvasRepository) {
    this.canvasRepository = canvasRepository;
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
  }

//  @Inject
//  public CanvasViewModel(@ApplicationContext Context context, CanvasRepository canvasRepository) {
//    this.canvasRepository = canvasRepository;
//
//
//  }

  public void add(Canvas canvas) {
    canvasRepository.add(canvas).subscribe((canvas1) -> {}
    , this::postThrowable, pending);
  }


  private void postThrowable(Throwable throwable){
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}
