package io.github.sithengineer.motoqueiro.util;

import java.util.UUID;

// based in constants from https://github.com/yonixw/mi-band-2
public final class Consts {
  //this is common for all BTLE devices. see http://stackoverflow.com/questions/18699251/finding-out-android-bluetooth-le-gatt-profiles
  public static final String BASE_UUID = "0000%s-0000-1000-8000-00805f9b34fb";

  public static final UUID UUID_SERVICE_GENERIC = UUID.fromString(String.format(BASE_UUID, "1800"));

  public static final UUID UUID_SERVICE_MIBAND_SERVICE =
      UUID.fromString(String.format(BASE_UUID, "FEE0"));

  public static final UUID UUID_SERVICE_MIBAND2_SERVICE =
      UUID.fromString(String.format(BASE_UUID, "FEE1"));

  public static final UUID UUID_SERVICE_HEARTBEAT =
      UUID.fromString(String.format(BASE_UUID, "180D"));

  // General service
  public static final UUID UUID_CHARACTERISTIC_DEVICE_NAME =
      UUID.fromString(String.format(BASE_UUID, "2A00"));

  // Miband service 1
  public static final UUID UUID_BUTTON_TOUCH =
      UUID.fromString("00000010-0000-3512-2118-0009af100700");

  //Heart beat service
  public static final UUID UUID_START_HEARTRATE_CONTROL_POINT =
      UUID.fromString("00002a39-0000-1000-8000-00805f9b34fb");

  public static final UUID UUID_NOTIFICATION_HEARTRATE =
      UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");

  public static final byte[] BYTE_LAST_HEART_RATE_SCAN = { 21, 1, 1 };
  public static final byte[] BYTE_NEW_HEART_RATE_SCAN = { 21, 2, 1 };

  public static final UUID UUID_DESCRIPTOR_UPDATE_NOTIFICATION =
      UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
}
