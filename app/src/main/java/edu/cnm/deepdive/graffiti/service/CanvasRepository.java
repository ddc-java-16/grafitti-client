package edu.cnm.deepdive.graffiti.service;

import edu.cnm.deepdive.graffiti.hilt.ProxyModule;
import edu.cnm.deepdive.graffiti.model.Canvas;
import io.reactivex.rxjava3.core.Single;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class CanvasRepository {


  private GrafittiProxy grafittiProxy;
  private GoogleSignInService signInService;

  @Inject
  CanvasRepository(GrafittiProxy grafittiProxy, GoogleSignInService signInService) {
    this.grafittiProxy = grafittiProxy;
    this.signInService = signInService;
  }
//  void post(String name) {
//    grafittiProxy.postCanvas(signInService
//        .refreshBearerToken().toString(), name);
//  }

  public Single<Canvas> add(String name){
    return signInService
        .refreshBearerToken()
        .flatMap((token) -> grafittiProxy.postCanvas(name, token));
  }
}
