// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.commands.SwerveDirectionPIDCommand;
// import frc.robot.subsystems.SwerveSubsystem.SwerveDriveWheel.SwerveDriveCoordinator;
//import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
//import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Robot;
/*import frc.robot.subsystems.SwerveDriveWheel;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
*/
// import frc.lib.util.SwerveModuleConstants;

// import frc.lib.util.CANSparkMaxUtil.Usage;
// import frc.lib.util.CANSparkMaxUtil;
// import frc.lib.math.OnboardModuleState;
// import frc.lib.util.CANCoderUtil;
// import frc.lib.util.CANCoderUtil.CCUsage;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkPIDController;
// import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class SwerveSubsystem extends SubsystemBase {
    /** Creates a new SwerveSubsystem. */

    // Motors
    private static CANSparkMax LEFT_FRONT_DRIVE_SPEED_MOTOR;
    private static CANSparkMax LEFT_BACK_DRIVE_SPEED_MOTOR;
    private static CANSparkMax RIGHT_FRONT_DRIVE_SPEED_MOTOR;
    private static CANSparkMax RIGHT_BACK_DRIVE_SPEED_MOTOR;

    public SwerveDirectionPIDSubsystem m_leftFrontDirection;
    public SwerveDirectionPIDSubsystem m_rightFrontDirection;
    public SwerveDirectionPIDSubsystem m_leftBackDirection;
    public SwerveDirectionPIDSubsystem m_rightBackDirection;

    Translation2d m_frontLeftLocation = new Translation2d(0.3,0.3);
    Translation2d m_frontRightLocation = new Translation2d(0.3,-0.3);
    Translation2d m_backLeftLocation = new Translation2d(-0.3,0.3);
    Translation2d m_backRightLocation = new Translation2d(-0.3 ,-0.3);;

    SwerveDriveKinematics m_Kinematics = new SwerveDriveKinematics(m_frontLeftLocation,  m_backLeftLocation, m_frontRightLocation, m_backRightLocation);

    // private static CANSparkMax LEFT_FRONT_DRIVE_DIRECTION_MOTOR;
    // private static CANSparkMax LEFT_BACK_DRIVE_DIRECTION_MOTOR;
    // private static CANSparkMax RIGHT_FRONT_DRIVE_DIRECTION_MOTOR;
    // private static CANSparkMax RIGHT_BACK_DRIVE_DIRECTION_MOTOR;

    // Encoders
    public static RelativeEncoder LEFT_FRONT_DRIVE_DISTANCE_ENCODER;
    public static RelativeEncoder LEFT_BACK_DRIVE_DISTANCE_ENCODER;
    public static RelativeEncoder RIGHT_FRONT_DRIVE_DISTANCE_ENCODER;
    public static RelativeEncoder RIGHT_BACK_DRIVE_DISTANCE_ENCODER;

    // public static CANcoder LEFT_FRONT_DRIVE_DIRECTION_ENCODER;
    // public static CANcoder LEFT_BACK_DRIVE_DIRECTION_ENCODER;
    // public static CANcoder RIGHT_FRONT_DRIVE_DIRECTION_ENCODER;
    // public static CANcoder RIGHT_BACK_DRIVE_DIRECTION_ENCODER;

    // Direction encoder wrapper that scales to degrees
    // public static DoubleSupplier LEFT_FRONT_DRIVE_DIRECTION_SCALED;
    // public static DoubleSupplier LEFT_BACK_DRIVE_DIRECTION_SCALED;
    // public static DoubleSupplier RIGHT_FRONT_DRIVE_DIRECTION_SCALED;
    // public static DoubleSupplier RIGHT_BACK_DRIVE_DIRECTION_SCALED;

    SwerveDriveWheel LEFT_FRONT_DRIVE_WHEEL;
    SwerveDriveWheel LEFT_BACK_DRIVE_WHEEL;
    SwerveDriveWheel RIGHT_FRONT_DRIVE_WHEEL;
    SwerveDriveWheel RIGHT_BACK_DRIVE_WHEEL;

    public boolean isFieldOriented = true;
    // Gyro
    public static Pigeon2 DRIVE_GYRO;
    public SwerveDriveCoordinator SWERVE_DRIVE_COORDINATOR;

    public SwerveSubsystem(SwerveDirectionPIDSubsystem m_leftFrontDirection,
            SwerveDirectionPIDSubsystem m_leftBackDirection, SwerveDirectionPIDSubsystem m_rightFrontDirection,
            SwerveDirectionPIDSubsystem m_rightBackDirection) {
        // Motors
        LEFT_FRONT_DRIVE_SPEED_MOTOR = new CANSparkMax(Constants.LEFT_FRONT_DRIVE_SPEED_MOTOR_PIN,
                MotorType.kBrushless);
        LEFT_BACK_DRIVE_SPEED_MOTOR = new CANSparkMax(Constants.LEFT_BACK_DRIVE_SPEED_MOTOR_PIN, MotorType.kBrushless);
        RIGHT_FRONT_DRIVE_SPEED_MOTOR = new CANSparkMax(Constants.RIGHT_FRONT_DRIVE_SPEED_MOTOR_PIN,
                MotorType.kBrushless);
        RIGHT_BACK_DRIVE_SPEED_MOTOR = new CANSparkMax(Constants.RIGHT_BACK_DRIVE_SPEED_MOTOR_PIN,
                MotorType.kBrushless);

        this.m_leftFrontDirection = m_leftFrontDirection;
        this.m_rightFrontDirection = m_rightFrontDirection;
        this.m_leftBackDirection = m_leftBackDirection;
        this.m_rightBackDirection = m_rightBackDirection;
        // LEFT_FRONT_DRIVE_DIRECTION_MOTOR = new
        // CANSparkMax(Constants.LEFT_FRONT_DRIVE_DIRECTION_MOTOR_PIN,
        // MotorType.kBrushless);
        // LEFT_BACK_DRIVE_DIRECTION_MOTOR = new
        // CANSparkMax(Constants.LEFT_BACK_DRIVE_DIRECTION_MOTOR_PIN,
        // MotorType.kBrushless);
        // RIGHT_FRONT_DRIVE_DIRECTION_MOTOR = new
        // CANSparkMax(Constants.RIGHT_FRONT_DRIVE_DIRECTION_MOTOR_PIN,
        // MotorType.kBrushless);
        // RIGHT_BACK_DRIVE_DIRECTION_MOTOR = new
        // CANSparkMax(Constants.RIGHT_BACK_DRIVE_DIRECTION_MOTOR_PIN,
        // MotorType.kBrushless);



        // DRIVE_ENCODERS = new MedianPIDSource(LEFT_FRONT_DRIVE_DISTANCE_ENCODER,
        // LEFT_BACK_DRIVE_DISTANCE_ENCODER, RIGHT_FRONT_DRIVE_DISTANCE_ENCODER,
        // RIGHT_BACK_DRIVE_DISTANCE_ENCODER);
        // LEFT_FRONT_DRIVE_DIRECTION_ENCODER = new
        // CANcoder(Constants.LEFT_FRONT_DRIVE_DIRECTION_ENCODER_PIN);
        // LEFT_BACK_DRIVE_DIRECTION_ENCODER = new
        // CANcoder(Constants.LEFT_BACK_DRIVE_DIRECTION_ENCODER_PIN);
        // RIGHT_FRONT_DRIVE_DIRECTION_ENCODER = new
        // CANcoder(Constants.RIGHT_FRONT_DRIVE_DIRECTION_ENCODER_PIN);
        // RIGHT_BACK_DRIVE_DIRECTION_ENCODER = new
        // CANcoder(Constants.RIGHT_BACK_DRIVE_DIRECTION_ENCODER_PIN);

        // Direction encoder wrapper that scales to degrees
        // DoubleSupplier LEFT_FRONT_DRIVE_DIRECTION_SCALED = () ->
        // LEFT_FRONT_DRIVE_DIRECTION_ENCODER.getPosition().getValueAsDouble() * 360;
        // DoubleSupplier LEFT_BACK_DRIVE_DIRECTION_SCALED = () ->
        // LEFT_FRONT_DRIVE_DIRECTION_ENCODER.getPosition().getValueAsDouble()* 360;
        // DoubleSupplier RIGHT_FRONT_DRIVE_DIRECTION_SCALED =() ->
        // LEFT_FRONT_DRIVE_DIRECTION_ENCODER.getPosition().getValueAsDouble()* 360;
        // DoubleSupplier RIGHT_BACK_DRIVE_DIRECTION_SCALED = () ->
        // LEFT_FRONT_DRIVE_DIRECTION_ENCODER.getPosition().getValueAsDouble()* 360;

        // Gyro
        DRIVE_GYRO = Robot.getGYRO();

        // SwerveDriveWheels
        LEFT_FRONT_DRIVE_WHEEL = new SwerveDriveWheel(LEFT_FRONT_DRIVE_SPEED_MOTOR, m_leftFrontDirection);
        LEFT_BACK_DRIVE_WHEEL = new SwerveDriveWheel(LEFT_BACK_DRIVE_SPEED_MOTOR, m_leftBackDirection);
        RIGHT_FRONT_DRIVE_WHEEL = new SwerveDriveWheel(RIGHT_FRONT_DRIVE_SPEED_MOTOR, m_rightFrontDirection);
        RIGHT_BACK_DRIVE_WHEEL = new SwerveDriveWheel(RIGHT_BACK_DRIVE_SPEED_MOTOR, m_rightBackDirection);

        // SwerveDriveCoordinator
        SWERVE_DRIVE_COORDINATOR = new SwerveDriveCoordinator(LEFT_FRONT_DRIVE_WHEEL, LEFT_BACK_DRIVE_WHEEL,
                RIGHT_FRONT_DRIVE_WHEEL, RIGHT_BACK_DRIVE_WHEEL);


        // Encoders
        LEFT_FRONT_DRIVE_DISTANCE_ENCODER = LEFT_FRONT_DRIVE_SPEED_MOTOR.getEncoder();
        LEFT_BACK_DRIVE_DISTANCE_ENCODER = LEFT_BACK_DRIVE_SPEED_MOTOR.getEncoder();
        RIGHT_FRONT_DRIVE_DISTANCE_ENCODER = RIGHT_FRONT_DRIVE_SPEED_MOTOR.getEncoder();
        RIGHT_BACK_DRIVE_DISTANCE_ENCODER = RIGHT_BACK_DRIVE_SPEED_MOTOR.getEncoder();

        // LEFT_BACK_DRIVE_DISTANCE_ENCODER = new
        // CANcoder(Constants.LEFT_BACK_DRIVE_DISTANCE_ENCODER_PIN);
        // RIGHT_FRONT_DRIVE_DISTANCE_ENCODER = new
        // CANcoder(Constants.RIGHT_FRONT_DRIVE_DISTANCE_ENCODER_PIN);
        // RIGHT_BACK_DRIVE_DISTANCE_ENCODER = new
        // CANcoder(Constants.RIGHT_BACK_DRIVE_DISTANCE_ENCODER_PIN);
    }

    public class degreeSupplier {
        public CANcoder encoder;

        public degreeSupplier(CANcoder encoder) {
            this.encoder = encoder;

        }

        public double getDegrees() {
            return (this.encoder.getPosition().getValueAsDouble() % 1) * 360;

        }

        public double getAsDouble() {
            return (this.encoder.getPosition().getValueAsDouble() % 1) * 360;

        }

    }

    public class SwerveDriveCoordinator {
        SwerveDriveWheel leftFrontWheel;
        SwerveDriveWheel leftBackWheel;
        SwerveDriveWheel rightFrontWheel;
        SwerveDriveWheel rightBackWheel;

        public SwerveDriveCoordinator(SwerveDriveWheel leftFrontWheel, SwerveDriveWheel leftBackWheel,
                SwerveDriveWheel rightFrontWheel, SwerveDriveWheel rightBackWheel) {
            this.leftFrontWheel = leftFrontWheel;
            this.leftBackWheel = leftBackWheel;
            this.rightFrontWheel = rightFrontWheel;
            this.rightBackWheel = rightBackWheel;

        }
        public void CartesianChassisSpeeds(double x, double y, double twist){
        //   ChassisSpeeds basicspeeds =  new ChassisSpeeds(x,y,twist);
           ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
  x, y, twist, DRIVE_GYRO.getRotation2d());

            SwerveModuleState[] moduleStates = m_Kinematics.toSwerveModuleStates(speeds);
            SwerveModuleState frontLeft =moduleStates[1];
            SwerveModuleState backLeft =moduleStates[0];
            SwerveModuleState frontRight =moduleStates[3];
            SwerveModuleState backRight =moduleStates[2];
            leftFrontWheel.SwerveSetWithState(frontLeft);
            leftBackWheel.SwerveSetWithState(backLeft);
            rightFrontWheel.SwerveSetWithState(frontRight);
            rightBackWheel.SwerveSetWithState(backRight);

            //***
            var frontLeftOptimized = SwerveModuleState.optimize(frontLeft,
   new Rotation2d(leftFrontWheel.directionController.getMeasurement()));
            var frontRightOptimized = SwerveModuleState.optimize(frontRight,
   new Rotation2d(rightFrontWheel.directionController.getMeasurement()));
            var backLeftOptimized = SwerveModuleState.optimize(backLeft,
   new Rotation2d(leftBackWheel.directionController.getMeasurement()));
            var backRightOptimized = SwerveModuleState.optimize(backRight,
   new Rotation2d(rightBackWheel.directionController.getMeasurement()));
            //*** 

        }
        
        // public void setSwerveDrive(double direction, double translatePower, double turnPower) {
        //     // !! this was just 90 
        //      direction = isFieldOriented ? direction - (DRIVE_GYRO.getAngle()%360) : direction;// gryo on side hence +90 took out +90
        //     //  System.out.println(DRIVE_GYRO.getAngle());
        //     if ((translatePower > -0.10) && (translatePower < 0.10) && (Math.abs (turnPower) > 0.10)) {
        //         inplaceTurn(turnPower);
        //     } else {
        //         translateTurn(isFieldOriented ? direction - DRIVE_GYRO.getAngle() : direction, translatePower, turnPower);

        //     }
        // }

        public void translate(double direction, double power) {

            leftFrontWheel.setDirection(direction);
            leftBackWheel.setDirection(direction);
            rightFrontWheel.setDirection(direction);
            rightBackWheel.setDirection(direction);

            leftFrontWheel.speedMotors(power);
            leftBackWheel.speedMotors(power);
            rightFrontWheel.speedMotors(power);
            rightBackWheel.speedMotors(power);
        }

        public void inplaceTurn(double power) {
            leftFrontWheel.setDirection(225.0);
            leftBackWheel.setDirection(135.0);
            rightFrontWheel.setDirection(315.0);
            rightBackWheel.setDirection(45.0);
        
            leftFrontWheel.speedMotors(power/4);
            leftBackWheel.speedMotors(power/4);
            rightFrontWheel.speedMotors(power/4);
            rightBackWheel.speedMotors(power/4);
        }

        public void translateTurn(double direction, double translatePower, double turnPower) {
            double turnAngle = turnPower * 45.0;

            

             // if the left front wheel is in the front
            if (closestAngle(direction, 135.0) >= 90.0) {
                leftFrontWheel.setDirection(direction + turnAngle);
            }
            // if it's in the back
            else {
                leftFrontWheel.setDirection(direction - turnAngle);
            }
            // if the left back wheel is in the front
            if (closestAngle(direction, 225.0) > 90.0) {
                leftBackWheel.setDirection(direction + turnAngle);
            }
            // if it's in the back
            else {
                leftBackWheel.setDirection(direction - turnAngle);
            }
            // if the right front wheel is in the front
            if (closestAngle(direction, 45.0) > 90.0) {
                rightFrontWheel.setDirection(direction + turnAngle);
            }
            // if it's in the back
            else {
                rightFrontWheel.setDirection(direction - turnAngle);
            }
            // if the right back wheel is in the front
            if (closestAngle(direction, 315.0) >= 90.0) {
                rightBackWheel.setDirection(direction + turnAngle);
            }
            // if it's in the back
            else {
                rightBackWheel.setDirection(direction - turnAngle);
            }

            leftFrontWheel.speedMotors(translatePower);
            leftBackWheel.speedMotors(translatePower);
            rightFrontWheel.speedMotors(translatePower);
            rightBackWheel.speedMotors(translatePower);
        }
        public void drifTranslate(double direction, double translatePower, double turnPower){
                      direction = isFieldOriented ? direction - DRIVE_GYRO.getAngle(): -direction; //gyro on hootide hence +90 took out +90 +30 + 180

                      SwerveModuleState leftFrontPosition = convertToPower(translatePower, 225, turnPower, direction);
                      SwerveModuleState leftBackPosition = convertToPower(translatePower, 135, turnPower, direction);
                      SwerveModuleState rightFrontPosition = convertToPower(translatePower, 315, turnPower, direction);
                      SwerveModuleState rightBackPosition = convertToPower(translatePower, 45, turnPower, direction);
          leftFrontWheel.SwerveSetWithState(leftFrontPosition);
          leftBackWheel.SwerveSetWithState(leftBackPosition);
          rightFrontWheel.SwerveSetWithState(rightFrontPosition);
          rightBackWheel.SwerveSetWithState(rightBackPosition);
    }
    public SwerveModuleState convertToPower(double translatepower, double rotateDirection, double turnpower, double direction ){
        double turnangle = rotateDirection-direction;
        double forwardCoeficient = Math.cos(Math.toRadians(turnangle))*turnpower;
        double leftCoeficient = Math.sin(Math.toRadians(turnangle))*turnpower;
        forwardCoeficient+=translatepower;
        translatepower = Math.sqrt(forwardCoeficient*forwardCoeficient+leftCoeficient*leftCoeficient);
        Rotation2d turnangleRot = new Rotation2d(Math.atan2(leftCoeficient, forwardCoeficient)+ Math.toRadians(direction));
SwerveModuleState Moduleset = new SwerveModuleState( translatepower, turnangleRot);

return Moduleset;
    }

    }

    /**
     * Get the closest angle between the given angles.
     */
    public static double closestAngle(double a, double b) {
        // get direction
        double dir = modulo(b, 360.0) - modulo(a, 360.0);

        // convert from -360 to 360 to -180 to 180
        if (Math.abs(dir) > 180.0) {
            dir = -(Math.signum(dir) * 360.0) + dir;
        }
        return dir;
    }

    private static double modulo(double b, double d) {

        return b % d;
    }

    // reset the distance motors back to 0
    public void resetDistanceMotors() {
        LEFT_FRONT_DRIVE_DISTANCE_ENCODER.setPosition(0);
        LEFT_BACK_DRIVE_DISTANCE_ENCODER.setPosition(0);
        RIGHT_FRONT_DRIVE_DISTANCE_ENCODER.setPosition(0);
        RIGHT_BACK_DRIVE_DISTANCE_ENCODER.setPosition(0);
    }

    public double returnAverageDistance() {
        return (LEFT_FRONT_DRIVE_DISTANCE_ENCODER.getPosition() +
         LEFT_BACK_DRIVE_DISTANCE_ENCODER.getPosition() + 
         RIGHT_FRONT_DRIVE_DISTANCE_ENCODER.getPosition() + 
         RIGHT_BACK_DRIVE_DISTANCE_ENCODER.getPosition())/4;
    } 

    

    @Override
    public void periodic() {

        // This method will be called once per scheduler run
    }

}
