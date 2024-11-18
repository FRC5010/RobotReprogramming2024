// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.MotorTesterExample;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.function.PercentControlMotor;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;

public class BasicKrakenRunner extends GenericSubsystem {

  PercentControlMotor motor;

  /** Creates a new BasicKrakenRunner. */
  public BasicKrakenRunner(Mechanism2d mechVisual, PercentControlMotor motor) {
    super(mechVisual);
    mechanismSimulation = mechVisual;
    this.motor = motor;

    motor.setVisualizer(mechanismSimulation, new Pose3d(1.0, 1.0, 1.0, new Rotation3d()), "Kraken Runner");
    motor.setupSimulatedMotor(1.0, 1.0);
  }

  public void runMotor(double percentage) {
    motor.set(percentage);
  }

  public double getMotorSpeed() {
    return motor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    motor.draw();
  }

  public void simulationPeriodic() {
    motor.simulationUpdate();
  }
}
