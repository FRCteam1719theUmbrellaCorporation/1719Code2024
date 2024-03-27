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

 

    public static Command RedAmp2note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(-.25);
    // SmartDashboard.putNumber("Before Command Sequence", 0);
        //return new ShootSequence(m_DeviceSubsystem);  
    return new SequentialCommandGroup(
        new WaitCommand(.5),
        //ONE NOTE
        new PIDGyroCommand(60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

        //TWO NOTE
        new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.
        MAX_SHOOTER_ANGLE),
        new WaitCommand(1),
        new AutoMovePIDCommand(0, 50, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE).withTimeout(1),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new WaitCommand(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
  
    // //3 NOTE

    // new PIDGyroCommand(0, m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    // Constants.MAX_SHOOTER_ANGLE).withTimeout(1),
    // new AutoMovePIDCommand(190, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    // MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(10, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    // new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    // //new PIDGyroCommand(22, m_swerveDrive),
    // new ShootSequence(m_DeviceSubsystem),
    // new PIDGyroCommand(0, m_swerveDrive),
        new WaitCommand(100) //DO NOT COMMENT
    );
  }

  public static Command RedOrBlueCenter2note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(Constants.RAINBOW_GLITTER);

    return new SequentialCommandGroup(
        new WaitCommand(.5),
        //ONE NOTE
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

        //TWO NOTE
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.
        MAX_SHOOTER_ANGLE),
        new WaitCommand(1),
        new AutoMovePIDCommand(0, 40, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE).withTimeout(1),
        new WaitCommand(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
        

    //3 NOTE

    // new PIDGyroCommand(0, m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    // Constants.MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(5, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    // MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(185, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    // //new PIDGyroCommand(22, m_swerveDrive),
    // new ShootSequence(m_DeviceSubsystem),
    // new PIDGyroCommand(0, m_swerveDrive),
        new WaitCommand(100) //DO NOT COMMENT
    );
  }

  public static Command RedClimber2note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
  return new SequentialCommandGroup(
        //AUTO LEFT
        new WaitCommand(.5),
        //ONE NOTE

        //new AutoMovePIDCommand(0,20, m_swerveDrive.returnAverageDistance(), m_swerveDrive).withTimeout(1),
        new PIDGyroCommand(-60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        //new PIDGyroCommand(45, m_swerveDrive), keep this commented out
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

    //TWO NOTE
    
    new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn",
    Constants.MAX_SHOOTER_ANGLE),
new WaitCommand(1),
    new AutoMovePIDCommand(0, 40, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
    MAX_SHOOTER_ANGLE).withTimeout(1),

   // new PIDGyroCommand(30, m_swerveDrive).withTimeout(.8),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
    new WaitCommand(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE),
    new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

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
        new WaitCommand(100) //DO NOT COMMENT
    );
    }
    
    public static Command BlueClimber2note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(-.25);
    // SmartDashboard.putNumber("Before Command Sequence", 0);
        //return new ShootSequence(m_DeviceSubsystem);  
    return new SequentialCommandGroup(
        new WaitCommand(.5),
        //ONE NOTE
        new PIDGyroCommand(60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

        //TWO NOTE
        new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn",
        Constants.MAX_SHOOTER_ANGLE),
        new WaitCommand(1),
        new AutoMovePIDCommand(0, 40, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE).withTimeout(1),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new WaitCommand(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
    // //3 NOTE

    // new PIDGyroCommand(0, m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    // Constants.MAX_SHOOTER_ANGLE).withTimeout(1),
    // new AutoMovePIDCommand(190, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    // MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(10, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    // new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    // //new PIDGyroCommand(22, m_swerveDrive),
    // new ShootSequence(m_DeviceSubsystem),
    // new PIDGyroCommand(0, m_swerveDrive),
         new WaitCommand(100) //DO NOT COMMENT
    );
  }

  public static Command BlueAmp2note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    m_ledSubsystem.set_led_color(-.25);
    // SmartDashboard.putNumber("Before Command Sequence", 0);
        //return new ShootSequence(m_DeviceSubsystem);  
    return new SequentialCommandGroup(
        new WaitCommand(.5),
        //ONE NOTE
        new PIDGyroCommand(-60, m_swerveDrive).withTimeout(.5),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

        //TWO NOTE
        new PIDGyroCommand(0, m_swerveDrive).withTimeout(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.
        MAX_SHOOTER_ANGLE),
        new WaitCommand(1),
        new AutoMovePIDCommand(0, 50, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE).withTimeout(1),
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
        new WaitCommand(1),
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE),
        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
    // //3 NOTE

    // new PIDGyroCommand(0, m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0,
    // Constants.MAX_SHOOTER_ANGLE).withTimeout(1),
    // new AutoMovePIDCommand(190, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive),
    // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, 0, Constants.
    // MAX_SHOOTER_ANGLE),
    // new AutoMovePIDCommand(10, 100, m_swerveDrive.returnAverageDistance(), m_swerveDrive).until(m_DeviceSubsystem::checkRing),
    // new PIDCommandTurnToAngle(m_limelight, m_swerveDrive),
    // //new PIDGyroCommand(22, m_swerveDrive),
    // new ShootSequence(m_DeviceSubsystem),
    // new PIDGyroCommand(0, m_swerveDrive),
         new WaitCommand(100) //DO NOT COMMENT
    );
  }

 public static Command AutoRightofDriverGoodTeam(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
        return new SequentialCommandGroup(
            new WaitCommand(.5),
            //ONE NOTE
            new PIDGyroCommand(60, m_swerveDrive).withTimeout(.5),
            new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
            new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
            // Allow other robots to do their autonomous sequence
            new WaitCommand(10),
            new AutoMovePIDCommand(0, 40, m_swerveDrive).withTimeout(2),
            new WaitCommand(100) //DO NOT COMMENT
        );
      }

  public static Command AutoCenterGoodTeam(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
        return new SequentialCommandGroup(
            new WaitCommand(.5),
            // ONE NOTE
            new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
            // Allow other robots to do their autonomous sequence
            new WaitCommand(11),
            new AutoMovePIDCommand(0, 40, m_swerveDrive).withTimeout(2),
            new WaitCommand(100) //DO NOT COMMENT
        );
      }

  public static Command AutoLeftofDriverGoodTeam(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
        return new SequentialCommandGroup(
            new WaitCommand(.5),
            //ONE NOTE
            new PIDGyroCommand(-60, m_swerveDrive).withTimeout(.5),
            new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(1),
            new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),
            // Allow other robots to do their autonomous sequence
            new WaitCommand(10),
            new AutoMovePIDCommand(0, 40, m_swerveDrive).withTimeout(2),
            new WaitCommand(100) //DO NOT COMMENT
        );
      } //CODE FOR AUTOS WITH GOOD TEAMMATES DO NOT UNCODE UNTIL TESTED - neel

  public static Command Test3note(DeviceSubsystem m_DeviceSubsystem, ShooterAnglePIDSubsystem m_AnglePIDSubsystem,
      LimelightSubsystem m_limelight, SwerveSubsystem m_swerveDrive, LedSubsystem m_ledSubsystem) {
    return new SequentialCommandGroup(
        //AUTO LEFT
        new WaitCommand(.5),
        //ONE NOTE

        new ShootSequence(m_DeviceSubsystem).withTimeout(2.4),

    //TWO NOTE
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.
        MAX_SHOOTER_ANGLE),
    new WaitCommand(1.5),
    new AutoMovePIDCommand(0, 40, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(2),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE).withTimeout(1),

   // new PIDGyroCommand(30, m_swerveDrive).withTimeout(.8),
    new WaitCommand(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-1", Constants.DEFAULT_SHOOTER_ANGLE).withTimeout(.5),
    new ShootSequence(m_DeviceSubsystem).withTimeout(2.6),


    //Test place
    new PIDGyroCommand(90, m_swerveDrive).withTimeout(1),
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.
        MAX_SHOOTER_ANGLE),
    new WaitCommand(.4),
    new AutoMovePIDCommand(-87, 40, m_swerveDrive).until(m_DeviceSubsystem::checkRing).withTimeout(1), //Tune
    new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.
        MAX_SHOOTER_ANGLE),
    new PIDGyroCommand(23, m_swerveDrive).withTimeout(.8),
    new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(.5),
    new WaitCommand(1),
    new ShootSequence(m_DeviceSubsystem).withTimeout(2.6),
    new WaitCommand(100) //DO NOT COMMENT
    );
  }
}
