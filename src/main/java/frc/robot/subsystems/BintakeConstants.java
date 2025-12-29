package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.MomentOfInertia;
import edu.wpi.first.units.measure.Voltage;

public class BintakeConstants {

	public static final Voltage INTAKE_VOLTAGE = Volts.zero();
	public static final Voltage HOLD_VOLTAGE = Volts.zero();

	public static final MotionMagicConfigs INTAKE_CONFIGS_MOTION_MAGIC = new MotionMagicConfigs();
    public static final TalonFXConfiguration INTAKE_CONFIGS_TALONFX = new TalonFXConfiguration();
    public static final Slot0Configs INTAKE_CONFIGS_SLOT0 = new Slot0Configs();

	// Mechanical Constants
	public static final double PIVOT_GEAR_RATIO = 1.0;
	public static final MomentOfInertia MOI = KilogramSquareMeters.one();
    public static final DCMotor SIM_MOTORS = DCMotor.getKrakenX60(2);

    // Positional Constants
    public static final Angle ANGLE_TOLERANCE_DEFAULT = Degrees.of(1.5);

	// Physical Constants
    public static final Angle ANGLE_SUBSYSTEM_MIN = Degrees.zero();
    public static final Angle ANGLE_SENSOR_MIN = Degrees.zero();
    public static final Angle ANGLE_SIM_MIN = Degrees.zero();
    public static final Angle ANGLE_SIM_STARTING = Degrees.zero();
    public static final Angle ANGLE_SUBSYSTEM_MAX = Degrees.zero();
    public static final Angle ANGLE_SENSOR_MAX = Degrees.zero();
    public static final Angle ANGLE_SIM_MAX = Degrees.zero();

	public static final Distance LENGTH_INTAKE_SIM = Meters.one();

	public static final MotionMagicConfigs PIVOT_CONFIGS_MOTION_MAGIC = new MotionMagicConfigs();
    public static final TalonFXConfiguration PIVOT_CONFIGS_TALONFX = new TalonFXConfiguration();
    public static final Slot0Configs PIVOT_CONFIGS_SLOT0 = new Slot0Configs();

    public static final Current HOMING_STATOR_CURRENT_LIMIT = Amps.of(50);
}
