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
  LedSubsystem m_LedSubsystem;
  public void robotInit() {
    
  }

  public static Command defaultAuto(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    // return new InstantCommand(()->{
    // BE SURE TO SCHEDULE A COMMAND WITH .schedule()
    // m_swerveDrive.resetDistanceMotors();

    // Change LEDs to rainbow
    // System.out.println("auton");

    // updates LEDs
    m_ledSubsystem.set_led_color(Constants.RAINBOW_GLITTER);

    // Target Distance IN INCHES
    double targetDistance = 67;
    SmartDashboard.putNumber("Before Command Sequence", 0);
    // Factor of distance
    final double distanceConversionFactor = 1.5;
    SmartDashboard.putNumber("Before Command Sequence", 1);

    return new SequentialCommandGroup(
        new WaitCommand(0.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(0.5),
        // move shooter to max shooting angle (48 deg)
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 2, Constants.MAX_SHOOTER_ANGLE).withTimeout(0.5),
        // new InstantCommand(()->{m_AnglePIDSubsystem.shootAngle();}),
        // turn intake off
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        // quick outtake to unjam note
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        new ShootSequence(m_DeviceSubsystem),
        // new ResetAngleCommand(m_limelight, m_swerveDrive),
        // turn intake on
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        new AutoMovePIDCommand(0, targetDistance / distanceConversionFactor, m_swerveDrive.returnAverageDistance(),
            m_swerveDrive).withTimeout(2),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(0.5),
       // new AutoMovePIDCommand(0, 9 / distanceConversionFactor, m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(0.5),
       // turn intake off 
       new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(0.5),
        new WaitCommand(2),

       // new AutoMovePIDCommand(0, 10 / distanceConversionFactor, m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(2),
        // quick outtake to unlodge stuck note 
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, -1, Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(3),
        // m_swerveDrive.returnAverageDistance(), m_swerveDrive),
        new ShootSequence(m_DeviceSubsystem).withTimeout(5),
        // new AutoMovePIDCommand(180, targetDistance - 10 / distanceConversionFactor,
        //     m_swerveDrive.returnAverageDistance(), m_swerveDrive)
        new WaitCommand(5)
    );
  };
}
