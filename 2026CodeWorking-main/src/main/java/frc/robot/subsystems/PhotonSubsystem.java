package frc.robot.subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.simulation.PhotonCameraSim;
import org.photonvision.simulation.SimCameraProperties;
import org.photonvision.simulation.VisionSystemSim;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Distance;
import frc.robot.Constants.PhotonConstants;
import frc.robot.subsystems.ShooterSubsystem;
// import org.photonvision.targeting.AprilTagFieldLayout;


public class PhotonSubsystem extends SubsystemBase {
    
    PhotonCamera camera = new PhotonCamera("photonvision");
    
    // private final PhotonCamera photonCamera;
    PhotonTrackedTarget target;
    double DistanceX = 1;
    double IDS[];
    double Yaws[];
    double Pitchs[];
    double DistanceY = 1;
    double DistanceZ = 1;
    boolean targetVisible = false;
     double targetYaw = 0.0;
    double targetPitch = 0.0;
    double TargetHeight = 0.0;
    // var camToTarget = target.getBestCameraToTarget();
     double PhotonDistance = 0.0; 
    double targetX;
    double TargetID;
    int x = 0;

    

    // public void resultget(){
    //     // PhotonPipelineResult result = camera.getLatestResult();
    //     var result = camera.getLatestResult();
    //     boolean hasTargets = result.hasTargets();
    //     List<PhotonTrackedTarget> targets = result.getTargets();
    // PhotonTrackedTarget target = result.getBestTarget();
        
    // }

//     public void getdata(){
//     int targetID = target.getFiducialId();
//     double poseAmbiguity = target.getPoseAmbiguity();
//     Transform3d bestCameraToTarget = target.getBestCameraToTarget();
//     Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();
// }

    public void photonPeriodic(){
        read();
    }

    public void read(){
        x = 0;
        var results = camera.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            // targetX;
            var result = results.get(results.size() - 1);
            // if (result.hasTargets()) {
                // At least one AprilTag was seen by the camera
                for (var target : result.getTargets()) { 
                    SmartDashboard.putNumber("Target ID: ", target.getFiducialId());
                    if (target.getFiducialId() == 16) {
                        x++;
                        // If Tag 16, this happens.
                        targetYaw = target.getYaw();
                        targetPitch = target.getPitch();
                        TargetHeight = (PhotonConstants.ArenaTagHeight - PhotonConstants.CameraHeight);
                        targetVisible = true;
                        TargetID = target.getFiducialId();
                        // targetPitch()
                        SmartDashboard.putNumber("yaw", targetYaw);
                        SmartDashboard.putNumber("pitch", targetPitch);

                        
                        
                        // angleT = 90.0 - targetPitch.toDegrees();
                        // 3.19.2026, I probably lost my headphones                       // HypotenuseSquared = (target.getBestCameraToTarget().getX() * target.getBestCameraToTarget().getX() + target.getBestCameraToTarget().getY() * target.getBestCameraToTarget().getY());
                        // PhotonDistance = (Math.sqrt((target.getBestCameraToTarget().getX() * target.getBestCameraToTarget().getX() + target.getBestCameraToTarget().getY() * target.getBestCameraToTarget().getY())));
}

                    }
                } else{
                    PhotonDistance = 0.0;
                    targetVisible = false;
                    targetYaw = 0.0;
                    targetPitch = 0.0;
                    TargetHeight = 0.0;
                    PhotonDistance = 0.0; 
                    TargetID = 0;
                }
            }
            
    }
// }

// R.I.P. Limelight 3 = 3-25-2026.
// nvm we resurrected you gng