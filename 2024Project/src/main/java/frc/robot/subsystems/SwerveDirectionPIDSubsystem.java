// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;


import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;





public class SwerveDirectionPIDSubsystem extends PIDSubsystem {
  /** Creates a new SwerveDirectionPIDSubsystem. */
SwerveDriveWheel m_DriveWheel;
CANcoder directionSensor;
public int directionMotorpin;
public CANSparkMax speedMotor;
public RelativeEncoder directionEncoder;


public DoubleSupplier directionSetpoint;

public CANSparkMax directionMotor;

  public SwerveDirectionPIDSubsystem(int directionSensorPin, int directionPin) {
    super(
        // The PIDController used by the subsystem
        new PIDController(Constants.DirectionP, Constants.DirectionI, Constants.DirectionD));
this.directionMotor = new CANSparkMax(directionPin, MotorType.kBrushless);
    //System.out.println("PID actinit");
    getController().enableContinuousInput(0, 360);
this.directionSensor = new CANcoder(directionSensorPin);


  }

  @Override
  public void useOutput(double output, double setpoint) {


    directionMotor.set(output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here


    return directionSensor.getAbsolutePosition().getValueAsDouble() * 360;
    
  }
  public void setDirection(double setpoint)
    {
   setSetpoint(setpoint);

    }
 
}
