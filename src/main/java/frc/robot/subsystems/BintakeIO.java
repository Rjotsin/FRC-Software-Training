package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
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
        boolean pivotStalling = false;
        String currentCommandName = "";
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

    default void setIntakeTorque(Current amps) {
    }

    default void setPivotTorque(Current amps) {
    }

    default void setTorque(Current intakeAmps, Current pivotAmps) {
        this.setIntakeTorque(intakeAmps);
        this.setPivotTorque(pivotAmps);
    }

    default boolean isObjectDetected() {
        return false;
    }

    default Temperature getIntakeMotorTemp() {
        return Celsius.zero();
    }

    default Temperature getPivotMotorTemp() {
        return Celsius.zero();
    }

    default void setSensorPosition(Angle target) {
    }

    default boolean isIntakeMotorConnected() {
        return false;
    }

    default boolean isPivotMotorConnected() {
        return false;
    }

    default Angle getPosition() {
        return Degrees.zero();
    }

    default Voltage getVoltage() {
        return Volts.zero();
    }

    default Angle getTargetAngle() {
        return Degrees.zero();
    }

    default boolean isPivotStalling() {
        return false;
    }

    default Angle getErrorDegrees() {
        return Degrees.zero();
    }

    default AngularVelocity getPivotVelocity() {
        return DegreesPerSecond.zero();
    }

    default Current getStatorCurrent() {
        return Amps.zero();
    }

    default Current getSupplyCurrent() {
        return Amps.zero();
    }

    default Current getTargetCurrent() {
        return Amps.zero();
    }

    default String getCurrentCommandName() {
        return "";
    }
}
