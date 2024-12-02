// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import org.frc5010.common.arch.GenericCommand;
import org.frc5010.common.sensors.camera.GenericCamera;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotClimb;

public class BasicRunClimb extends GenericCommand {
  /** Creates a new BasicRunClimb. */
  RobotClimb climbSubsystem;
  DoubleSupplier leftSpeed;
  DoubleSupplier rightSpeed;
  public BasicRunClimb(RobotClimb climbSubsystem, DoubleSupplier rightSpeed, DoubleSupplier leftSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climbSubsystem = climbSubsystem;
    this.rightSpeed = rightSpeed;
    this.leftSpeed = leftSpeed;
    addRequirements(climbSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void init() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbSubsystem.setSpeedLeft (leftSpeed.getAsDouble());
    climbSubsystem.setSpeedRight (rightSpeed.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void stop (boolean interrupted) {
    climbSubsystem.setSpeedLeft (0);
    climbSubsystem.setSpeedRight (0);
  }
  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
