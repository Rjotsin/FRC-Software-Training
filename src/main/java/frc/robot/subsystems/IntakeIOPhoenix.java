package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ConnectedMotorValue;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.util.PhoenixUtil;

public class IntakeIOPhoenix implements IntakeIO {
    private TalonFX leftMotor;
    private TalonFX rightMotor;
    private DigitalInput objectSensor;
    private VoltageOut leftMotorVoltageRequest;
    private VoltageOut rightMotorVoltageRequest;
    private TorqueCurrentFOC leftMotorTorqueRequest;
    private TorqueCurrentFOC rightMotorTorqueRequest;
    private TalonFXConfigurator leftMotorConfigurator;
    private TalonFXConfigurator rightMotorConfigurator;
    private StatusSignal<Voltage> leftMotorVoltageSignal;
    private StatusSignal<Voltage> rightMotorVoltageSignal;
    private StatusSignal<Current> leftMotorStatorCurrentSignal;
    private StatusSignal<Current> rightMotorStatorCurrentSignal;
    private StatusSignal<Current> leftMotorSupplyCurrentSignal;
    private StatusSignal<Current> rightMotorSupplyCurrentSignal;
    private StatusSignal<Temperature> leftMotorTemperatureSignal;
    private StatusSignal<Temperature> rightMotorTemperatureSignal;
    private StatusSignal<ConnectedMotorValue> leftMotorConnectedSignal;
    private StatusSignal<ConnectedMotorValue> rightMotorConnectedSignal;

    private Alert configAlert = new Alert("Config for the intake motor could not be applied", AlertType.kError);

    public IntakeIOPhoenix(int leftMotorId, int rightMotorId, int objectSensorPort) {
        leftMotor = new TalonFX(leftMotorId);
        rightMotor = new TalonFX(leftMotorId);
        objectSensor = new DigitalInput(objectSensorPort);
        leftMotorVoltageRequest = new VoltageOut(0);
        rightMotorVoltageRequest = new VoltageOut(0);
        leftMotorTorqueRequest = new TorqueCurrentFOC(0);
        rightMotorTorqueRequest = new TorqueCurrentFOC(0);
        leftMotorConfigurator = leftMotor.getConfigurator();
        rightMotorConfigurator = rightMotor.getConfigurator();
        leftMotorVoltageSignal = leftMotor.getMotorVoltage();
        rightMotorVoltageSignal = rightMotor.getMotorVoltage();
        leftMotorStatorCurrentSignal = leftMotor.getStatorCurrent();
        rightMotorStatorCurrentSignal = rightMotor.getStatorCurrent();
        leftMotorSupplyCurrentSignal = leftMotor.getSupplyCurrent();
        rightMotorSupplyCurrentSignal = rightMotor.getSupplyCurrent();
        leftMotorTemperatureSignal = leftMotor.getDeviceTemp();
        rightMotorTemperatureSignal = rightMotor.getDeviceTemp();
        leftMotorConnectedSignal = leftMotor.getConnectedMotor();
        rightMotorConnectedSignal = rightMotor.getConnectedMotor();
    }

    public void applyInitConfigs() {
        if (!PhoenixUtil.applyAndCheckConfiguration(
                leftMotor, IntakeConstants.INTAKE_LEFT_INIT_CONFIGS)) {
            configAlert.set(true);
        }
        if (!PhoenixUtil.applyAndCheckConfiguration(
                rightMotor, IntakeConstants.INTAKE_RIGHT_INIT_CONFIGS)) {
            configAlert.set(true);
        }
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs) {
        BaseStatusSignal.refreshAll(leftMotorVoltageSignal, rightMotorVoltageSignal, leftMotorStatorCurrentSignal,
                rightMotorStatorCurrentSignal, leftMotorSupplyCurrentSignal, rightMotorSupplyCurrentSignal,
                leftMotorTemperatureSignal, rightMotorTemperatureSignal, leftMotorConnectedSignal,
                rightMotorConnectedSignal);
        inputs.leftMotorVoltage = leftMotorVoltageSignal.getValue().in(Volts);
        inputs.rightMotorVoltage = rightMotorVoltageSignal.getValue().in(Volts);
        inputs.leftMotorStatorCurrentAmps = leftMotorStatorCurrentSignal.getValue().in(Amps);
        inputs.rightMotorStatorCurrentAmps = rightMotorStatorCurrentSignal.getValue().in(Amps);
        inputs.leftMotorSupplyCurrentAmps = leftMotorSupplyCurrentSignal.getValue().in(Amps);
        inputs.rightMotorSupplyCurrentAmps = rightMotorSupplyCurrentSignal.getValue().in(Amps);
        inputs.leftMotorTemperatureCelsius = leftMotorTemperatureSignal.getValue().in(Celsius);
        inputs.rightMotorTemperatureCelsius = rightMotorTemperatureSignal.getValue().in(Celsius);
        inputs.leftMotorConnected = leftMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
        inputs.rightMotorConnected = rightMotorConnectedSignal.getValue() == ConnectedMotorValue.KrakenX60_Integrated;
        
    }

    @Override
    public void setLeftVoltage(Voltage volts) {
        leftMotor.setControl(leftMotorVoltageRequest.withOutput(volts));
    }

    @Override
    public void setRightVoltage(Voltage volts) {
        rightMotor.setControl(rightMotorVoltageRequest.withOutput(volts));
    }

    @Override
    public void setLeftTorque(Current amps) {
        leftMotor.setControl(leftMotorTorqueRequest.withOutput(amps));
    }

    @Override
    public void setRightTorque(Current amps) {
        rightMotor.setControl(rightMotorTorqueRequest.withOutput(amps));
    }

    @Override
    public boolean isObjectDetected() {
        return !objectSensor.get();
    }

    @Override
    public Temperature getLeftMotorTemp() {
        return leftMotor.getDeviceTemp().getValue();
    }

    @Override
    public Temperature getRightMotorTemp() {
        return rightMotor.getDeviceTemp().getValue();
    }
}