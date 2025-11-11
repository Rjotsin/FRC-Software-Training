// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private IntakeIO io;
  private IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

  /** Creates a new Intake. */
  public Intake(IntakeIO io) {
    super("Intake");
    this.io = io;
  }

  public Command intake(double percentPower) {
    return new Command() {

    };
  }

  public Command outtake(double percentPower) {
    return new Command() {

    };
  }

  public Command waitForGameObject() {
    return new Command() {

    };
  }

  public boolean gameObjectDetected() {
    return false;
  }

  public void intializeEnabled() {
    io.setVoltages(Volts.of(0), Volts.of(0));
  }

  public void intializeDisabled() {
    io.setVoltages(Volts.of(0), Volts.of(0));
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Intake", inputs);
  }
}
