// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericRobot;
import org.frc5010.common.config.ConfigConstants;
import org.frc5010.common.config.json.DrivetrainPropertiesJson;
import org.frc5010.common.constants.SwerveConstants;
import org.frc5010.common.drive.GenericDrivetrain;
import org.frc5010.common.motors.MotorFactory;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.sensors.Controller;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
// Reference Document Link: https://docs.google.com/document/d/16YqkkYDurYPHd9RxBJdwxWiMCAomJKIatphhviYabxw/edit?usp=sharing
/** This is an example robot class. */
public class ReprogramRobot extends GenericRobot {
  SwerveConstants swerveConstants;
  GenericDrivetrain drivetrain;
  Intake intakeSubsystem;
  DisplayValueSubsystem displayValueSubsystem = new DisplayValueSubsystem();
  PercentControlMotor frontIntakeMotor, backIntakeMotor;

  


  public ReprogramRobot(String directory) {
    super(directory);
    PercentControlMotor frontIntakeMotor = new PercentControlMotor(MotorFactory.KrakenX60(5)); // Needs motor id
    PercentControlMotor backtIntakeMotor = new PercentControlMotor(MotorFactory.KrakenX60(1)); // Needs motor id
    drivetrain = (GenericDrivetrain) getSubsystem(ConfigConstants.DRIVETRAIN);
    intakeSubsystem = new Intake(frontIntakeMotor, backtIntakeMotor, mechVisual);
  }


  @Override
  public void configureButtonBindings(Controller driver, Controller operator) {
  }

  @Override
  public void setupDefaultCommands(Controller driver, Controller operator) {
    drivetrain.setDefaultCommand(drivetrain.createDefaultCommand(driver));
    intakeSubsystem.setDefaultCommand(Commands.run(() -> intakeSubsystem.setIntakeSpeed(driver.getLeftYAxis()), intakeSubsystem));
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
