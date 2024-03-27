// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PIDCommandTurnToAngle extends PIDCommand {
  public double ANGLEAIM;
  /** Creates a new PIDCommandTurnToAngle. */
  public PIDCommandTurnToAngle(LimelightSubsystem mLimelightSubsystem , SwerveSubsystem mSwerveSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PTurnToAngle, Constants.ITurnToAngle, Constants.DTurnToAngle),
        // This should return the measurement
        
         () -> mLimelightSubsystem.getAngleToSpeaker(),
        // This should return the setpoint (can also be a constant)
        ()-> 0,
        // This uses the output
        output -> {
          //System.out.println("Speaker angle horz: " + mLimelightSubsystem.getAngleToSpeaker());
         mSwerveSubsystem.SWERVE_DRIVE_COORDINATOR.drifTranslate(0, 0, -output);
         
   
        });
        
        getController().setTolerance(Constants.LimeLightDegreesTolerance, Constants.LimeLightVelocityTolerance);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mSwerveSubsystem, mLimelightSubsystem);

    // Configure additional PID options by calling `getController` here.    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

          SmartDashboard.putNumber("Before Command Sequence in pid aim y", 2);
    return this.m_controller.atSetpoint();
  }
}
