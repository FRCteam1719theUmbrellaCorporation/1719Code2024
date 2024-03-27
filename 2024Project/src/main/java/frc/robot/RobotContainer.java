// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ShootAngleControlCommand;
import frc.robot.commands.IntakeSequence;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoMovePIDCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.PIDCommandTurnToAngle;
import frc.robot.commands.SwerveTeleopCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DeviceSubsystem;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;
import frc.robot.subsystems.SwerveDirectionPIDSubsystem;
// import frc.robot.subsystems.SwerveDirectionPIDSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwerveDirectionPIDSubsystem m_leftFrontDirection = new
  SwerveDirectionPIDSubsystem(
  Constants.LEFT_FRONT_DRIVE_DIRECTION_ENCODER_PIN,
  Constants.LEFT_FRONT_DRIVE_DIRECTION_MOTOR_PIN);
  private final SwerveDirectionPIDSubsystem m_leftBackDirection = new
  SwerveDirectionPIDSubsystem(
  Constants.LEFT_BACK_DRIVE_DIRECTION_ENCODER_PIN,
  Constants.LEFT_BACK_DRIVE_DIRECTION_MOTOR_PIN);
  private final SwerveDirectionPIDSubsystem m_rightFrontDirection = new
  SwerveDirectionPIDSubsystem(
  Constants.RIGHT_FRONT_DRIVE_DIRECTION_ENCODER_PIN,
  Constants.RIGHT_FRONT_DRIVE_DIRECTION_MOTOR_PIN);
  private final SwerveDirectionPIDSubsystem m_rightBackDirection = new
  SwerveDirectionPIDSubsystem(
  Constants.RIGHT_BACK_DRIVE_DIRECTION_ENCODER_PIN,
  Constants.RIGHT_BACK_DRIVE_DIRECTION_MOTOR_PIN);
  private final SwerveSubsystem m_swerveDrive = new SwerveSubsystem(
    m_leftFrontDirection,m_leftBackDirection,
      m_rightFrontDirection, m_rightBackDirection);
  private final LimelightSubsystem m_limelight = new LimelightSubsystem();
  private final ShooterAnglePIDSubsystem m_AnglePIDSubsystem = new ShooterAnglePIDSubsystem();
  private final DeviceSubsystem m_DeviceSubsystem = new DeviceSubsystem();
  private final LedSubsystem m_LedSubsystem = new LedSubsystem(m_limelight);
  private final ClimbSubsystem m_ClimbSubsystem = new ClimbSubsystem();


  private final Joystick m_driverController = new Joystick(OperatorConstants.kDriverControllerPort);
  private final Joystick m_helperController = new Joystick(OperatorConstants.kHelperControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }


  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    

    SwerveTeleopCommand DriveMode = new SwerveTeleopCommand(
        this.m_swerveDrive, m_driverController::getY, m_driverController::getX,
        m_driverController::getTwist);
    this.m_swerveDrive.setDefaultCommand(DriveMode);
    // Manually Controlled Shoot Angle

    ShootAngleControlCommand AngleControl = new ShootAngleControlCommand(
        this.m_AnglePIDSubsystem, m_limelight, m_helperController::getY, m_DeviceSubsystem);

    this.m_AnglePIDSubsystem.setDefaultCommand(AngleControl);

    // LedCommand mLedCommand = new LedCommand(m_LedSubsystem, m_limelight, m_DeviceSubsystem, m_AnglePIDSubsystem);
    //this.m_LedSubsystem.setDefaultCommand(mLedCommand);

    // Run shoot sequence part 2 BUTTON 2 (HELPER)
    new JoystickButton(m_helperController, 2).onTrue(  
            new InstantCommand(() -> {
              m_DeviceSubsystem.turnIntakeMotors(-1); // -1 is the correct value for shooting
            })
        );
    ;
    new JoystickButton(m_helperController, 2).onFalse(
            new InstantCommand(() -> {
              m_DeviceSubsystem.turnIntakeMotors(0);
            }));

    // Turn on and off intake motors BUTTON 5 (HELPER)
    new JoystickButton(m_helperController, 5).onTrue(
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOn", Constants.DEFAULT_SHOOTER_ANGLE));
    new JoystickButton(m_helperController, 5).onFalse(
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.DEFAULT_SHOOTER_ANGLE)
    );


    // Turn on and off outtake motors BUTTON 3 (HELPER)
    new JoystickButton(m_helperController, 3).onTrue(
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-3", Constants.DEFAULT_SHOOTER_ANGLE));
    new JoystickButton(m_helperController, 3).onFalse(
        new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "intakeOff", Constants.DEFAULT_SHOOTER_ANGLE));



    //Shoot into Amp BUTTON 6 (HELPER)
    new JoystickButton(m_helperController, 6).onTrue(
        new SequentialCommandGroup(
            // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "2", Constants.MIN_SHOOTER_ANGLE),
            // new WaitCommand(1.5),
            // new IntakeSequence(m_DeviceSubsystem, m_AnglePIDSubsystem, "-2", Constants.MIN_SHOOTER_ANGLE)));

          new AutoMovePIDCommand(0, 10, m_swerveDrive),
          new WaitCommand(2),
          new InstantCommand(()-> {
            m_swerveDrive.resetDistanceMotors();
          }),
          new AutoMovePIDCommand(0, 10, m_swerveDrive)
        )

    );


    // Reset Gyro BUTTON 7 (HELPER)
    new JoystickButton(m_helperController, 7).onTrue(
        new InstantCommand(() -> {
          Robot.zeroGYRO();
        }));

    // Align with limelight BUTTON 5 (DRIVER)
    new JoystickButton(m_driverController, 5).onTrue(
        new PIDCommandTurnToAngle(m_limelight, m_swerveDrive).withTimeout(0.5)

    );

    new JoystickButton(m_helperController, 1).onTrue(
        new InstantCommand(() -> {
        m_DeviceSubsystem.turnShooterMotors(1);
      })
        );


         new JoystickButton(m_helperController, 1).onFalse(
      new InstantCommand(() -> {
        m_DeviceSubsystem.turnShooterMotors(0);
      })
        );

    // Manual Arm Aim BUTTON 10 (HELPER)
    new JoystickButton(m_helperController, 10).onTrue(
        new InstantCommand(() -> {
          m_AnglePIDSubsystem.setManualControl(true);
        }));

    new JoystickButton(m_helperController, 10).onFalse(
        new InstantCommand(() -> {
          m_AnglePIDSubsystem.setManualControl(false);
        }));


    // Raise pistons BUTTON 11 (HELPER)
    new JoystickButton(m_helperController, 11).onTrue(
        new InstantCommand(() -> {
          m_ClimbSubsystem.raise();
        }));
    // Lower pistons BUTTON 12 (HELPER)
    new JoystickButton(m_helperController, 12).onTrue(
        new InstantCommand(() -> {
          m_ClimbSubsystem.lower();
        }));
  }

  /*
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @ the command to run in autonomous
   */
  public Command getAutonomousCommand(String m_autoSelected) {
    // returns the correct auto called from the smart dashboard

    if (m_autoSelected.equals("RedAmp2note")) {
      return Autos.RedAmp2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("RedClimber2note")) {
      return Autos.RedClimber2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("BlueClimber2note")) {
      return Autos.BlueClimber2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("RedOrBlueCenter2note")) {
      return Autos.RedOrBlueCenter2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("BlueAmp2note")) {
      return Autos.BlueAmp2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("Center3note(test)")) {
      return Autos.Test3note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("AutoRightGoodTeams")) {
      return Autos.AutoRightofDriverGoodTeam(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("AutoCenterGoodTeams")) {
      return Autos.AutoCenterGoodTeam(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else if (m_autoSelected.equals("AutoLeftGoodTeams")) {
      return Autos.AutoLeftofDriverGoodTeam(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    } else {
      return Autos.RedOrBlueCenter2note(m_DeviceSubsystem, m_AnglePIDSubsystem, m_limelight, m_swerveDrive, m_LedSubsystem);
    }
  }
}
