package edu.cnm.deepdive.graffiti.service;

import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.UUID;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GrafittiProxy  {

//  @GET("/canvases/{canvasKey}/points")
//  Single<List<Point>> getPoint(@Header("Authorization") String bearerToken);
//
//  @POST("/canvases/{canvasKey}/points")
//  Completable postPoint(@Body Point point, @Header("Authorization") String bearerToken);

  @POST("/canvases")
  Single<Canvas> postCanvas(@Body Canvas canvas, @Header("Authorization") String bearerToken);

//  @GET("/canvases/{canvasKey}")
//  Single<Canvas> getCanvas(@Header("Authorization") String bearerToken, @Path("canvasKey") String canvasKey);
}
