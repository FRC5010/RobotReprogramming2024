// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.frc5010.common.arch.GenericRobot;
import org.frc5010.common.config.ConfigConstants;
import org.frc5010.common.constants.SwerveConstants;
import org.frc5010.common.drive.GenericDrivetrain;
import org.frc5010.common.motors.MotorFactory;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.sensors.Controller;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.BasicRunClimb;

// Reference Document Link: https://docs.google.com/document/d/16YqkkYDurYPHd9RxBJdwxWiMCAomJKIatphhviYabxw/edit?usp=sharing
/** This is an example robot class. */
public class ReprogramRobot extends GenericRobot {
  SwerveConstants swerveConstants;
  GenericDrivetrain drivetrain;
  FeederSubsystem feeder;
  Shooter shooterSubsystem;
  PercentControlMotor topShooterMotor;
  PercentControlMotor bottomShooterMotor;

  private RobotClimb climbSubsystem;
	private PercentControlMotor climbMotorLeft;
	private PercentControlMotor climbMotorRight;

  public ReprogramRobot(String directory) {
    super(directory);
    climbMotorLeft = new PercentControlMotor(MotorFactory.NEO(7).invert(true));
    climbMotorRight = new PercentControlMotor(MotorFactory.NEO(8));
    climbSubsystem = new RobotClimb(mechVisual, climbMotorLeft, climbMotorRight);
    topShooterMotor = new PercentControlMotor(MotorFactory.KrakenX60(0));
    bottomShooterMotor = new PercentControlMotor(MotorFactory.KrakenX60(0));

    drivetrain = (GenericDrivetrain) getSubsystem(ConfigConstants.DRIVETRAIN);
    feeder = new FeederSubsystem(mechVisual);
    shooterSubsystem = new Shooter(bottomShooterMotor, topShooterMotor, mechVisual);
  }

  @Override
  public void configureButtonBindings(Controller driver, Controller operator) {
    driver.createAButton().onTrue(feeder.acceptNote());
    driver.createBButton().onTrue(feeder.loadNote());
    driver.setRightTrigger(driver.createRightTrigger().deadzone(0.5).cubed());
    (new Trigger(() -> driver.getRightTrigger() > 0))
        .whileTrue(Commands.runOnce(() -> shooterSubsystem.setShooterSpeed(driver.getRightTrigger())))
        .onFalse(Commands.runOnce(() -> shooterSubsystem.setShooterSpeed(0)));
  }


  @Override
  public void setupDefaultCommands(Controller driver, Controller operator) {
    drivetrain.setDefaultCommand(drivetrain.createDefaultCommand(driver));
    climbSubsystem.setDefaultCommand(new BasicRunClimb(climbSubsystem, ()-> operator.getRightYAxis(), ()-> operator.getLeftYAxis())); 


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
