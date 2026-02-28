package frc.robot.subsystems;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import frc.robot.Configs;
import frc.robot.Constants;
public class ShooterSubsystem extends SubsystemBase{
    private final SparkMax m_Shooter = new SparkMax(ShooterConstants.ShooterCANID, MotorType.kBrushless);

    public SparkClosedLoopController m_elevatorPID = m_Shooter.getClosedLoopController();
  RelativeEncoder encoder;
  public static final SparkMaxConfig motorConfig = new SparkMaxConfig();

  public void config() {
    m_elevatorPID = m_Shooter.getClosedLoopController();

    motorConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        // Set PID values for position control. We don't need to pass a closed loop
        // slot, as it will default to slot 0.
        .p(1.0)
        // speed
        .i(0.0)
        // integral
        .d(0.0)
        // kinda like friction
        .iZone(0)
        .outputRange(-0.5, 0.5);
            // m_Shooter.setP(1);

    m_Shooter.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    // we'll need to add FeedForward later

// can't wait to watch jjk season 3 fr    
  }
    
}