package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ConnectedMotorValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.util.PhoenixUtil;

public class BintakeIOPhoenix implements BintakeIO {
    private TalonFX intakeMotor;
    private TalonFX pivotMotor;
    private DigitalInput objectSensor;
    private VoltageOut intakeMotorVoltageRequest;
    private VoltageOut pivotMotorVoltageRequest;
    private TalonFXConfigurator intakeMotorConfigurator;
    private TalonFXConfigurator pivotMotorConfigurator;
    private StatusSignal<Voltage> intakeMotorVoltageSignal;
    private StatusSignal<Voltage> pivotMotorVoltageSignal;
    private StatusSignal<Current> intakeMotorStatorCurrentSignal;
    private StatusSignal<Current> pivotMotorStatorCurrentSignal;
    private StatusSignal<Current> intakeMotorSupplyCurrentSignal;
    private StatusSignal<Current> pivotMotorSupplyCurrentSignal;
    private StatusSignal<Temperature> intakeMotorTemperatureSignal;
    private StatusSignal<Temperature> pivotMotorTemperatureSignal;
    private StatusSignal<ConnectedMotorValue> intakeMotorConnectedSignal;
    private StatusSignal<ConnectedMotorValue> pivotMotorConnectedSignal;
    private StatusSignal<Angle> positionSignal;
    private StatusSignal<Double> targetSignal;
    private StatusSignal<Double> positionErrorSignal;
    private StatusSignal<AngularVelocity> velocitySignal;
    private MotionMagicVoltage motionMagicVoltageRequest;

    private Alert configAlert = new Alert("Config for the intake motor could not be applied", AlertType.kError);

    public BintakeIOPhoenix(int intakeMotorId, int pivotMotorId, int objectSensorPort) {
        intakeMotor = new TalonFX(intakeMotorId);
        pivotMotor = new TalonFX(pivotMotorId);
        objectSensor = new DigitalInput(objectSensorPort);
        intakeMotorVoltageRequest = new VoltageOut(0);
        pivotMotorVoltageRequest = new VoltageOut(0);
        // intakeMotorTorqueRequest = new TorqueCurrentFOC(0);
        // pivotMotorTorqueRequest = new TorqueCurrentFOC(0);
        intakeMotorConfigurator = intakeMotor.getConfigurator();
        pivotMotorConfigurator = pivotMotor.getConfigurator();
        intakeMotorVoltageSignal = intakeMotor.getMotorVoltage();
        pivotMotorVoltageSignal = pivotMotor.getMotorVoltage();
        intakeMotorStatorCurrentSignal = intakeMotor.getStatorCurrent();
        pivotMotorStatorCurrentSignal = pivotMotor.getStatorCurrent();
        intakeMotorSupplyCurrentSignal = intakeMotor.getSupplyCurrent();
        pivotMotorSupplyCurrentSignal = pivotMotor.getSupplyCurrent();
        intakeMotorTemperatureSignal = intakeMotor.getDeviceTemp();
        pivotMotorTemperatureSignal = pivotMotor.getDeviceTemp();
        intakeMotorConnectedSignal = intakeMotor.getConnectedMotor();
        pivotMotorConnectedSignal = pivotMotor.getConnectedMotor();
        positionSignal = pivotMotor.getPosition();
        targetSignal = pivotMotor.getClosedLoopReference();
        positionErrorSignal = pivotMotor.getClosedLoopError();
        velocitySignal = pivotMotor.getVelocity();
        motionMagicVoltageRequest = new MotionMagicVoltage(Degrees.zero());
        
    }

    BaseStatusSignal[] statusSignals = {
        intakeMotorVoltageSignal,
        pivotMotorVoltageSignal,
        intakeMotorStatorCurrentSignal,
        pivotMotorStatorCurrentSignal,
        intakeMotorSupplyCurrentSignal,
        pivotMotorSupplyCurrentSignal,
        intakeMotorTemperatureSignal,
        pivotMotorTemperatureSignal,
        intakeMotorConnectedSignal,
        pivotMotorConnectedSignal,
        positionSignal,
        targetSignal,
        positionErrorSignal,
        velocitySignal};

