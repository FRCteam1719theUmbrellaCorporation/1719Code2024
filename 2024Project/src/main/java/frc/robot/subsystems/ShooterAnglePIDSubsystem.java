/*
 * Shooter Angle PID Subsytem: this command allows us to change the position of the arm using a setpoint 
 * 
 * It also holds the manual control boolean, which allows us to manually control it in other parts
 */

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import frc.robot.Constants;


public class ShooterAnglePIDSubsystem extends PIDSubsystem {
  /** Creates a new ShooterAnglePIDSubsystem. */

  // public DigitalInput armLowerLimit = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH_PIN);
  public DutyCycleEncoder ShootAngleEncoder;
  public CANSparkMax ShootAngleMotor;

  // used by intake state
  public boolean isIntaking = false;

  // used to control manual control
  public boolean manualControl = false;
  
  //change to actual encoder
  public ShooterAnglePIDSubsystem() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Constants.ShootAngleP, Constants.ShootAngleI, Constants.ShootAngleD));
         {
          
        };

        // This is the through bore encoder
        this.ShootAngleEncoder = new DutyCycleEncoder(Constants.ShootAngleEncoder_PIN);

        // This motor controls the arm
        this.ShootAngleMotor = new CANSparkMax(Constants.ShootAngleMotorPin,MotorType.kBrushless);
  }

  // Sets if we're intaking or not
  public void setIntakeState(boolean state) {
    this.isIntaking = state;
  }

  // gets manual control TODO: make this just a public thing its the same
  public boolean getIntakeState() {
    return this.isIntaking;
  }

    // sets manual control
    public void setManualControl(boolean state) {
      manualControl = state;
    }

  public double shootAngle() {
      return getMeasurement();   
          }

  // sets the motor based on the output
  @Override
  public void useOutput(double output, double setpoint) {

    // TODO: implement limit switch
    // if (armLowerLimit.get() && output > 0){
    //   output = 0;
    // } 
ShootAngleMotor.set(output);
  }

  //this sets the arm position in degrees
  @Override
  public double getMeasurement() {
    double AngleDegrees = ShootAngleEncoder.getAbsolutePosition()*360.0 - Constants.SHOOTER_ANGLE_ZEROPOINT_OFFSET - Constants.SHOOTER_ANGLE_CORRECTION;
    
    // // Return the process variable measurement here
    // System.out.println(AngleDegrees);
    return AngleDegrees;
    
  }
}


