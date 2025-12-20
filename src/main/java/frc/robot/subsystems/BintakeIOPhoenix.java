package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.TorqueCurrentFOC;
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
    private TorqueCurrentFOC intakeMotorTorqueRequest;
    private TorqueCurrentFOC pivotMotorTorqueRequest;
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
        intakeMotorTorqueRequest = new TorqueCurrentFOC(0);
        pivotMotorTorqueRequest = new TorqueCurrentFOC(0);
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
                intakeMotor, BintakeConstants.BINTAKE_INTAKE_INIT_CONFIGS)) {
            configAlert.set(true);
        }
        if (!PhoenixUtil.applyAndCheckConfiguration(
                pivotMotor, BintakeConstants.BINTAKE_PIVOT_INIT_CONFIGS)) {
            configAlert.set(true);
        }
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        BaseStatusSignal.refreshAll(statusSignals);
        // inputs.intakeMotorVoltage = intakeMotorVoltageSignal.getValue().in(Volts);
        // inputs.pivotMotorVoltage = pivotMotorVoltageSignal.getValue().in(Volts);
        // inputs.intakeMotorStatorCurrent = intakeMotorStatorCurrentSignal.getValue().in(Amps);
        // inputs.pivotMotorStatorCurrent = pivotMotorStatorCurrentSignal.getValue().in(Amps);
        // inputs.intakeMotorSupplyCurrent = intakeMotorSupplyCurrentSignal.getValue().in(Amps);
        // inputs.pivotMotorSupplyCurrent = pivotMotorSupplyCurrentSignal.getValue().in(Amps);
        // inputs.intakeMotorTemperature = intakeMotorTemperatureSignal.getValue().in(Celsius);
        // inputs.pivotMotorTemperature = pivotMotorTemperatureSignal.getValue().in(Celsius);
        // inputs.intakeMotorConnected = intakeMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
        // inputs.pivotMotorConnected = pivotMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
        
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
    public void setIntakeTorque(Current amps) {
        intakeMotor.setControl(intakeMotorTorqueRequest.withOutput(amps));
    }

    @Override
    public void setPivotTorque(Current amps) {
        pivotMotor.setControl(pivotMotorTorqueRequest.withOutput(amps));
    }

    @Override
    public boolean isObjectDetected() {
        return !objectSensor.get();
    }

    @Override
    public Temperature getIntakeMotorTemp() {
        return intakeMotor.getDeviceTemp().getValue();
    }

    @Override
    public Temperature getPivotMotorTemp() {
        return pivotMotor.getDeviceTemp().getValue();
    }

    @Override
    public void setSensorPosition(Angle target) {
    }

    @Override
    public boolean isIntakeMotorConnected() {
        return intakeMotor.getConnectedMotor() != null;
    }

    @Override
    public boolean isPivotMotorConnected() {
        return pivotMotor.getConnectedMotor() != null;
    }

    @Override
    public Angle getPosition() {
        return pivotMotor.getPosition().getValue();
    }

    // @Override
    // public Voltage getVoltage() {
    //     return pivotMotor.getMotorVoltage().getValue();
    // }

    // @Override
    // public Angle getTargetAngle() {
    //     return Degrees.zero();
    // }

    // @Override
    // public boolean isPivotStalling() {
    //     return false;
    // }

    // @Override
    // public Angle getErrorDegrees() {
    //     return Degrees.zero();
    // }

    @Override
    public AngularVelocity getPivotVelocity() {
        return pivotMotor.getVelocity().getValue();
    }

    // @Override
    // public Current getStatorCurrent() {
    //     return Amps.zero();
    // }

    // @Override
    // public Current getSupplyCurrent() {
    //     return Amps.zero();
    // }

    // @Override
    // public Current getTargetCurrent() {
    //     return Amps.zero();
    // }

    // @Override
    // public String getCurrentCommandName() {
    //     return "";
    // }


}