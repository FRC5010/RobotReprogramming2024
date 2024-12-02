// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.sensors.ValueSwitch;
import org.frc5010.common.sensors.encoder.GenericEncoder;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotClimb extends GenericSubsystem {
  PercentControlMotor leftClimbMotor;
  PercentControlMotor rightClimbMotor;
  GenericEncoder rightEncoder;
  GenericEncoder leftEncoder;
  ValueSwitch leftCurrentSwitch;
  ValueSwitch rightCurrentSwitch;
  private final double maxHeight = 100;
  private final double minMaxThreshold = 1;
  /** Creates a new RobotClimb. */
  public RobotClimb(Mechanism2d simulatedRobot, PercentControlMotor leftMotor, PercentControlMotor rightMotor) {
      setMechSimulation(simulatedRobot);
      leftClimbMotor = leftMotor;
      rightClimbMotor = rightMotor;
      leftEncoder = leftMotor.getMotorEncoder();
      rightEncoder = rightMotor.getMotorEncoder();
      leftEncoder.setPositionConversion (1.0);
      rightEncoder.setPositionConversion (1.0);
      Supplier<Double> leftCurrent = ()-> ((CANSparkMax)leftClimbMotor.getMotor()).getOutputCurrent();
      Supplier<Double> rightCurrent = ()-> ((CANSparkMax)rightClimbMotor.getMotor()).getOutputCurrent();
      leftCurrentSwitch = new ValueSwitch(40.0, leftCurrent, 1.0);
      rightCurrentSwitch = new ValueSwitch(40.0, rightCurrent, 1.0);
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

public Trigger leftCurrentSwitchIsHit () {
  return new Trigger(()-> leftCurrentSwitch.get());
}

public Trigger rightCurrentSwitchIsHit () {
  return new Trigger(()-> rightCurrentSwitch.get());
}

public boolean leftIsAtMin () {
  return getLeftPosition()<minMaxThreshold;
}

public boolean rightIsAtMin () {
  return getRightPosition()<minMaxThreshold;
}

public boolean leftIsAtMax () {
  return getRightPosition()>(maxHeight-minMaxThreshold);
}

public boolean rightIsAtMax () {
  return getRightPosition()>(maxHeight-minMaxThreshold);
}

public Command zeroClimb () {
  return Commands.parallel (zeroLeftClimb(), zeroRightClimb());
}

private void resetLeftEncoder () {
  leftEncoder.reset ();
}

private void resetRightEncoder () {
  rightEncoder.reset ();
}

public Command zeroLeftClimb () {
  return Commands.run(()-> setSpeedLeft(0.5))
    .until(leftCurrentSwitchIsHit())
    .andThen(Commands.runOnce(this::resetLeftEncoder));
    

}

public Command zeroRightClimb () {
  return Commands.run(()-> setSpeedRight(0.5), this)
    .until(rightCurrentSwitchIsHit())
    .andThen(Commands.runOnce(this::resetRightEncoder));
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
