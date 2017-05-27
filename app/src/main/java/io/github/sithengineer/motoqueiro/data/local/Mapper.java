package io.github.sithengineer.motoqueiro.data.local;

import android.database.Cursor;
import io.github.sithengineer.motoqueiro.data.model.GpsPoint;
import io.github.sithengineer.motoqueiro.data.model.HeartRatePoint;
import io.github.sithengineer.motoqueiro.data.model.RidePart;
import io.github.sithengineer.motoqueiro.data.model.TriDimenPoint;
import java.util.List;
import rx.functions.Func1;
import rx.functions.Func5;

final class Mapper {

  static Func5<Cursor, List<GpsPoint>, List<HeartRatePoint>, List<TriDimenPoint>, List<TriDimenPoint>, RidePart>
      CURSOR_TO_RIDE;
  static Func1<Cursor, GpsPoint> CURSOR_TO_GPS_POINT;
  static Func1<Cursor, HeartRatePoint> CURSOR_TO_HEART_RATE_POINT;
  static Func1<Cursor, TriDimenPoint> CURSOR_TO_ACCEL_POINT;
  static Func1<Cursor, TriDimenPoint> CURSOR_TO_GRAVITY_POINT;

  static {
    CURSOR_TO_GPS_POINT = (cursor) -> {
      long timestamp =
          cursor.getLong(cursor.getColumnIndex(RidePersistenceContract.GpsEntry.COLUMN_TIMESTAMP));
      double lat =
          cursor.getDouble(cursor.getColumnIndex(RidePersistenceContract.GpsEntry.COLUMN_LAT));
      double lon =
          cursor.getDouble(cursor.getColumnIndex(RidePersistenceContract.GpsEntry.COLUMN_LON));
      return new GpsPoint(lat, lon, timestamp);
    };

    CURSOR_TO_HEART_RATE_POINT = (cursor) -> {
      long timestamp = cursor.getLong(
          cursor.getColumnIndex(RidePersistenceContract.HeartRateEntry.COLUMN_TIMESTAMP));
      int bpm =
          cursor.getInt(cursor.getColumnIndex(RidePersistenceContract.HeartRateEntry.COLUMN_BPM));
      return new HeartRatePoint(bpm, timestamp);
    };

    CURSOR_TO_ACCEL_POINT = (cursor) -> {
      long timestamp = cursor.getLong(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_TIMESTAMP));
      float xx = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_XX));
      float yy = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_YY));
      float zz = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_ZZ));
      return new TriDimenPoint(xx, yy, zz, timestamp);
    };

    CURSOR_TO_GRAVITY_POINT = (cursor) -> {
      long timestamp = cursor.getLong(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_TIMESTAMP));
      float xx = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_XX));
      float yy = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_YY));
      float zz = cursor.getFloat(
          cursor.getColumnIndex(RidePersistenceContract.AccelerometerEntry.COLUMN_ZZ));
      return new TriDimenPoint(xx, yy, zz, timestamp);
    };

    CURSOR_TO_RIDE = (cursor, gpsPoints, heartRatePoints, accelPoints, gravityPoints) -> {
      long initialTimestamp = cursor.getLong(
          cursor.getColumnIndex(RidePersistenceContract.RideEntry.COLUMN_START_TIMESTAMP));
      long finalTimestamp = cursor.getLong(
          cursor.getColumnIndex(RidePersistenceContract.RideEntry.COLUMN_END_TIMESTAMP));
      String id =
          cursor.getString(cursor.getColumnIndex(RidePersistenceContract.RideEntry._ID));

      String name = cursor.getString(cursor.getColumnIndex(RidePersistenceContract.RideEntry._ID));

      boolean completed = cursor.getInt(cursor.getColumnIndex(RidePersistenceContract.RideEntry.COLUMN_COMPLETED)) == 1;

      RidePart part = new RidePart(id, name, initialTimestamp, finalTimestamp, completed);
      part.setGpsCoordinates(gpsPoints);
      part.setHeartRateCaptures(heartRatePoints);
      part.setAccelerometerCaptures(accelPoints);
      part.setGravityCaptures(gravityPoints);
      return part;
    };
  }
}