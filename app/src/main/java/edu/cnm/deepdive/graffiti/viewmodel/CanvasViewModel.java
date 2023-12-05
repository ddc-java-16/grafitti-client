package edu.cnm.deepdive.graffiti.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;
import edu.cnm.deepdive.graffiti.service.CanvasRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class CanvasViewModel extends ViewModel implements DefaultLifecycleObserver {

  private final CanvasRepository canvasRepository;
  private final CompositeDisposable pending;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Canvas> canvas;
  private final MutableLiveData<Point> point;
  private final MutableLiveData<Tag> tag;
  private final MutableLiveData<List<Canvas>> canvases;
  private final MutableLiveData<Integer> color;
  private final MutableLiveData<Integer> stroke;
  private final MutableLiveData<Integer> style;

  @Inject
  CanvasViewModel(@ApplicationContext Context context, CanvasRepository canvasRepository) {
    this.canvasRepository = canvasRepository;
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
    canvas = new MutableLiveData<>();
    point = new MutableLiveData<>();
    tag = new MutableLiveData<>();
    canvases = new MutableLiveData<>();
    color = new MutableLiveData<>(Color.BLACK);
    stroke = new MutableLiveData<>(1);
    style = new MutableLiveData<>(1);
  }


  public void add(Canvas canvas) {
    canvasRepository.add(canvas).subscribe(
        this.canvas::postValue,
        this::postThrowable, pending);
  }

  public void add(Tag tag) {
    tag.setColor(color.getValue());
    tag.setStroke(stroke.getValue());
    tag.setStyle(style.getValue());
    Canvas canvas = this.getCanvas().getValue();
    canvasRepository.add(tag, canvas).subscribe(
        (t) -> {
          canvas.getTags().add(t);
          this.canvas.postValue(canvas);
        },
        this::postThrowable, pending);
  }

  public void add(Point point) {
    canvasRepository.add(point, canvas.getValue())
        .subscribe(this.point::postValue, this::postThrowable, pending);
  }

  public void fetchAll() {
    canvasRepository.getAll().subscribe(canvases::postValue, this::postThrowable, pending);
  }

  public void fetch(Canvas canvas) {
    canvasRepository.get(canvas).subscribe(this.canvas::postValue, this::postThrowable, pending);
  }

  public void fetch(String canvasId) {
    canvasRepository.get(canvasId).subscribe(this.canvas::postValue, this::postThrowable, pending);
  }

  public void refresh() {
    canvasRepository.get(canvas.getValue())
        .subscribe(canvas::postValue, this::postThrowable, pending);
  }



  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Canvas>> getCanvases() {
    return canvases;
  }

  public LiveData<Canvas> getCanvas() {
    return canvas;
  }

  public LiveData<Tag> getTag() {
    return tag;
  }

  public void setStyle(int style){this.style.postValue(style);}

  public void setColor(int color) {
    this.color.postValue(color);
  }

  public void setStroke(int stroke){this.stroke.postValue(stroke);}

  public LiveData<Integer> getColor(){
    return color;
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }


}
