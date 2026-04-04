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
    private final SparkMax m_Shooter = new SparkMax(ShooterConstants.ShooterWheelCANID, MotorType.kBrushless);
    private final SparkMax m_Conveyor = new SparkMax(ShooterConstants.ConveyorCanID, MotorType.kBrushless);
        public final SparkMax m_Vector = new SparkMax(ShooterConstants.VectorIndexCANID, MotorType.kBrushless);

    private final double WHEELRADIUS = 0.04;
    private final double GRAVITY = -9.81;
    private final double SHOOTERHEIGHT = 0.45;
    private final double HOPPERHEIGHT = 3;
    public double shooterDistance = 5;
    private final double SHOOTERANGLE = Math.toRadians(75);
    private final double BALLMASS = 0.4;
    private final double TORQUESLOPE = -2.55 / 5676;
    private final double TORQUEOFFSET = 2.6;
    private final double CONTACTLENGTH = 0.3;
    private final double TIMECONSTANT = 0.1;
    private final double BALLWEIGHT = BALLMASS * GRAVITY;
    private double velocity = 0;
    public SparkClosedLoopController m_ShooterPID = m_Shooter.getClosedLoopController();
  RelativeEncoder encoder;
  public static final SparkMaxConfig motorConfig = new SparkMaxConfig();

  public void config() {
    m_ShooterPID = m_Shooter.getClosedLoopController();

    motorConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        // Set PID values for position control. We don't need to pass a closed loop
        // slot, as it will default to slot 0.
        .p(1.5)
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
    SmartDashboard.putNumber("shooter rpm", m_Shooter.getEncoder().getVelocity());
    // double shooterSpeed = calculateShooterSpeed(1.5);
    // m_elevatorPID.setSetpoint(shooterSpeed, ControlType.kVelocity);
    // SmartDashboard.putNumber("Shooter Speed", m_ShooterPID.getSetpoint());
    // SmartDashboard.putNumber("Shooter Distance", shooterDistance);
    // SmartDashboard.putString("Shooter PID Controller", m_ShooterPID.toString());
  }

  public void conveyorrun(double speed){
    m_Conveyor.set(-speed);
    
  }

  public void vectorrun(double speed){
    m_Vector.set(-speed/4);
    
  }

  // public void shooter(){
  //   m_Shooter.set();
  // }

  public void multiple_run(double speed){
    m_Vector.set(-speed/4);
    m_Conveyor.set(-speed/4);

  }

  public void shoot(double speed){
    // m_Shooter.setVoltage(12);
    // double shooterSpeed = calculateShooterSpeed(5);
    // m_ShooterPID.setSetpoint(shooterSpeed, ControlType.kVelocity);
    m_Shooter.set(speed);
  }

  public void stop(){
    // double shooterSpeed = calculateShooterSpeed(1.5);
  //  m_Shooter.setVoltage(0);
   m_Shooter.disable();
    // m_elevatorPID.setSetpoint(0, ControlType.kVelocity);
  }

  public double calculateShooterSpeed(double distance) {
    // Calculate the required initial velocity using projectile motion equations
    velocity = distance * 2.214;
    velocity *= (-HOPPERHEIGHT + SHOOTERHEIGHT + (distance / Math.tan(SHOOTERANGLE)));
    velocity = velocity / Math.sin(SHOOTERANGLE);
    velocity = Math.sin(SHOOTERANGLE);
    // double torque = (TORQUESLOPE * velocity) + TORQUEOFFSET;
    double rpm = (((((velocity * BALLMASS) / TIMECONSTANT) + BALLWEIGHT) * WHEELRADIUS) - TORQUEOFFSET) / TORQUESLOPE;
    return rpm;}
}