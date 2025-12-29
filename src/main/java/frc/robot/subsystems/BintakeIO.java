package frc.robot.subsystems;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface BintakeIO {

    @AutoLog
    class IntakeIOInputs {
        double intakeMotorVoltage = 0.0;
        double pivotMotorVoltage = 0.0;
        double intakeMotorStatorCurrent = 0.0;
        double pivotMotorStatorCurrent = 0.0;
        boolean gameObjectDetected = false;
        double intakeMotorSupplyCurrent = 0.0;
        double pivotMotorSupplyCurrent = 0.0;
        boolean intakeMotorConnected = false;
        boolean pivotMotorConnected = false;
        double intakeMotorTemperature = 0.0;
        double pivotMotorTemperature = 0.0;
        double positionDegrees = 0.0;
        double targetDegrees = 0.0;
        double errorDegrees = 0.0;
        double pivotVelocity = 0.0;
        boolean pivotStalling = false;
        // String currentCommandName = "";
    }

    default void updateInputs(IntakeIOInputs inputs) {
    }

    default void setIntakeVoltage(Voltage volts) {
    }

    default void setPivotVoltage(Voltage volts) {
    }

    default void setVoltages(Voltage intakeVolts, Voltage pivotVolts) {
        this.setIntakeVoltage(intakeVolts);
        this.setPivotVoltage(pivotVolts);
    }

    default boolean isObjectDetected() {
        return false;
    }

    default void setSensorPosition(Angle target) {
    }

    default boolean isPivotStalling() {
        return false;
    }

}
