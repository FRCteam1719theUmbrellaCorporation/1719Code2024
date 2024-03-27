
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;


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
  // all 4 wheels are rotated 180
  public static final int LEFT_FRONT_DRIVE_SPEED_MOTOR_PIN = 1;
  public static final int LEFT_BACK_DRIVE_SPEED_MOTOR_PIN = 7;
  public static final int RIGHT_FRONT_DRIVE_SPEED_MOTOR_PIN = 4;
  public static final int RIGHT_BACK_DRIVE_SPEED_MOTOR_PIN = 10;

  // These are the pins for controlling the wheels direction
  // all 4 wheels are rotated 180
  public static final int LEFT_FRONT_DRIVE_DIRECTION_MOTOR_PIN =2;
  public static final int LEFT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 8;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_MOTOR_PIN = 5;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_MOTOR_PIN = 11;

  // these pins hold the ids for the wheel's direction encoders
  public static final int LEFT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 3;
  public static final int LEFT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 9;
  public static final int RIGHT_FRONT_DRIVE_DIRECTION_ENCODER_PIN = 6;
  public static final int RIGHT_BACK_DRIVE_DIRECTION_ENCODER_PIN = 12;
  
  // intake / outtake motors, allowing robot to shoot ids.
  public static final int SHOOTER = 15;
  public static final int INTAKE= 14;

  // changes the speed of the intake / shoot motors.
  // NEEDS TO BE CHANGED!!!
  public static final double INTAKESPEED = -1;
  public static final double OUTAKESPEED = 1;
  public static final double SHOOTSPEED = 1;

  //I2C port for color sensor - we may need to "Change the I2C port below to match the connection of your color sensor"
  public static final I2C.Port i2cPort = I2C.Port.kOnboard;
  //colorsensor, i2c port is parameter
  public static final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort); 

  // the value of this is probably wrong
  public static final int DISTANCE_NOTE_IN = 1500; 

  // angles of shooter
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

  /***************************************
   * Physical measurements of the system 
  ****************************************/

  // the vertical height of top of speaker's apriltag above ground
  public static final double SPEAKER_APRILTAG_HEIGHT = 60; // inches
  // vertical distance from top of apriltag to center of speaker
  public static final double HOLE_TO_APRILTAG_HEIGHT = 24; // inches //Jai just changed this from 24 to 20

  // Angle of limelight from the horizontal
  public static final double LIMELIGHT_MOUNT_ANGLE_DEGREES = 20.0; 
  // Offset of limelight center in inches from floor
  public static final  double LIMELIGHT_LENS_HEIGHT_INCHES = 7.5; 
  
  // maximum arm angle allowed (deg)
  public static final double MAX_SHOOTER_ANGLE = 51.5; // might set 1-2 lower
  // minimum arm angle allowed (deg)
  public static final double MIN_SHOOTER_ANGLE = 0; // horizontal shooter
  // arm angle when shooting platform (lexan) is horizontal
  public static final double SHOOTER_ARM_ANGLE = 27.5; // degreees
  
  // distance from the shooter arm's pivot to the shooting mechanism plane
  public static final double SHOOTER_ARM_LENGTH = 16.; // inches 
  // horizontal distance from limelight lens to shooter arm pivot point
  public static final double LIMELIGHT_TO_SHOOTER_PIVOT = 6.25; // inches 
  // vertical height of shooter arm pivot above floor
  public static final double SHOOTER_PIVOT_TO_FLOOR = 7.5;
  // default shooter angle if it can't see limelight
  public static final double DEFAULT_SHOOTER_ANGLE = Constants.MAX_SHOOTER_ANGLE;
  // default distance of robot to speaker at which DEFAULT_SHOOTER_ANGLE will work
  public static final double DISTANCE_FROM_SPEAKER_FOR_DEFAULT_SHOOTING = 42.0;
  // Empirical gyro correction factor to align with field orientation
  // needed in part since the gryo has been turned on its side
  public static final double GYRO_CORRECTION_FACTOR = 120; 
  // Empirical correction factor to Limelight distance based on azimuth offset
  public static final double AZIMUTH_CORRECTION_FACTOR = 20.0;

  // Now do math to combine constants for the shooting equations
  public static final double SPEAKER_SHOOTING_dY = SPEAKER_APRILTAG_HEIGHT + HOLE_TO_APRILTAG_HEIGHT
             - SHOOTER_PIVOT_TO_FLOOR - SHOOTER_ARM_LENGTH*Math.cos(Math.toRadians(DEFAULT_SHOOTER_ANGLE + SHOOTER_ARM_ANGLE));
  public static final double SPEAKER_SHOOTING_dX = LIMELIGHT_TO_SHOOTER_PIVOT
             + SHOOTER_ARM_LENGTH*Math.sin(Math.toRadians(DEFAULT_SHOOTER_ANGLE+SHOOTER_ARM_ANGLE));



  // controlls the motor led pin. and yes it is a motor 
  public static final int LED_PWM_PIN = 9;


  // testing required to get these ratios correct. use system.out.prinln or
  // something

  // public static final double ShooterDegreesPerEncoderRotation = 0;

  // stablizes the angle of the shooter
  public static final double ShootAngleP = 0.02;
  public static final double ShootAngleI = 0.0;
  public static final double ShootAngleD = 0;

  // these pins controls the angle of the shooter
  public static final int ShootAngleEncoder_PIN = 0; // encoder
  public static final int ShootAngleMotorPin = 16; // motor

  // these are used in lime light robot driving pid.
  public static final double LimeLightPositionTolerance = 2;
  public static final double LimeLightDegreesTolerance = 2;
  public static final double LimeLightVelocityTolerance = 0.02;

  // distance from the place you will score in from the april tag in inches
  public static final double DistFromAprilTag = 20; //24 was too high

  // Pid for PIDCommandTurnToAngle (positioning robot direction)
  public static final double PTurnToAngle = 0.005;
  public static final double ITurnToAngle = 0.000;
  public static final double DTurnToAngle = 0;

  // controls tolerence of limelight shooter angle i think
