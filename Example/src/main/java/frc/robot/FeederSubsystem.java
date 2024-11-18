// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.util.function.DoubleSupplier;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.MotorFactory;
import org.frc5010.common.motors.function.PercentControlMotor;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;

public class FeederSubsystem extends GenericSubsystem {



PercentControlMotor motor;

  /** Creates a new Feeder. */
  public FeederSubsystem(Mechanism2d simulatedRobot) {
    setMechSimulation(simulatedRobot);

    motor = new PercentControlMotor(MotorFactory.NEO(11));

    motor.setVisualizer(mechanismSimulation, new Pose3d(0.05, 1.0, 0.4, new Rotation3d()), "Feeder Neo");
    motor.setupSimulatedMotor(1.0, 1.0);

  }

  @Override
  public void periodic() {
    motor.draw();

  }

  @Override
  public void simulationPeriodic(){
    motor.simulationUpdate();
  }
  public void runMotor(DoubleSupplier speed){}
}
