// In Robot.java (Java) or Robot.h (C++)
package frc.robot.commands;

import frc.robot.subsystems.DeviceSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.ctre.phoenix6.configs.MountPoseConfigs;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Autos {
  LimelightSubsystem mLimelightSubsystem;
  ShooterAnglePIDSubsystem m_AnglePIDSubsystem;
  DeviceSubsystem m_DeviceSubsystem;
  SwerveSubsystem m_swerveDrive;
  LedSubsystem m_kLedSubsystem;

  public void robotInit() {

  }

  public static Command defaultAuto(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    // return new InstantCommand(()->{
    // BE SURE TO SCHEDULE A COMMAND WITH .schedule()
    // m_swerveDrive.resetDistanceMotors();

    // Change LEDs to rainbow
    // m_ledSubsystem.update_led_color(Constants.RAINBOW_GLITTER);

    // Target Distance IN INCHES
    double targetDistance = 67;
    SmartDashboard.putNumber("Before Command Sequence", 0);
    // Factor of distance
    final double distanceConversionFactor = 1.5;
    SmartDashboard.putNumber("Before Command Sequence", 1);

    // Default AUTO
    return new SequentialCommandGroup(
        //new WaitCommand(0.25),
        
        //new InstantCommand(() -> SmartDashboard.putNumber("Before Command Sequence", 5)),
       // new PIDCommandTurnToAngle(gm_limelight, m_swerveDrive).withTimeout(0.5),
        // move shooter to max shooting angle (48 deg)
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 2, Constants.MAX_SHOOTER_ANGLE).withTimeout(0.5),
        // new InstantCommand(()->{m_AnglePIDSubsystem.shootAngle();}),
        // turn intake off
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        // quick outtake to unjam note
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE)
            .withTimeout(0.5),
        new ShootSequence(m_DeviceSubsystem),
        // new ResetAngleCommand(m_limelight, m_swerveDrive),
        // turn intake on
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        new AutoMovePIDCommand(0, targetDistance / distanceConversionFactor, m_swerveDrive.returnAverageDistance(),
            m_swerveDrive).withTimeout(2),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(0.5),
        // new AutoMovePIDCommand(0, 9 / distanceConversionFactor,
        // m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(0.5),
        // turn intake off
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        new WaitCommand(2),

        // new AutoMovePIDCommand(0, 10 / distanceConversionFactor,
        // m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(2),
        // quick outtake to unlodge stuck note
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(3),
        // m_swerveDrive.returnAverageDistance(), m_swerveDrive),
        new ShootSequence(m_DeviceSubsystem).withTimeout(5),
        // new AutoMovePIDCommand(180, targetDistance - 10 / distanceConversionFactor,
        // m_swerveDrive.returnAverageDistance(), m_swerveDrive)
        new WaitCommand(5));

  };

  public static Command autoPositionOne(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(-.25);
    // SmartDashboard.putNumber("Before Command Sequence", 0);
        //return new ShootSequence(m_DeviceSubsystem);  
    return new SequentialCommandGroup(
        //AUTO LEFT
        new WaitCommand(.5),
        //ONE NOTE

        //new AutoMovePIDCommand(0,20, m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(1),
        new PIDGyroCommand(60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        //new PIDGyroCommand(45, m_swerveDrive), keep this commented out
        new ShootSequence(m_DeviceSubsystem),

    //TWO NOTE
    
    new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    Constants.MAX_SHOOTER_ANGLE),
new WaitCommand(1),
    new AutoMovePIDCommand(0, 50, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.
    MAX_SHOOTER_ANGLE).withTimeout(1),

   // new PIDGyroCommand(30, m_swerveDrive).withTimeout(.8),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
    new WaitCommand(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE),
    new ShootSequence(m_DeviceSubsystem),

    // //3 NOTE

    new PIDGyroCommand(0, m_swerveDrive),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    Constants.MAX_SHOOTER_ANGLE),
    new AutoMovePIDCommand(190, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    MAX_SHOOTER_ANGLE),
    new AutoMovePIDCommand(10, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    //new PIDGyroCommand(22, m_swerveDrive),
    new ShootSequence(m_DeviceSubsystem),
    new PIDGyroCommand(0, m_swerveDrive),
        new WaitCommand(100)
    );

  }

  public static Command autoPositionTwo(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(Constants.RAINBOW_GLITTER);

    return new SequentialCommandGroup(
        //AUTO LEFT
        new WaitCommand(.5),
        //ONE NOTE

        new ShootSequence(m_DeviceSubsystem),

    //TWO NOTE
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    Constants.MAX_SHOOTER_ANGLE),
    new WaitCommand(1),
    new AutoMovePIDCommand(0, 50, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.
    MAX_SHOOTER_ANGLE).withTimeout(1),

   // new PIDGyroCommand(30, m_swerveDrive).withTimeout(.8),
    new WaitCommand(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE),
    new ShootSequence(m_DeviceSubsystem),

    //3 NOTE

    new PIDGyroCommand(0, m_swerveDrive),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    Constants.MAX_SHOOTER_ANGLE),
    new AutoMovePIDCommand(5, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    MAX_SHOOTER_ANGLE),
    new AutoMovePIDCommand(185, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    //new PIDGyroCommand(22, m_swerveDrive),
    new ShootSequence(m_DeviceSubsystem),
    new PIDGyroCommand(0, m_swerveDrive),
        new WaitCommand(100)
    );
  }
  
  public static Command autoPositionThree(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
  return new SequentialCommandGroup(
        //AUTO LEFT
        new WaitCommand(.5),
        //ONE NOTE

        //new AutoMovePIDCommand(0,20, m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(1),
        new PIDGyroCommand(-60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        //new PIDGyroCommand(45, m_swerveDrive), keep this commented out
        new ShootSequence(m_DeviceSubsystem),

    //TWO NOTE
    
    new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    Constants.MAX_SHOOTER_ANGLE),
new WaitCommand(1),
    new AutoMovePIDCommand(0, 40, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.
    MAX_SHOOTER_ANGLE).withTimeout(1),

   // new PIDGyroCommand(30, m_swerveDrive).withTimeout(.8),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
    new WaitCommand(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE),
    new ShootSequence(m_DeviceSubsystem),

    // //3 NOTE

    // new PIDGyroCommand(0, m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    // Constants.MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(190, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    // MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(10, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    // //new PIDGyroCommand(22, m_swerveDrive),
    // new ShootSequence(m_DeviceSubsystem),
    // new PIDGyroCommand(0, m_swerveDrive)
        new WaitCommand(100)
    );
      }
}
