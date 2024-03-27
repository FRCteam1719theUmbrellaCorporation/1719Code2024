// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.DeviceSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;

public class ShootAngleControlCommand extends Command {

  public boolean manualControl = false;
  private ShooterAnglePIDSubsystem mAnglePIDSubsystem;
  private LimelightSubsystem limeLight;

  private DoubleSupplier Yposition;

  private DeviceSubsystem m_DeviceSubsystem;

  /** Creates a new ShootAngleControlCommand. */
  public ShootAngleControlCommand(ShooterAnglePIDSubsystem mAnglePIDSubsystem, LimelightSubsystem limeLight, DoubleSupplier Yposition, DeviceSubsystem m_DeviceSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mAnglePIDSubsystem);

  this.mAnglePIDSubsystem = mAnglePIDSubsystem;
    this.limeLight = limeLight;

    this.Yposition = Yposition;
    this.m_DeviceSubsystem = m_DeviceSubsystem;

    // ignore this error :) i have no clue what it wants but it work
    //this.buttonVal = buttonVal;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   mAnglePIDSubsystem.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println(mAnglePIDSubsystem.getMeasurement());

    // this should allow manual control
    if (mAnglePIDSubsystem.manualControl) {
      double y = Yposition.getAsDouble() * 25 + 25;
      // System.out.println("going");
      mAnglePIDSubsystem.setSetpoint((y > Constants.MAX_SHOOTER_ANGLE) ? Constants.MAX_SHOOTER_ANGLE : y);
    }

    // returns angle as double
 else if (this.limeLight.getDistance() == 0.0 ) {
      mAnglePIDSubsystem.setSetpoint(Constants.DEFAULT_SHOOTER_ANGLE);

        } else if (!mAnglePIDSubsystem.getIntakeState()) {

      mAnglePIDSubsystem.setSetpoint(this.limeLight.getShootingAngle());
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override 
  public void end(boolean interrupted ) {

   }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
