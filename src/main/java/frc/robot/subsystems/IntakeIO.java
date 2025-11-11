package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Celsius;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {

    @AutoLog
    class IntakeIOInputs {
        public double leftMotorVoltage = 0.0;
        public double rightMotorVoltage = 0.0;
        public double leftMotorStatorCurrentAmps = 0.0;
        public double rightMotorStatorCurrentAmps = 0.0;
        public boolean gameObjectDetected = false;
        public double leftMotorSupplyCurrentAmps = 0.0;
        public double rightMotorSupplyCurrentAmps = 0.0;
        public boolean leftMotorConnected = false;
        public boolean rightMotorConnected = false;
        public double leftMotorTemperatureCelsius = 0.0;
        public double rightMotorTemperatureCelsius = 0.0;
    }

    default void updateInputs(IntakeIOInputs inputs) {
    }

    default void setLeftVoltage(Voltage volts) {
    }

    default void setRightVoltage(Voltage volts) {
    }

    default void setVoltages(Voltage leftVolts, Voltage rightVolts) {
        this.setLeftVoltage(leftVolts);
        this.setRightVoltage(rightVolts);
    }

    default void setLeftTorque(Current amps) {
    }

    default void setRightTorque(Current amps) {
    }

    default boolean isObjectDetected() {
        return false;
    }

    default Temperature getLeftMotorTemp() {
        return Celsius.of(0.0);
    }

    default Temperature getRightMotorTemp() {
        return Celsius.of(0.0);
    }

}