//see LimelightDegreesTolerance

  /*
   * LED CONSTANTS FOR COLOR
   * DO NOT EDIT
   */

  // Static colors during match
  public static final double NO_NOTE_BASELINE_GOLD = -0.85;
  public static final double NO_TAG_PURPLE = 0.91;
  public static final double SPEAKER_GREEN = 0.77;
  public static final double AMP_BLUE = 0.83;
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

  // Zero-point offset for shooter-arm (throughbore) encoder. 
  // This makes the returned angle be 0.0 when the shooting plane is horizontal
  public static final double SHOOTER_ANGLE_ZEROPOINT_OFFSET = 95.0; // degrees
  // Aiming offset in degrees to correct shooting. Currently 10 deg. 
  public static final double SHOOTER_ANGLE_CORRECTION = 0.0;

  //degree angle to intake

  //TODO: ADD ANALOG PIN HERE 
public static final AnalogInput ULTRASONIC_SENSOR_PIN = new AnalogInput(0);

// TODO: set these PLEASE
public static final double AUTO_MOVE_P = 0.02;
public static final double AUTO_MOVE_I = 0.00;
public static final double AUTO_MOVE_D = 0;
public static final double DISTANCEPOSITIONTOLERENCE = 2;


  //TODO: FILL IN IDS AND THEN MAKE BOTH FINAL
public static int BACKWARDS_SOLENOID_PIN = 0; 
public static int FORWARDS_SOLENOID_PIN = 1;
public static int COMPRESSOR_MODULE_NUMBER = 25;


//for learing how to drive 0 to 1
//Sugerman: keep the SPEED value at 1.5 to get max speed
public static double TELEOPSPEEDMODIFIER = 1.5;
public static final double TELEOPTWISTMODIFIER = .5;


public static final double JOYSTICK_SCALE_FACTOR = 2.0;
public static final double TWIST_SCALE_FACTOR = 3.0;

public static final double SHOOTERINTAKESUPPORT = -0.01;

// public static final int BOTTOM_LIMIT_SWITCH_PIN = 100;

} 
