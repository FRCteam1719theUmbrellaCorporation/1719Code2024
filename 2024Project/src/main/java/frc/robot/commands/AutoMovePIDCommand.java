// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoMovePIDCommand extends PIDCommand {
  /** Creates a new AutoMovePIDCommand. */

  public AutoMovePIDCommand(double direction, double distance, SwerveSubsystem m_swerve) {
    super(
        // The controller that the command will use
        new PIDController(Constants.AUTO_MOVE_P, Constants.AUTO_MOVE_I, Constants.AUTO_MOVE_D),
        // This should return the measurement
        () -> m_swerve.returnAverageDistance(),
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          // Use the output here
          //System.out.println("Difference in distance: " + Math.abs(initialDist - m_swerve.returnAverageDistance()));
          m_swerve.SWERVE_DRIVE_COORDINATOR.drifTranslate(direction,output,0.0);
          
        });
        m_swerve.resetDistanceMotors();
        addRequirements(m_swerve);
        getController().setTolerance(Constants.DISTANCEPOSITIONTOLERENCE, Constants.LimeLightVelocityTolerance);

    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
}}
