package io.github.sithengineer.motoqueiro.data.remote;

import io.github.sithengineer.motoqueiro.data.RideDataSource;
import io.github.sithengineer.motoqueiro.data.model.GpsPoint;
import io.github.sithengineer.motoqueiro.data.model.HeartRatePoint;
import io.github.sithengineer.motoqueiro.data.model.RidePart;
import io.github.sithengineer.motoqueiro.data.model.TriDimenPoint;
import io.github.sithengineer.motoqueiro.network.RideWebService;
import java.util.List;
import rx.Completable;
import rx.Single;

public class RideRemoteDataSource implements RideDataSource {

  private final RideWebService rideWebService;

  public RideRemoteDataSource(RideWebService rideWebService) {
    this.rideWebService = rideWebService;
  }

  @Override public Completable saveGpsData(String rideId, List<GpsPoint> points) {
    return Completable.error(new NoSuchMethodException());
  }

  @Override public Single<Long> saveGpsData(String rideId, GpsPoint coords) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<List<GpsPoint>> getGpsData(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override
  public Completable saveAccelerometerData(String rideId, List<TriDimenPoint> points) {
    return Completable.error(new NoSuchMethodException());
  }

  @Override
  public Single<Long> saveAccelerometerData(String rideId, TriDimenPoint point) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<List<TriDimenPoint>> getAccelerometerData(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override
  public Completable saveGravityData(String rideId, List<TriDimenPoint> points) {
    return Completable.error(new NoSuchMethodException());
  }

  @Override public Single<Long> saveGravityData(String rideId, TriDimenPoint point) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<List<TriDimenPoint>> getGravityData(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override
  public Completable saveHeartRateData(String rideId, List<HeartRatePoint> points) {
    return Completable.error(new NoSuchMethodException());
  }

  @Override public Single<Long> saveHeartRateData(String rideId, HeartRatePoint point) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<List<HeartRatePoint>> getHeartRateData(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<Long> saveRide(RidePart ride) {
    return rideWebService.upload(ride).toSingleDefault(0L);
  }

  @Override public Single<RidePart> getRide(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<Boolean> markCompleted(String rideId) {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<List<RidePart>> getCompletedRides() {
    return Single.error(new NoSuchMethodException());
  }

  @Override public Single<Boolean> markSynced(String rideId) {
    return Single.error(new NoSuchMethodException());
  }
}