    public void applyInitConfigs() {
        if (!PhoenixUtil.applyAndCheckConfiguration(
                intakeMotor, BintakeConstants.INTAKE_CONFIGS_TALONFX)) {
            configAlert.set(true);
        }
        if (!PhoenixUtil.applyAndCheckConfiguration(
                pivotMotor, BintakeConstants.PIVOT_CONFIGS_TALONFX)) {
            configAlert.set(true);
        }
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        BaseStatusSignal.refreshAll(statusSignals);

        
        inputs.intakeMotorVoltage = getIntakeVoltage().in(Volts);
        inputs.pivotMotorVoltage = getPivotVoltage().in(Volts);
        inputs.intakeMotorStatorCurrent = getIntakeStatorCurrent().in(Amps);
        inputs.pivotMotorStatorCurrent = getPivotStatorCurrent().in(Amps);
        inputs.intakeMotorSupplyCurrent = getIntakeSupplyCurrent().in(Amps);
        inputs.pivotMotorSupplyCurrent = getPivotSupplyCurrent().in(Amps);
        inputs.intakeMotorTemperature = getIntakeMotorTemp().in(Celsius);
        inputs.pivotMotorTemperature = getPivotMotorTemp().in(Celsius);
        inputs.intakeMotorConnected = isIntakeMotorConnected();
        inputs.pivotMotorConnected = isPivotMotorConnected();
        inputs.positionDegrees = getPosition().in(Degrees);
        inputs.targetDegrees = getTargetAngle();
        inputs.errorDegrees = getErrorDegrees();
        inputs.pivotVelocity = getPivotVelocity().in(DegreesPerSecond);
        inputs.gameObjectDetected = isObjectDetected();
        inputs.pivotStalling = isPivotStalling();
    }

    @Override
    public void setIntakeVoltage(Voltage volts) {
        intakeMotor.setControl(intakeMotorVoltageRequest.withOutput(volts));
    }

    @Override
    public void setPivotVoltage(Voltage volts) {
        pivotMotor.setControl(pivotMotorVoltageRequest.withOutput(volts));
    }

    @Override
    public void setSensorPosition(Angle target) {
    }

    private Temperature getIntakeMotorTemp() {
        return intakeMotorTemperatureSignal.getValue();
    }

    private Temperature getPivotMotorTemp() {
        return pivotMotorTemperatureSignal.getValue();
    }

    private boolean isIntakeMotorConnected() {
        return intakeMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
    }

    private boolean isPivotMotorConnected() {
        return pivotMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
    }

    private Voltage getIntakeVoltage() {
        return intakeMotorVoltageSignal.getValue();
    }

    private Voltage getPivotVoltage() {
        return pivotMotorVoltageSignal.getValue();
    }

    private Current getIntakeStatorCurrent() {
        return intakeMotorStatorCurrentSignal.getValue();
    }

    private Current getIntakeSupplyCurrent() {
        return intakeMotorSupplyCurrentSignal.getValue();
    }

    private Current getPivotStatorCurrent() {
        return pivotMotorStatorCurrentSignal.getValue();
    }

    private Current getPivotSupplyCurrent() {
        return pivotMotorSupplyCurrentSignal.getValue();
    }

    private Angle getPosition() {
        return positionSignal.getValue();
    }

    private Double getTargetAngle() {
        return targetSignal.getValue();
    }

    private Double getErrorDegrees() {
        return positionErrorSignal.getValue();
    }

    private AngularVelocity getPivotVelocity() {
        return velocitySignal.getValue();
    }

    @Override
    public boolean isObjectDetected() {
        return !objectSensor.get();
    }

    @Override
    public boolean isPivotStalling() {
        return getPivotStatorCurrent().in(Amps) >= BintakeConstants.HOMING_STATOR_CURRENT_LIMIT.in(Amps);
    }

}