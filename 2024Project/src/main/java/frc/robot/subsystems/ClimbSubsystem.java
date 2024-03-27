/**
 * Climb Subsystem: This subsytem is dedicated to the Pneumatic control.
 * 
 * Pneumatics work via a double solenoid and here you can just move them up and down.
 */

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
// import com.revrobotics.CANSparkLowLevel.MotorType;
// import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private static DoubleSolenoid m_pistons;

  /** Creates a new climbSubsystem. */
  public ClimbSubsystem() {

    //PNEUMATICS
    m_pistons = new DoubleSolenoid(Constants.COMPRESSOR_MODULE_NUMBER, PneumaticsModuleType.REVPH, Constants.FORWARDS_SOLENOID_PIN, Constants.BACKWARDS_SOLENOID_PIN);
    m_pistons.set(DoubleSolenoid.Value.kReverse);


      
  }

  // this function should make both the left piston and the right piston raise
  public void raise() {
        m_pistons.toggle();

  }

  // this function should make them lower
  public void lower(){
        m_pistons.set(DoubleSolenoid.Value.kReverse);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
