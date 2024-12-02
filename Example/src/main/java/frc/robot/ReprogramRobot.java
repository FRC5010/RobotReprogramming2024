// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericRobot;
import org.frc5010.common.config.ConfigConstants;
import org.frc5010.common.config.json.DrivetrainPropertiesJson;
import org.frc5010.common.constants.SwerveConstants;
import org.frc5010.common.drive.GenericDrivetrain;
import org.frc5010.common.sensors.Controller;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
// Reference Document Link: https://docs.google.com/document/d/16YqkkYDurYPHd9RxBJdwxWiMCAomJKIatphhviYabxw/edit?usp=sharing
/** This is an example robot class. */
public class ReprogramRobot extends GenericRobot {
  SwerveConstants swerveConstants;
  GenericDrivetrain drivetrain;
  FeederSubsystem feeder;

  public ReprogramRobot(String directory) {
    super(directory);
    drivetrain = (GenericDrivetrain) getSubsystem(ConfigConstants.DRIVETRAIN);
    feeder = new FeederSubsystem(mechVisual);
  }

  @Override
  public void configureButtonBindings(Controller driver, Controller operator) {
    driver.createAButton()
    .and(feeder.atStop.negate()
    .and(feeder.detected.negate()).and(isIdle))
    .onTrue(feeder.runFeeder(()->0.5));
    driver.createAButton()
    .or(feeder.atStop
    .or(feeder.detected))
    .onTrue(getAutonom)
  }


  @Override
  public void setupDefaultCommands(Controller driver, Controller operator) {
    drivetrain.setDefaultCommand(drivetrain.createDefaultCommand(driver));
    feeder.setDefaultCommand(Commands.run(() -> feeder.runMotor(()->driver.getLeftTrigger()), feeder));
  }

  @Override
  public void initAutoCommands() {
    drivetrain.setAutoBuilder();
  }

  @Override
  public Command generateAutoCommand(Command autoCommand) {
    return drivetrain.generateAutoCommand(autoCommand);
  }

}
