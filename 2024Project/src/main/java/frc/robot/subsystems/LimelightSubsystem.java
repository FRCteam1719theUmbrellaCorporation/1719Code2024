// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  // !! is this the correct way to put in the angle subsystem??
  private ShooterAnglePIDSubsystem m_angler;
  
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Get angle for shooter head (returns double angle from 0 to 360)
  public double getShootingAngle() {
    // First get the current angle of the shooter
    // !! Someone will have to edit this code so we can use the AnglePIDSubsystem to getMeasurement()
    double phi = Math.toRadians(m_angler.getMeasurement()); 
    // vertical height from the shooter to the target
    double Y = Constants.SPEAKER_APRILTAG_HEIGHT+Constants.HOLE_TO_APRILTAG_HEIGHT
             - Constants.SHOOTER_PIVOT_TO_FLOOR - Constants.SHOOTER_ARM_LENGTH*Math.cos(phi);
    // horizontal distance from shooter to target
    double X = getDistance() + Constants.LIMELIGHT_TO_SHOOTER_PIVOT 
             + Constants.SHOOTER_ARM_LENGTH*Math.sin(phi) ;
    // System.out.println(Math.toDegrees(Math.atan2(Y,X)));
    return Math.toDegrees(Math.atan2(Y,X));
  }

  // Get distance to april tag (returns double)
  // This version is hard-coded for the speakers. Pass in a variable that the height   
  // of the apriltag in question to make this more general
  public double getDistance() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    Number aprilTagId = tid.getNumber(0);
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    if (aprilTagId.intValue() == 4 || aprilTagId.intValue() == 7) {
      double angleToGoalDegrees = Constants.LIMELIGHT_MOUNT_ANGLE_DEGREES + targetOffsetAngle_Vertical;
      double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

      //calculate distance
      double distanceFromLimelightToGoalInches = (Constants.SPEAKER_APRILTAG_HEIGHT - Constants.LIMELIGHT_LENS_HEIGHT_INCHES) / Math.tan(angleToGoalRadians);
      return distanceFromLimelightToGoalInches;
    }
    return 0.0;
  }
  
  // Returns angle to any april tag
  public double getAngleToTag() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double targetOffsetAngle_Horizontal = tx.getDouble(0.0);
    return targetOffsetAngle_Horizontal;
  }
  // Returns angle to traps
  public double getAngleToTrap() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
     if (aprilTagId == 15 || aprilTagId == 14 || aprilTagId == 16 || 
      aprilTagId == 11 || aprilTagId == 12 || aprilTagId == 13) {
      return getAngleToTag();
    }
    return 0.0;
  }
  // Returns angle to speakers
  public double getAngleToSpeaker() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
     if (aprilTagId == 7 || aprilTagId == 4) {
      return getAngleToTag();
    }
    return 0.0;
  }
  // Returns angle to amps
  public double getAngleToAmp() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid = table.getEntry("tid");
    double aprilTagId = tid.getInteger(0);
     if (aprilTagId == 6 || aprilTagId == 5) {
      return getAngleToTag();
    }
    return 0.0;
  }
}
