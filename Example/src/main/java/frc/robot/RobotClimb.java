// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.sensors.ValueSwitch;
import org.frc5010.common.sensors.encoder.GenericEncoder;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RobotClimb extends GenericSubsystem {
  PercentControlMotor leftClimbMotor;
  PercentControlMotor rightClimbMotor;
  GenericEncoder rightEncoder;
  GenericEncoder leftEncoder;
  ValueSwitch leftCurrentSwitch;
  ValueSwitch rightCurrentSwitch;
  /** Creates a new RobotClimb. */
  public RobotClimb(Mechanism2d simulatedRobot, PercentControlMotor leftMotor, PercentControlMotor rightMotor) {
      setMechSimulation(simulatedRobot);
      leftClimbMotor = leftMotor;
      rightClimbMotor = rightMotor;
      leftEncoder = leftMotor.getMotorEncoder();
      rightEncoder = rightMotor.getMotorEncoder();
      leftEncoder.setPositionConversion (1.0);
      rightEncoder.setPositionConversion (1.0);
      leftCurrentSwitch = new ValueSwitch(40.0, ()->0.0, 1.0);
      leftCurrentSwitch = new ValueSwitch(40.0, ()->0.0, 1.0);
  }

public void setSpeedRight (double speedRight) {
  rightClimbMotor.set(speedRight);
}
public void setSpeedLeft (double speedLeftt) {
  leftClimbMotor.set(speedLeftt);
}

public double getLeftPosition () {
  return leftEncoder.getPosition();
}

public double getRightPosition () {
  return rightEncoder.getPosition();
}

public boolean leftIsAtMin () {
  return false;
}

public boolean rightIsAtMin () {
  return false;
}

public boolean leftIsAtMax () {
  return false;
}

public boolean rightIsAtMax () {
  return false;
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
