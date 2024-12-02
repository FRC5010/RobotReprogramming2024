// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot ;

import org.frc5010.common.arch.GenericSubsystem;
import org.frc5010.common.motors.MotorController5010;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.motors.hardware.KrakenX60;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends GenericSubsystem {

  // motors
  PercentControlMotor bottomMotor;
  PercentControlMotor topMotor;

  /** Creates a new Shooter. */
  public Shooter(PercentControlMotor bottomMotor, PercentControlMotor topMotor, Mechanism2d simulatedRobot) {
    this.bottomMotor = bottomMotor;
    this.topMotor = topMotor;

    //sets simulation - gearing, pose3d, and momentOfInertialKgMetersSq
    topMotor.setVisualizer(simulatedRobot, new Pose3d(0, 0, 0, new Rotation3d()), "topShooterMotor");
    topMotor.setupSimulatedMotor(1, 1);
    bottomMotor.setVisualizer(simulatedRobot, new Pose3d(0, 0, 1, new Rotation3d()), "bottomShooterMotor");
    bottomMotor.setupSimulatedMotor(1, 1);

  }

  public void setShooterSpeed(double shooterSpeed) {
    bottomMotor.set(shooterSpeed);
    topMotor.set(shooterSpeed);
  }

  public double getShooterSpeed() {
    return topMotor.get();
  }

  @Override
  public void simulationPeriodic() {
    topMotor.simulationUpdate();
    bottomMotor.simulationUpdate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    topMotor.draw();
    bottomMotor.draw();
  }
}
