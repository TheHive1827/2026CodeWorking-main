package frc.robot.subsystems;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import frc.robot.Configs;
import frc.robot.Constants;
public class ShooterSubsystem extends SubsystemBase{
    private final SparkMax m_Shooter = new SparkMax(ShooterConstants.ShooterCANID, MotorType.kBrushless);

    private final double WHEELRADIUS = 0.04;
    private final double GRAVITY = -9.81;
    private final double SHOOTERHEIGHT = 0.45;
    private final double HOPPERHEIGHT = 3;
    private  double shooterDistance = 0;
    private final double SHOOTERANGLE = Math.toRadians(75);
    private final double BALLMASS = 0.4;
    private final double TORQUESLOPE = -2.55 / 5676;
    private final double TORQUEOFFSET = 2.6;
    private final double CONTACTLENGTH = 0.3;
    private final double TIMECONSTANT = 0.1;
    private final double BALLWEIGHT = BALLMASS * GRAVITY;

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
    
  public void periodic(){
    shooterDistance = SmartDashboard.getNumber("Shooter Distance", 0);
    double shooterSpeed = calculateShooterSpeed(shooterDistance);
    m_elevatorPID.setSetpoint(shooterSpeed, ControlType.kVelocity);
  }

  public double calculateShooterSpeed(double distance) {
    // Calculate the required initial velocity using projectile motion equations
    double velocity = distance * Math.sqrt(GRAVITY/2) * (-HOPPERHEIGHT + SHOOTERHEIGHT + (distance / Math.tan(SHOOTERANGLE)));
    velocity = velocity / Math.sin(SHOOTERANGLE);
    // double torque = (TORQUESLOPE * velocity) + TORQUEOFFSET;
    double rpm = (((((velocity * BALLMASS) / TIMECONSTANT) + BALLWEIGHT) * WHEELRADIUS) - TORQUEOFFSET) / TORQUESLOPE;
    return rpm;
  }
}