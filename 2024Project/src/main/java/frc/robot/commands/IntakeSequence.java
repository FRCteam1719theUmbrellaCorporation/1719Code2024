// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import java.time.Instant;
// import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
// import frc.robot.Robot;
import frc.robot.subsystems.DeviceSubsystem;
import frc.robot.subsystems.ShooterAnglePIDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class IntakeSequence extends SequentialCommandGroup {
  /** Creates a new ShootSequence. */
  DeviceSubsystem mDeviceSubsystem;

  private double ANGLEAIM;

  public WaitCommand waitwait (double time) {
    return new WaitCommand(time);
  }
  public InstantCommand IntakeMotors (int direction) {
    return new InstantCommand(()->mDeviceSubsystem.turnIntakeMotors(-direction));
  }
  public InstantCommand setIntakeState(ShooterAnglePIDSubsystem m_angler, boolean state) {
    return new InstantCommand(()-> m_angler.setIntakeState(state));
  }
  public InstantCommand setIntakeSetpoint(ShooterAnglePIDSubsystem m_angler) {
    return new InstantCommand(()->m_angler.setSetpoint(ANGLEAIM));
    
  }

  public IntakeSequence(DeviceSubsystem mDeviceSubsystem, ShooterAnglePIDSubsystem m_angler, String action, double ANGLEAIM) {
    this.mDeviceSubsystem = mDeviceSubsystem;
    this.ANGLEAIM = ANGLEAIM;
    
    if (ANGLEAIM > Constants.MAX_SHOOTER_ANGLE) {
    ANGLEAIM = Constants.MAX_SHOOTER_ANGLE;
    }
    if (ANGLEAIM < Constants.MIN_SHOOTER_ANGLE) {
    ANGLEAIM = Constants.MIN_SHOOTER_ANGLE;
    }

    //Turn on intake
    if (action == "intakeOn") {
       addCommands(setIntakeState(m_angler, true), setIntakeSetpoint(m_angler), IntakeMotors(1)); 
    } 
    //Turn off intake
    else if (action == "intakeOff") {
       addCommands(IntakeMotors(0), setIntakeState(m_angler, false),IntakeMotors(0)); // same question as above
    }
    //Move shooter to an angle
    else if (action == "2") {
      addCommands(setIntakeState(m_angler, true), setIntakeSetpoint(m_angler));
    }

    // this should set the intake to false
     else if (action == "3") {
      addCommands(setIntakeState(m_angler, false));
    }

    //Turn on outtake
    else if (action == "-1") {
       addCommands(setIntakeState(m_angler, true), setIntakeSetpoint(m_angler), IntakeMotors(-1), waitwait(.05), IntakeMotors(0), setIntakeState(m_angler, false));
    } 
    //Special outtake for amp shooting
    else if (action == "-2") {
       addCommands(setIntakeState(m_angler, true), setIntakeSetpoint(m_angler), IntakeMotors(-1), waitwait(.5), IntakeMotors(0), setIntakeState(m_angler, false));
    } 

    // copy of state -1, where arm does NOT move down. this fixes aim
  else if (action == "-3") {
       addCommands(setIntakeSetpoint(m_angler), IntakeMotors(-1), waitwait(.05), IntakeMotors(0), setIntakeState(m_angler, false));
    } 
  }
       // Called once the command ends or is interrupted.

//   @Override 
//   public void end(boolean interrupted ) {
// mDeviceSubsystem.turnOffIntakeMotors();
//    }
}
