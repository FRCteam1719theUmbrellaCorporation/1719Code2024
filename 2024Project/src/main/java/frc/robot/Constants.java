// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

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

  // Human controller pin ids
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kHelperControllerPort = 1;
  }

  // These pins controll speed motors
  public static final int LEFT_FRONT_DRIVE_SPEED_MOTOR_PIN = 1;// all 4 wheels are rotated 180
  public static final int LEFT_BACK_DRIVE_SPEED_MOTOR_PIN = 7;
  public static final int RIGHT_FRONT_DRIVE_SPEED_MOTOR_PIN = 4;
  public static final int RIGHT_BACK_DRIVE_SPEED_MOTOR_PIN = 10;

  // These are the pins for controlling the wheels direction
  public static final int LEFT_FRONT_DRIVE_DIRECTION_MOTOR_PIN =2;// all 4 wheels are rotated 180
  public static final int LEFT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 8;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_MOTOR_PIN = 5;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 11;

  // these pins hold the ids for the wheel's direction encoders
  public static final int LEFT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 3;
  public static final int LEFT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 9;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 6;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 12;

 

  public static final int ARM_MOTOR = 21; // NO IDEA IF THIS IS CORRECT BEWARE
  
  // intake / outtake motors, allowing robot to shoot ids.
  public static final int SHOOTER = 15;
  public static final int INTAKE= 14;

  // changes the speed of the intake / shoot motors.
  // NEEDS TO BE CHANGED!!!
  public static final double INTAKESPEED = -1;
  public static final double OUTAKESPEED = 1;
  public static final double SHOOTSPEED = 1;

  // angles of shooter
  // PLACE HOLDERS!!!!!!!!!!!!
  //only 1 shoot angle needed
  public static final double shootAngleA = 110.0;
  public static final double shootAngleB = 110.0;
  public static final double shootAngleC = 30.0;

  // Gyro pin
  public static final int CAN_GYRO_PORT = 13;

  // these are the pid values for syncronizing direction motors 
  public static final double DirectionP = 0.005;
  public static final double DirectionI = 0.00;
  public static final double DirectionD = 0.0;
  // actually the vertical height of top of speaker's apriltag above ground
  public static final double SPEAKER_HEIGHT = 60; // NOT REAL VALUE - Must be inches

  // vertical distance from top of apriltag to center of speaker
  public static final double HOLE_TO_APRILTAG_HEIGHT = 24; // (2 feet) - Must be inches
  // vertical distance to (assumed) shooter height CENTER above ground
  // yes, we are ignoring the fact that it changes as we change
  // the shooter angle
  public static final double CENTER_HEIGHT_TO_GROUND = 25.5; //Must be inches
  // Horizontal distane from limelight to shooter CENTER
  public static final double CENTER_DISTANCE = 12; // (2 feet) - Must be inches



  // Angle of limelight from the horizontal
  public static final double LIMELIGHT_MOUNT_ANGLE_DEGREES = 20.0; //TODO check angle
  // Offset of limelight center in inches from floor
  public static final  double LIMELIGHT_LENS_HEIGHT_INCHES = 7.5; 
  // Vertical distance of speaker's apriltag above ground
  public static final double GOAL_HEIGHT_INCHES = 60; //60-offset height //TODO test

  // distance from the lime light stand to the shooter base
  public static final double DISTANCE_LIMELIGHT_TO_SHOOTER = 0; // NOT REAL VALUE - Must be meters

  // controlls the motor led pin. and yes it is a motor 
  public static final int LED_PWM_PIN = 9;


  // testing required to get these ratios correct. use system.out.prinln or
  // something

  // // 
  // public static final double ShooterDegreesPerEncoderRotation = 0;

  // stablizes the angle of the shooter
  public static final double ShootAngleP = 0.005;
  public static final double ShootAngleI = 0.000;
  public static final double ShootAngleD = 0;

  // these pins controls the angle of the shooter
  public static final int ShootAngleEncoder_PIN = 0; // encoder
  public static final int ShootAngleMotorPin = 16; // motor

  // these are used in lime light robot driving pid.
  public static final double LimeLightPositionTolerance = 2;
  public static final double LimeLightDegreesTolerance = 2;
  public static final double LimeLightVelocityTolerance = 0.02;

  // distance from the place you will score in from the april tag in inches
  public static final double DistFromAprilTag = 24;

  // Pid for PIDCommandTurnToAngle (positioning robot direction)
  public static final double PTurnToAngle = 0.005;
  public static final double ITurnToAngle = 0.001;
  public static final double DTurnToAngle = 0;

  // controls tolerence of limelight shooter angle i think
//see LimelightDegreesTolerance

  /*
   * LED CONSTANTS FOR COLOR
   * DO NOT EDIT
   */
  // Static colors during match
  public static final double NO_NOTE_BASELINE_GOLD = 0.67;
  public static final double HAS_NOTE_NO_TAG_PURPLE = 0.91;
  public static final double HAS_NOTE_SPEAKER_GREEN = 0.77;
  public static final double HAS_NOTE_AMP_BLUE = 0.83;
  public static final double AUTON_YELLOW = 0.25;
  // Extra colors
  public static final double WHITE = 0.93;
  public static final double RED = 0.61;
  // Patterns
  public static final double LARSON_SCANNER = 0.19;
  public static final double RAINBOW_GLITTER = -0.89;


  //Limelight Move Command PID's
  public static final double LIGHT_MOVE_P = 0.005;
  public static final double LIGHT_MOVE_I = 0;
  public static final double LIGHT_MOVE_D = 0;
 
  //Vertical Offset for Throughbore Encoder. Currently 95 deg in 0-1 units
  public static final double UPOFFSET = 0.2643;

  //degree angle to intake
  public static final double INTAKE_DEGREE_VALUE = 57;

  //TODO: ADD ANALOG PIN HERE PLEASE
public static final AnalogInput ULTRASONIC_SENSOR_PIN = new AnalogInput(0);

// TODO: set these PLEASE
public static final double AUTO_MOVE_P = 0.0005;
public static final double AUTO_MOVE_I = 0;
public static final double AUTO_MOVE_D = 0;
public static final double DISTANCEPOSITIONTOLERENCE = 2;


  //TODO: FILL IN IDS AND THEN MAKE BOTH FINAL
public static int BACKWARDS_SOLENOID_PIN = 9; 
public static int FORWARDS_SOLENOID_PIN = 8;

//for learing how to drive 0 to 1
public static final double TELEOPSPEEDMODIFIER = .2;
public static final double TELEOPTWISTMODIFIER = .2;

//boolean for aming
//public boolean SelfAim = true;
}
