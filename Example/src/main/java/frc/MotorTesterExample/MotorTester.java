// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.MotorTesterExample;

import org.frc5010.common.arch.GenericRobot;
import org.frc5010.common.motors.MotorFactory;
import org.frc5010.common.motors.function.PercentControlMotor;
import org.frc5010.common.motors.function.VelocityControlMotor;
import org.frc5010.common.sensors.Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/** Add your docs here. */
public class MotorTester extends GenericRobot {

    BasicKrakenRunner krakenRunner;
    VelocityRunner velocityRunner;

    public MotorTester(String directory) {
        super(directory);
        PercentControlMotor motor1 = new PercentControlMotor(MotorFactory.KrakenX60(3));
        krakenRunner = new BasicKrakenRunner(mechVisual, motor1);
        
        velocityRunner = new VelocityRunner(new VelocityControlMotor(MotorFactory.NEO(2)), mechVisual);
    }

    @Override
    public void configureButtonBindings(Controller driver, Controller operator) {
        driver.createAButton().whileTrue(Commands.runOnce(() -> velocityRunner.setReferenceSpeed(2000), velocityRunner))
        .onFalse(Commands.runOnce(() -> velocityRunner.setReferenceSpeed(0.0), velocityRunner));
    }

    @Override
    public void setupDefaultCommands(Controller driver, Controller operator) {
        krakenRunner.setDefaultCommand(Commands.run(() -> krakenRunner.runMotor(driver.getLeftYAxis()), krakenRunner));
    }

    @Override
    public void initAutoCommands() {
    }

    @Override
    public Command generateAutoCommand(Command autoCommand) {
        return autoCommand;
    }
}
