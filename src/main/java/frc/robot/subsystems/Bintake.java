// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.littletonrobotics.junction.Logger;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Bintake extends SubsystemBase {
  private BintakeIO io;
  private BintakeIOInputsAutoLogged bintakeInputs = new BintakeIOInputsAutoLogged();

  /** Creates a new Intake. */
  public Bintake(BintakeIO io) {
    super("Bintake");
    this.io = io;
  }

  public Command intake(double percentPower) {
    return new InstantCommand() {
    };
  }

  public Command outtake(double percentPower) {
    return new InstantCommand() {
    };
  }

  public Command passthrough(double percentPower) {
    return new InstantCommand() {
    };
  }

  public Command waitForGameObject() {
    return new InstantCommand() {
    };
  }

  public boolean gameObjectDetected() {
    return false;
  }

  public void intializeEnabled() {
    io.setVoltages(Volts.zero(), Volts.zero());
  }

  public void intializeDisabled() {
    io.setVoltages(Volts.zero(), Volts.zero());
  }

  public Command moveToAngle(Angle target) {
    return new InstantCommand() {
    };
  }

  public Angle getPositionAngle() {
    return Degrees.zero();
  }

  public Command runPivotVoltage(double percentPower) {
    return new Command() {
    };
  }

  public boolean atAngle(Angle target) {
    return false;
  }

  @Override
  public void periodic() {
    io.updateInputs(bintakeInputs);
    Logger.processInputs("Bintake", bintakeInputs);
  }

  public void setIntakeBrake() {
  }

  public void setIntakeCoast() {
  }

  public void setPivotBrake() {
  }

  public void setPivotCoast() {
  }
  
  public Command waitUntilAngleReached(Angle target) {
    return new InstantCommand();
  }

  public Command moveToAngleAndWait() {
    return new InstantCommand();
}

  public Voltage getIntakeVoltage() {
    return Volts.zero();
  }

  public Voltage getPivotVoltage() {
    return Volts.zero();
  }

  public Temperature getIntakeMotorTemp() {
      return Celsius.zero();
  }

  public Temperature getPivotMotorTemp() {
      return Celsius.zero();
  }
  
  public Angle getPosition() {
      return Degrees.zero();
  }

  public Angle getTargetAngle() {
      return Degrees.zero();
  }

  public Angle getErrorDegrees() {
      return Degrees.zero();
  }

  public AngularVelocity getPivotVelocity() {
      return DegreesPerSecond.zero();
  }
}
