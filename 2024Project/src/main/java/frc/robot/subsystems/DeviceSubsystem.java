/*
Device Subsytem: This subsystem controls the device motors and sensors

Specifcally control over the shooter and intake motors and the proximity sensor (for sensing the note!)
*/

package frc.robot.subsystems;
import com.revrobotics.ColorSensorV3;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// import com.fasterxml.jackson.databind.introspect.AnnotationCollector.OneAnnotation;
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;



// Motors
public class DeviceSubsystem extends SubsystemBase {

    // control the arm 


    // these 2 controll the the motors of the shooter and intake
    private static CANSparkMax SHOOTER;
    private static CANSparkMax INTAKE;


    private int proximity;
    private ColorSensorV3 m_colorSensor = Constants.m_colorSensor;


    public DeviceSubsystem() {

       SHOOTER = new CANSparkMax(Constants.SHOOTER, MotorType.kBrushless);
       INTAKE = new CANSparkMax(Constants.INTAKE, MotorType.kBrushless);


    }


    //turns the shooter motors with a double
    public void turnShooterMotors(double speed) {
        SHOOTER.set(speed);
        
    }

    //turns the intake motors with a double
    public void turnIntakeMotors(double speed) { 
        INTAKE.set(speed*0.5);
    }

    // turns off the shooter
    public void turnOffShooter(){
        SHOOTER.set(0);
    }

    // turns off the intake motors
    public void turnOffIntakeMotors() {
        // SHOOTER.set(0);
        INTAKE.set(0);
    }

    // returns a boolean based on if the ring is in the system or not
    public boolean checkRing(){ 
        // gets the proximity
        proximity = m_colorSensor.getProximity();

        // returns true if the note is sensed.
        if (proximity >= Constants.DISTANCE_NOTE_IN) {
            return true;
        } 
        else {
            return false;
        }
    }
}
