package frc.robot.subsystems;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
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

public class IntakeSubsystem extends SubsystemBase{
  // XboxController m_shooterController = new XboxController(OIConstants.kShooterControllerPort);
  // private ShooterSubsystem m_shooter = new ShooterSubsystem();
    // private final SparkMax m_IntakeMotor = new SparkMax(IntakeConstants.IntakeCanID, MotorType.kBrushless);
    // private final SparkMax m_IntakeMotor2 = new SparkMax(IntakeConstants.IntakeCanID, MotorType.kBrushless);
    // private final 
    private final SparkMax IntakeControl = new SparkMax(IntakeConstants.IntakeControlCanID, MotorType.kBrushless);
    private final SparkMax IntakeSpin = new SparkMax(IntakeConstants.IntakeSpinCanID, MotorType.kBrushless);
    private final RelativeEncoder ControlEncoder = IntakeControl.getEncoder();
    double MaxSpeed = 0.25;

    public void periodic(){
      SmartDashboard.putNumber("Control Encoder Position: ", ControlEncoder.getPosition());
      SmartDashboard.putString("Kendall-O-Meter: ", "yes");
      SmartDashboard.putNumber("Intake Control Speed: ", IntakeControl.get());
      SmartDashboard.putNumber("Intake Spin Speed: ", IntakeSpin.get());
    }

    public void InitEncoder(){
      ControlEncoder.setPosition(0.0);
    }


    public void IntakePeriodic(){
        // SmartDashboard.putNumber("IntakeControl", SparkMax.getBus);
    }


    public void controlrun(double speed){
    IntakeControl.set(speed/5);
  }

  public void vortexrun(double speed){
    IntakeControl.set(speed/5);
  }

      public void spinrun(double speed){
    IntakeSpin.set(-speed/5);
  }

  // public void RunAll(double speed){
  //   IntakeSpin.set(-speed/5);
  //   m_shooter.m_Conveyor.set(speed/5);
  //   m_shooter.m_Vector.set(speed/5);
  //   m_shooter.m_Shooter.set(speed/5);
  // }
    // public void Intake(boolean forward, boolean back){
    // public void Intake(int speed){
    //     speed = 0;
    //     if (forward == true){
    //         speed++;
    //     }
    //     if (back == true){
    //         speed--;
    //     }
    //     m_IntakeMotor.set(MaxSpeed*speed);
    // }

    // public void Intake2(int speed){
    //     m_IntakeMotor2.set(MaxSpeed*speed);
    // }

    // public void IntakeControl
    // IDK WHAT THE POINT OF THIS IS BUT ILL GUESS WE'LL SEE
}