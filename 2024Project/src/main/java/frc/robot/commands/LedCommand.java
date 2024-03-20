// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LedSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LedCommand extends Command {
  /** Creates a new LedCommand. */

  LimelightSubsystem mLimelightSubsystem;
  LedSubsystem mLedSubsystem;

  public LedCommand(LedSubsystem mLedSubsystem, LimelightSubsystem mLimelightSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.mLedSubsystem = mLedSubsystem;
    this.mLimelightSubsystem = mLimelightSubsystem;
    addRequirements(mLedSubsystem, mLimelightSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // sets this so that it doesn't try to set the leds all the time
    double aprilTagId = mLimelightSubsystem.getTag();

    System.out.println(aprilTagId); //TODO: COMMENT THIS OUT!!

    // speaker
    if (aprilTagId == 4 || aprilTagId == 7) {
      mLedSubsystem.set_led_color(Constants.SPEAKER_GREEN);

      // amp
    } else if (aprilTagId == 6 || aprilTagId == 5) {
      mLedSubsystem.set_led_color(Constants.AMP_BLUE);

      // no lime light
    } else {
      mLedSubsystem.set_led_color(Constants.NO_TAG_PURPLE);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}