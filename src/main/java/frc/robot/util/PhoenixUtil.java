// Copyright 2021-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.util;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DriverStation;
import java.util.function.Supplier;

public class PhoenixUtil {
  /** Attempts to run the command until no error is produced. */
  public static void tryUntilOk(int maxAttempts, Supplier<StatusCode> command) {
    for (int i = 0; i < maxAttempts; i++) {
      var error = command.get();
      if (error.isOK()) break;
    }
  }

  public static boolean checkError(Supplier<StatusCode> function) {
    StatusCode code = function.get();

    if (code != StatusCode.OK) {
      return false;
    }

    DriverStation.reportWarning("CTRE Device Config " + code.getName() + " was successful", false);
    return true;
  }

  public static boolean applyAndCheckConfiguration(
      TalonFX talon, TalonFXConfiguration config, int numTries) {
    boolean appliedConfigs;

    for (int x = 0; x < numTries; x++) {
      DriverStation.reportWarning(
          "Trying CTRE Device Config for Talon "
              + talon.getDeviceID()
              + " ,attempt number "
              + (x + 1),
          false);
      appliedConfigs = checkError(() -> talon.getConfigurator().apply(config));
      if (appliedConfigs) {
        return true;
      }
    }

    DriverStation.reportWarning("Error sending configuration", false);
    return false;
  }

  public static boolean applyAndCheckConfiguration(TalonFX talon, TalonFXConfiguration config) {
    return applyAndCheckConfiguration(talon, config, 3);
  }
}
