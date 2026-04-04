package frc.robot.subsystems;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
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
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Configs;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.OIConstants;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import java.math.*;
import java.lang.*;
import java.util.*;
// import java.String;

public class IntakePIDSubsystem extends SubsystemBase{
  // String kendall[];
    private final SparkMax IntakeControl = new SparkMax(IntakeConstants.IntakeControlCanID, MotorType.kBrushless);
    private final SparkMax IntakeSpin = new SparkMax(IntakeConstants.IntakeSpinCanID, MotorType.kBrushless);
    private final RelativeEncoder ControlEncoder = IntakeControl.getEncoder();
    double MaxSpeed = 0.50;
    public static final SparkMaxConfig motorConfig = new SparkMaxConfig();
    public SparkClosedLoopController IntakeControlPID = IntakeControl.getClosedLoopController();
    double PositionGoal;
    public void periodic(){
      SmartDashboard.putNumber("Control Encoder Position: ", ControlEncoder.getPosition());
      SmartDashboard.putNumber("position goal: ", PositionGoal);
      // SmartDashboard.putNumber("Intake Control Speed: ", IntakeControl.get());
      // SmartDashboard.putNumber("Intake Spin Speed: ", IntakeSpin.get());
      // SmartDashboard.putNumber("Intake Spin Speed: ", IntakeSpin.get());
        // IntakeControlPID.setSetpoint(PositionGoal, ControlType.kPosition);
      }

    public void Init(){
      double PositionGoal = 0;
      // int x = 0;
      ControlEncoder.setPosition(0.0);
      // int randNum = (int)(Math.random() * 10);
      // while(x > 11){
      //   x++;
      //   if (x <= randNum){
      //     kendall[x] = "▮";
      //   } else
      //   kendall[x] = "▯";
      // }
      // SmartDashboard.putStringArray("Kendall-O-Meter: ", kendall);
    }

    public void config() {
    IntakeControlPID = IntakeControl.getClosedLoopController();

    motorConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        // Set PID values for position control. We don't need to pass a closed loop
        // slot, as it will default to slot 0.
        .p(0.25)
        // speed
        .i(0)
        // integral
        .d(0.0)
        // kinda like friction
        .iZone(0)
        .outputRange(-0.8, 0.5);
            // m_Shooter.setP(1);

    IntakeControl.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

// waiting waiting waiting waiting
  }


    public void controlrun(double speed){
          if ((PositionGoal + speed) > IntakeConstants.ControlMin){
        PositionGoal = IntakeConstants.ControlMin;
        // IntakeControlPID.setSetpoint(PositionGoal, ControlType.kPosition);
      } else if ((PositionGoal + speed) < IntakeConstants.ControlMax){
        PositionGoal = IntakeConstants.ControlMax;
  } else{
    PositionGoal = PositionGoal + speed;
  }
IntakeControlPID.setSetpoint(PositionGoal, ControlType.kPosition);
SmartDashboard.putNumber("intake position", 11);
IntakeControl.set(speed);
// SmartDashboard
}

    public void spinrun(double speed){
    IntakeSpin.set(-speed/5);
  }
}