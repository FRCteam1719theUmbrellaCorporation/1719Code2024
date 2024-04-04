/*
 * Limelight Subsytem: this subsystem makes it far easier to read data from the limelight
 * 
 * Allows you to find things like an april tags, their distances (x and y) aswell as the angles to each game peice
 */

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// WPILIB
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// ROBOT
import frc.robot.Constants;

public class LimelightSubsystem extends SubsystemBase {  

  public static double tagID = 0;
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Corrent the shooting angle by a small factor
  public static double ShootingAngleCorrection(double distance) {
    // Returns error correction that is good to 0.1 deg to a distance of 10 ft
    return 1.6*Math.tanh((distance - Constants.DISTANCE_FROM_SPEAKER_FOR_DEFAULT_SHOOTING)/47.);
  }
  
  // Get angle for shooter head (returns double angle from 0 to 360)
  public double getShootingAngle() {
    // Vertical height from the shooter to the target
    double Y = Constants.SPEAKER_SHOOTING_dY;
    // Horizontal distance from shooter to target
    double distance_to_target = getDistance();
    // Distance taking into consideration the equation of distance
    double X = distance_to_target + Constants.SPEAKER_SHOOTING_dX;
    // Angle for shooting
    double phi = Math.toDegrees(Math.atan2(Y,X));
    // Return angle with the shooting angle correction
    return phi + ShootingAngleCorrection(distance_to_target);    
  }

  // Get distance to april tag (returns double) - ONLY FOR SPEAKER
  public double getDistance() {
    // Network table to access limelight readings
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    Number aprilTagId = tid.getNumber(0);
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    // Speakers are ID 4 and 7
    if (aprilTagId.intValue() == 4 || aprilTagId.intValue() == 7) {
      // Calculate the angle
      double angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + targetOffsetAngle_Vertical;
      double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);
      // Calculate the distance
      double distanceFromLimelightToGoalInches = (Constants.SPEAKER_APRILTAG_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT_INCHES) / Math.tan(angleToGoalRadians);
      return distanceFromLimelightToGoalInches;
    }
    // If not seeing april tag, return 0.0    
    return 0.0;
  }
  
  // Returns angle to any april tag
  public double getAngleToTag() {
    // Network table to access limelight readings
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double targetOffsetAngle_Horizontal = tx.getDouble(0.0);
    // Return the horizontal angle to tag
    return targetOffsetAngle_Horizontal;
  }

  // Returns angle to trap
  public double getAngleToTrap() {
    // Network table to access limelight readings
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
    // IDs 11, 12, 13, 14, 15, and 16
    if (aprilTagId == 15 || aprilTagId == 14 || aprilTagId == 16 || 
      aprilTagId == 11 || aprilTagId == 12 || aprilTagId == 13) {
      return getAngleToTag();
    }
    // If not seeing april tag, return 0.0
    return 0.0;
  }

  // Returns angle to speakers (tags 4 and 7)
  public double getAngleToSpeaker() {
    double aprilTagId = getTag();
    if (aprilTagId == 7 || aprilTagId == 4) {
      return getAngleToTag();
    }
    // If not seeing april tag, return 0.0
    return 0.0;
  }
 
  // Returns the ID of a tag in focus
  public double getTag() {
    // Network table to access limelight readings
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
    tagID = aprilTagId;
    return aprilTagId;
  }

  // Returns angle to amps (tags 5 and 6)
  public double getAngleToAmp() {
    // Network table to access limelight readings
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
    if (aprilTagId == 6 || aprilTagId == 5) {
      return getAngleToTag();
    }
    // If not seeing april tag, return 0.0
    return 0.0;
  }
}
