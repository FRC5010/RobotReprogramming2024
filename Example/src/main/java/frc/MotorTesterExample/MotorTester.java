// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.MotorTesterExample;

import org.frc5010.common.arch.GenericRobot;
import org.frc5010.common.sensors.Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/** Add your docs here. */
public class MotorTester extends GenericRobot {

    BasicKrakenRunner krakenRunner;

    public MotorTester(String directory) {
        super(directory);
        krakenRunner = new BasicKrakenRunner(mechVisual);
    }

    @Override
    public void configureButtonBindings(Controller driver, Controller operator) {
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
