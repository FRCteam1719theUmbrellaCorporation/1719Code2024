// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
// import com.revrobotics.CANSparkLowLevel.MotorType;
// import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private static DoubleSolenoid m_pistons;

    // private final Compressor m_Compressor;
  /** Creates a new climbSubsystem. */
  public ClimbSubsystem() {

    m_pistons = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.FORWARDS_SOLENOID_PIN, Constants.BACKWARDS_SOLENOID_PIN);
    m_pistons.set(DoubleSolenoid.Value.kReverse);
        System.out.println("pistons set");
    // m_Compressor = new Compressor(PneumaticsModuleType.CTREPCM)
  }

  // this function should make both the left piston and the right piston raise
  public void raise() {
        m_pistons.set(DoubleSolenoid.Value.kForward);

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
