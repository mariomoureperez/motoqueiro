package io.github.sithengineer.motoqueiro.data;

import io.github.sithengineer.motoqueiro.app.Preferences;
import io.github.sithengineer.motoqueiro.hardware.Accelerometer;
import io.github.sithengineer.motoqueiro.hardware.Gravity;
import io.github.sithengineer.motoqueiro.hardware.Gyroscope;
import io.github.sithengineer.motoqueiro.hardware.MiBandService;
import io.github.sithengineer.motoqueiro.hardware.capture.LatLng;
import io.github.sithengineer.motoqueiro.hardware.capture.MiBandData;
import io.github.sithengineer.motoqueiro.hardware.capture.RelativeCoordinates;
import io.github.sithengineer.motoqueiro.hardware.gps.Gps;
import rx.Observable;
import timber.log.Timber;

public class DataManager {

  private final Accelerometer accelerometer;
  private final Gyroscope gyroscope;
  private final Gravity gravity;
  private final Gps gps;
  private final MiBandService miBand;
  private final RideRepository rideRepo;
  private final Preferences preferences;

  public DataManager(Accelerometer accelerometer, Gyroscope gyroscope, Gravity gravity, Gps gps,
      MiBandService miBand, RideRepository rideRepo, Preferences preferences) {
    this.accelerometer = accelerometer;
    this.gyroscope = gyroscope;
    this.gravity = gravity;
    this.gps = gps;
    this.miBand = miBand;
    this.rideRepo = rideRepo;
    this.preferences = preferences;
  }

  public Observable<Void> gatherData() {
    // gps data generator observable
    Observable<Void> generateGpsObservable =
        gps.listen().doOnNext(pos -> handleGpsPositionCapture(pos)).map(__ -> null);

    // accelerometer sensor data generator observable
    Observable<Void> generateAccelObservable = accelerometer.listen()
        .doOnNext(accelData -> handleAccelerometerCapture(accelData))
        .onErrorResumeNext(err -> {
          Timber.e(err);
          return Observable.empty();
        })
        .map(__ -> null);

    // gyroscope sensor data generator observable
    Observable<Void> generateGravityObservable = gravity.listen()
        .doOnNext(gravityData -> handleGravityCapture(gravityData))
        .onErrorResumeNext(err -> {
          Timber.e(err);
          return Observable.empty();
        })
        .map(__ -> null);

    // gyroscope sensor data generator observable
    Observable<Void> generateGyroObservable = gyroscope.listen()
        .doOnNext(gyroData -> handleGyroscopeCapture(gyroData))
        .onErrorResumeNext(err -> {
          Timber.e(err);
          return Observable.empty();
        })
        .map(__ -> null);

    // miband heart rate sensor data generator observable
    Observable<Void> generateHeartRateObservable = miBand.listen()
        .doOnNext(heartData -> handleMiBandCapture(heartData))
        .onErrorResumeNext(err -> {
          Timber.e(err);
          return Observable.empty();
        })
        .map(__ -> null);

    return Observable.merge(generateGpsObservable, generateAccelObservable, generateGyroObservable,
        generateGravityObservable, generateHeartRateObservable);
  }

  private String getRideId() {
    return preferences.getRideId();
  }

  private void handleMiBandCapture(MiBandData bandData) {
    rideRepo.saveHeartRate(getRideId(), bandData.getHeartRateBpm());
  }

  private void handleGpsPositionCapture(LatLng capture) {
    rideRepo.saveGpsCoordinate(getRideId(), capture.getLat(), capture.getLng());
  }

  private void handleAccelerometerCapture(RelativeCoordinates capture) {
    rideRepo.saveAccelerometerCapture(getRideId(), capture.getXx(), capture.getYy(),
        capture.getZz());
  }

  private void handleGravityCapture(RelativeCoordinates capture) {
    rideRepo.saveGravityCapture(getRideId(), capture.getXx(), capture.getYy(), capture.getZz());
  }

  private void handleGyroscopeCapture(RelativeCoordinates capture) {
    rideRepo.saveGyroscopeCapture(getRideId(), capture.getXx(), capture.getYy(), capture.getZz());
  }
}
