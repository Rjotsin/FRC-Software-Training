package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConstants {

	public static final TalonFXConfiguration INTAKE_LEFT_INIT_CONFIGS = new TalonFXConfiguration()
			.withMotorOutput(
					new MotorOutputConfigs()
							.withInverted(InvertedValue.Clockwise_Positive)
							.withNeutralMode(NeutralModeValue.Brake))
			.withCurrentLimits(
					new CurrentLimitsConfigs()
							.withSupplyCurrentLimit(Amps.of(8))
							.withSupplyCurrentLimitEnable(true));
	public static final TalonFXConfiguration INTAKE_RIGHT_INIT_CONFIGS = new TalonFXConfiguration()
			.withMotorOutput(
					new MotorOutputConfigs()
							.withInverted(InvertedValue.CounterClockwise_Positive)
							.withNeutralMode(NeutralModeValue.Brake))
			.withCurrentLimits(
					new CurrentLimitsConfigs()
							.withSupplyCurrentLimit(Amps.of(8))
							.withSupplyCurrentLimitEnable(true));

}
