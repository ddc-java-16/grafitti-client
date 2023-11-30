package edu.cnm.deepdive.graffiti.service;

import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CanvasRepository {


  private GraffitiProxy graffitiProxy;
  private GoogleSignInService signInService;

  @Inject
  CanvasRepository(GraffitiProxy graffitiProxy, GoogleSignInService signInService) {
    this.graffitiProxy = graffitiProxy;
    this.signInService = signInService;
  }

  public Single<Canvas> add(Canvas canvas){
    return signInService
        .refreshBearerToken()
        .flatMap((token) -> graffitiProxy.postCanvas(canvas, token));
  }

  public Single<Point> add(Point point, Canvas canvas){
    return signInService
        .refreshBearerToken()
        .flatMap((token) -> graffitiProxy.postPoint(point, canvas.getId(), token));
  }

  public Single<Tag> add(Tag tag, Canvas canvas){
    return signInService
        .refreshBearerToken()
        .flatMap((token) -> graffitiProxy.postTag(tag, canvas.getId(), token));
  }

  public Single<Canvas> get(Canvas canvas){
    return signInService
        .refreshBearerToken()
        .flatMap((token) -> graffitiProxy.getCanvas(canvas.getId(), token));
  }
}
