package io.github.sithengineer.motoqueiro.data.model;

import java.util.LinkedList;
import java.util.List;

public class RidePart {

  private final String id;
  private final String name;
  private final long initialTimestamp;
  private final long finalTimestamp;
  private final boolean completed;
  private List<TriDimenPoint> accelerometerCaptures;
  private List<TriDimenPoint> gravityCaptures;
  private List<GpsPoint> gpsCoordinates;
  private List<HeartRatePoint> heartRateCaptures;

  public RidePart(String id, String name, long initialTimestamp, long finalTimestamp, boolean completed) {
    this.id = id;
    this.name = name;
    this.initialTimestamp = initialTimestamp;
    this.finalTimestamp = finalTimestamp;
    this.completed = completed;
    this.accelerometerCaptures = new LinkedList<>();
    this.gravityCaptures = new LinkedList<>();
    this.gpsCoordinates = new LinkedList<>();
    this.heartRateCaptures = new LinkedList<>();
  }

  public String getId() {
    return id;
  }

  public long getInitialTimestamp() {
    return initialTimestamp;
  }

  public long getFinalTimestamp() {
    return finalTimestamp;
  }

  public boolean isCompleted() {
    return completed;
  }

  public String getName() {
    return name;
  }

  public List<TriDimenPoint> getAccelerometerCaptures() {
    return accelerometerCaptures;
  }

  public void setAccelerometerCaptures(List<TriDimenPoint> accelerometerCaptures) {
    this.accelerometerCaptures = accelerometerCaptures;
  }

  public List<TriDimenPoint> getGravityCaptures() {
    return gravityCaptures;
  }

  public void setGravityCaptures(List<TriDimenPoint> gravityCaptures) {
    this.gravityCaptures = gravityCaptures;
  }

  public List<GpsPoint> getGpsCoordinates() {
    return gpsCoordinates;
  }

  public void setGpsCoordinates(List<GpsPoint> gpsCoordinates) {
    this.gpsCoordinates = gpsCoordinates;
  }

  public List<HeartRatePoint> getHeartRateCaptures() {
    return heartRateCaptures;
  }

  public void setHeartRateCaptures(List<HeartRatePoint> heartRateCaptures) {
    this.heartRateCaptures = heartRateCaptures;
  }

  public void addGpsCoord(long timestamp, double lat, double lon) {
    gpsCoordinates.add(new GpsPoint(lat, lon, timestamp));
  }

  public void addAccelerometerPoint(long timestamp, float x, float y, float z) {
    accelerometerCaptures.add(new TriDimenPoint(x, y, z, timestamp));
  }

  public void addGravityPoint(long timestamp, float x, float y, float z) {
    gravityCaptures.add(new TriDimenPoint(x, y, z, timestamp));
  }

  public void addHeartRatePoint(long timestamp, int bpm) {
    heartRateCaptures.add(new HeartRatePoint(bpm, timestamp));
  }
}