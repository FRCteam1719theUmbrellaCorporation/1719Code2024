// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  public static Pigeon2 GYRO = new Pigeon2(Constants.CAN_GYRO_PORT);

  // Robot auton choices
  private final String RedOnePosition = "RedAmp2note";
  private final String RedThreePosition = "RedClimber2note";
  private final String BlueOnePosition = "BlueClimber2note";
  private final String TwoPosition = "RedOrBlueCenter2note";
  private final String BlueThreePosition = "BlueAmp2note";
  private final String TEST3NOTE = "Center3note(test)";

  private final String AutoLeftGoodTeams = "AutoLeftGoodTeams";
  private final String AutoCenterGoodTeams = "AutoCenterGoodTeams";
  private final String AutoRightGoodTeams = "AutoRightGoodTeams";

  // private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    CameraServer.startAutomaticCapture();
    UsbCamera USBCAM = CameraServer.startAutomaticCapture();
    USBCAM.setResolution(720, 540);


    // makes a new camera I HOPE :)
    CameraServer.startAutomaticCapture();

    m_robotContainer = new RobotContainer();
    GYRO.reset();
 
   // autonomousCommand = new ;

    // Setup smart dashboard to choose auton
    m_chooser.setDefaultOption("RedOrBlueCenter2note", TwoPosition);
    m_chooser.addOption("RedAmp2note", RedOnePosition);
    m_chooser.addOption("RedClimber2note", RedThreePosition);
    m_chooser.addOption("BlueAmp2note", BlueThreePosition);
    m_chooser.addOption("BlueClimber2note", BlueOnePosition);
    // m_chooser.addOption("RedOrBlueCenter2note", TwoPosition); THIS IS COMMENTED BECAUSE IT IS DEFAULT OPTION
    m_chooser.addOption("Center3note(test)", TEST3NOTE);

    m_chooser.addOption("AutoRightGoodTeams", AutoLeftGoodTeams);
    m_chooser.addOption("AutoCenterGoodTeams", AutoCenterGoodTeams);
    m_chooser.addOption("AutoLeftGoodTeams", AutoRightGoodTeams);

    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //System.out.println("WORKING");
   m_autonomousCommand = m_robotContainer.getAutonomousCommand(m_chooser.getSelected());
      // m_autonomousCommand = m_robotContainer.getAutonomousCommand("default");

  

  }
  // Return the instance of the gyroscope
  public static Pigeon2 getGYRO() {
    return GYRO;
  }
  // Zero the gyroscope
  public static void zeroGYRO() {
    GYRO.reset();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
 
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
