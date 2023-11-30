package edu.cnm.deepdive.graffiti.service;

import edu.cnm.deepdive.graffiti.model.Canvas;
import edu.cnm.deepdive.graffiti.model.Point;
import edu.cnm.deepdive.graffiti.model.Tag;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface GraffitiProxy {

//  @GET("/canvases/{canvasKey}/points")
//  Single<List<Point>> getPoint(@Header("Authorization") String bearerToken);
//
//  @POST("/canvases/{canvasKey}/points")
//  Completable postPoint(@Body Point point, @Header("Authorization") String bearerToken);

  @POST("canvases")
  Single<Canvas> postCanvas(@Body Canvas canvas, @Header("Authorization") String bearerToken);

  @POST("canvases/{canvasId}/tags")
  Single<Tag> postTag(@Body Tag tag, @Path("canvasId") String canvasId, @Header("Authorization") String bearerToken);

  @POST("canvases/{canvasId}/points")
  Single<Point> postPoint(@Body Point point,@Path("canvasId") String canvasId, @Header("Authorization") String bearerToken);

  @GET("canvases/{canvasId}")
  Single<Canvas> getCanvas(@Path("canvasId") String canvasId, @Header("Authorization") String bearerToken);
}
