// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final int LEFT_FRONT_DRIVE_SPEED_MOTOR_PIN = 1;
  public static final int LEFT_BACK_DRIVE_SPEED_MOTOR_PIN = 7;
  public static final int RIGHT_FRONT_DRIVE_SPEED_MOTOR_PIN = 4;
  public static final int RIGHT_BACK_DRIVE_SPEED_MOTOR_PIN = 10;

  public static final int LEFT_FRONT_DRIVE_DIRECTION_MOTOR_PIN = 2;
  public static final int LEFT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 8;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_MOTOR_PIN = 5;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 11;

  public static final int LEFT_FRONT_DRIVE_DISTANCE_ENCODER_PIN = 2;
  public static final int LEFT_BACK_DRIVE_DISTANCE_ENCODER_PIN = 8;
  public static final int RIGHT_FRONT_DRIVE_DISTANCE_ENCODER_PIN = 5;
  public static final int RIGHT_BACK_DRIVE_DISTANCE_ENCODER_PIN = 11;

  public static final int LEFT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 3;
  public static final int LEFT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 9;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 6;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 12;

  public static final int BOTTOM_DEVICE_MOTOR = 21;
public static final int TOP_DEVICE_MOTOR = 22;
public static final int BACK_DEVICE_MOTOR = 23;

  public static final int MXP_PORT = 0;

  public static final double DirectionP = 0.02;
  public static final double DirectionI = 0.001;
  public static final double DirectionD = 0.0;

}
