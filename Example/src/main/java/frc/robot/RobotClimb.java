// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RobotClimb extends GenericSubsystem {
  PercentControlMotor motor
  /** Creates a new RobotClimb. */
  public RobotClimb(Mechanism2d simulatedRobot) {
      setMechSimulation(simulatedRobot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
