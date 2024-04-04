/*
 * LedSubsystem: Controls the led lights! Colors are sent in constants!
 */

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// WPILIB
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

// ROBOT
import frc.robot.Constants;

public class LedSubsystem extends SubsystemBase {
  private static Spark m_blinkin = null;  
  private LimelightSubsystem m_limelight;
  private DeviceSubsystem m_device;
//   private double tag;
  private double lastRunTime = Timer.getFPGATimestamp();

  /** Creates a new LedSubsystem. */
  public LedSubsystem(LimelightSubsystem m_lime, DeviceSubsystem m_dev) {
    m_blinkin = new Spark(Constants.LED_PWM_PIN);
    m_limelight = m_lime;
    m_device = m_dev;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double currentTime = Timer.getFPGATimestamp();
    if (currentTime - lastRunTime > Constants.LED_TIMING_DELAY) {
      computeLEDColor();
      currentTime = Timer.getFPGATimestamp();
    }
  }

  // Set all led's to given color given a value
  public void set_led_color(double val) {
    if (val >= -1.0 && val <= 1.0) {
      m_blinkin.set(val);
    }
  }

  public void computeLEDColor() {
    double tag = m_limelight.tagID;
    // Baseline
    // ID 4 or 7 is Speaker
    if (tag == 4 || tag == 7) {
      set_led_color(Constants.SPEAKER_COLOR);
    } 
    // ID 5 or 6 is Amp
    else if (tag == 5 || tag == 6) {
      set_led_color(Constants.AMP_COLOR);
    } 
    else {
      set_led_color(Constants.BASELINE_COLOR);
      // Has Ring
      if (m_device.checkRing()) {
        set_led_color(Constants.HAS_NOTE_COLOR);
      }
    }
  }
}
