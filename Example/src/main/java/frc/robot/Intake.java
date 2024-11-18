// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.function.PercentControlMotor;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;



public class Intake extends GenericSubsystem {
  PercentControlMotor frontMotor; 
  PercentControlMotor backMotor;
  /** Creates a new Intake. */
  public Intake(PercentControlMotor frontMotor, PercentControlMotor backMotor, Mechanism2d mechVisual) {
    // motor setup
    this.frontMotor=frontMotor;
    this.backMotor=backMotor;
    backMotor.setFollow(frontMotor);

    // simulation setup
    mechanismSimulation = mechVisual;
    backMotor.setVisualizer(mechVisual, new Pose3d(1.0,1.0,1.0, new Rotation3d()), "backIntakeMotor");
    frontMotor.setVisualizer(mechVisual, new Pose3d(1.0,1.0,1.0, new Rotation3d()), "frontIntakeMotor");
    backMotor.setupSimulatedMotor(1.0, 1.0);
    frontMotor.setupSimulatedMotor(1.0, 1.0);
    setMechSimulation(mechVisual);

  }

  public void setIntakeSpeed(double intakeSpeed){
    frontMotor.set(intakeSpeed);
  }

  public double getIntakeSpeed(){
    return backMotor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    frontMotor.draw();
    backMotor.draw();
  }

  public void simulationPeriodic(){
    frontMotor.simulationUpdate();
    backMotor.simulationUpdate();
  }
}
