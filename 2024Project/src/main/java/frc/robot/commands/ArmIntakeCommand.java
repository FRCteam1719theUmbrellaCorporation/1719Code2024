// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DeviceSubsystem;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;

public class ArmIntakeCommand extends Command {
  private ShooterAnglePIDSubsystem m_angler;
  private DeviceSubsystem m_device;

  /** Creates a new ArmIntakeCommand. */
  public ArmIntakeCommand(ShooterAnglePIDSubsystem m_angler, DeviceSubsystem m_device) {
    addRequirements(m_angler);
    this.m_angler=m_angler;
    this.m_device=m_device;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_angler.setSetpoint(Constants.DEFAULT_SHOOTER_ANGLE);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_device.checkRing();
  }
}
