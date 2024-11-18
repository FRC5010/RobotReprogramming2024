// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.MotorTesterExample;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.constants.GenericPID;
import org.frc5010.common.motors.function.VelocityControlMotor;
import org.frc5010.common.sensors.encoder.GenericEncoder;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;

public class VelocityRunner extends GenericSubsystem {
  /** Creates a new VelocityKrakenRunner. */

  VelocityControlMotor motor;
  GenericEncoder encoder;
  String MOTOR_SPEED_STRING = "Motor Speed";

  public VelocityRunner(VelocityControlMotor motor, Mechanism2d mechanism) {
    super(mechanism);
    this.motor = motor;
    this.encoder = motor.getMotorEncoder();
    mechanismSimulation = mechanism;
    values.declare(MOTOR_SPEED_STRING, 0.0);

    motor.setValues(new GenericPID(0.5, 0.0, 0.01));
    motor.setVisualizer(mechanismSimulation, new Pose3d(0.0, 1.0, 1.0, new Rotation3d()), "Velocity Runner");
    motor.setupSimulatedMotor(1.0, 1.0);
  }

  public void setReferenceSpeed(double rpm) {
    motor.setReference(rpm);
  }

  public double getMotorSpeed() {
    return encoder.getVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    motor.draw();
    values.set(MOTOR_SPEED_STRING, getMotorSpeed());
  }

  public void simulationPeriodic() {
    motor.simulationUpdate();
  }
}
