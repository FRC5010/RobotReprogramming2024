// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.util.function.DoubleSupplier;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.MotorFactory;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.telemetry.DisplayBoolean;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class FeederSubsystem extends GenericSubsystem {



PercentControlMotor motor;
  DigitalInput detect;
  DigitalInput stop;
  DisplayBoolean detectDisplay;
  DisplayBoolean detectStopDisplay;
  public enum FeederState{
    Idle,
    Loading,
    Loaded
  }
  public FeederState state = FeederState.Idle;
  /** Creates a new Feeder. */
  public FeederSubsystem(Mechanism2d simulatedRobot) {
    super(simulatedRobot);
    setMechSimulation(simulatedRobot);

    motor = new PercentControlMotor(MotorFactory.NEO(11));

    motor.setVisualizer(mechanismSimulation, new Pose3d(0.15, 1.0, 0.4, new Rotation3d()), "Feeder Neo");
    motor.setupSimulatedMotor(1.0, 1.0);
    detect = new DigitalInput(3);
    stop = new DigitalInput(1);
    detectStopDisplay = new DisplayBoolean(false, "stop display", "feeder subsystem");
    detectDisplay = new DisplayBoolean(false, "detect", "feeder subsystem");

  }
  

  @Override
  public void periodic() {
    motor.draw();
    detectStopDisplay.setValue(stop.get());
    detectDisplay.setValue(detect.get());
  }

  @Override
  public void simulationPeriodic(){
    motor.simulationUpdate();
  }
  public void runMotor(double speed){
    motor.set(speed);

  }
  public Command runFeeder(DoubleSupplier doubleSupplier){
    return Commands.run(()->runMotor(doubleSupplier.getAsDouble()), this);
  }
  public Command runFeeder(double speed){
     return Commands.runOnce(()->runMotor(speed), this);
  }
  public Command acceptNote(){
    return runFeeder(()->.5).until(detected).andThen(runFeeder(()->.3)).until(atStop).andThen(()->loading()).finallyDo(()->runMotor(0));
  }
  public Command loadNote(){
    return runFeeder(()->-.2).until(atStop.negate()).andThen(()->loaded()).finallyDo(()->runMotor(0));
  }
  public void setState(FeederState state){
    this.state = state;
  }
  public void loading(){
    setState(FeederState.Loading);
  }
  public void loaded(){
    setState(FeederState.Loaded);
  }
  public void idle(){
    setState(FeederState.Idle);
  }
  
  public Trigger atStop = new Trigger(()->stop.get());
  public Trigger detected = new Trigger(()->detect.get());
  public Trigger isIdle = new Trigger(()->state == FeederState.Idle);
  public Trigger isLoading = new Trigger(()->state == FeederState.Loading);
  public Trigger isLoaded = new Trigger(()->state == FeederState.Loaded);
  
}
