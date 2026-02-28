package frc.robot.subsystems;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;

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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Configs;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase{
    private final SparkMax m_IntakeMotor = new SparkMax(IntakeConstants.IntakeCanID, MotorType.kBrushless);
    private final SparkMax m_IntakeMotor2 = new SparkMax(IntakeConstants.IntakeCanID, MotorType.kBrushless);
    double MaxSpeed;
    public void periodic(){
        // MaxSpeed = SmartDashboard.getNumber("Intake Speed", 0);
        MaxSpeed = 0.25;
    }

    // public void Intake(boolean forward, boolean back){
    public void Intake(int speed){
        // speed = 0;
        // if (forward == true){
        //     speed++;
        // }
        // if (back == true){
        //     speed--;
        // }
        m_IntakeMotor.set(MaxSpeed*speed);
    }

    public void Intake2(int speed){
        m_IntakeMotor2.set(MaxSpeed*speed);
    }
    // IDK WHAT THE POINT OF THIS IS BUT ILL GUESS WE'LL SEE
}