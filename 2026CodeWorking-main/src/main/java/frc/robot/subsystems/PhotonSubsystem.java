package frc.robot.subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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


public class PhotonSubsystem {
    
    PhotonCamera camera = new PhotonCamera("photonvision");
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    // private final PhotonCamera photonCamera;
    PhotonTrackedTarget target;
    double DistanceX = 1;
    double DistanceY = 1;
    double DistanceZ = 1;
    boolean targetVisible = false;
     double targetYaw = 0.0;
    double targetPitch = 0.0;
    double TargetHeight = 0.0;
    // var camToTarget = target.getBestCameraToTarget();
     double PhotonDistance = 0.0; 
    double targetX;
    

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

    public void periodic(){
        SmartDashboard.putNumber("Target Yaw", targetYaw);
    }

    public void read(){
        var results = camera.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            targetVisible = false;
            targetYaw = 0.0;
            targetPitch = 0.0;
            TargetHeight = 0.0;
            // var camToTarget = target.getBestCameraToTarget();
            PhotonDistance = 0.0; 
            // targetX;
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
                // At least one AprilTag was seen by the camera
                for (var target : result.getTargets()) { 
                    if (target.getFiducialId() == 16) {
                        // If Tag 16, this happens.
                        targetYaw = target.getYaw();
                        targetPitch = target.getPitch();
                        TargetHeight = (PhotonConstants.ArenaTagHeight - PhotonConstants.CameraHeight);
                        targetVisible = true;

                        
                        
                        // angleT = 90.0 - targetPitch.toDegrees();
                        // 3.19.2026, I probably lost my headphones.
                        // HypotenuseSquared = (target.getBestCameraToTarget().getX() * target.getBestCameraToTarget().getX() + target.getBestCameraToTarget().getY() * target.getBestCameraToTarget().getY());
                        PhotonDistance = (Math.sqrt((target.getBestCameraToTarget().getX() * target.getBestCameraToTarget().getX() + target.getBestCameraToTarget().getY() * target.getBestCameraToTarget().getY())));
}

                    }
                }
            }
            
    }
}


// R.I.P. Limelight 3 = 3-25-2026.
// nvm we resurrected you gng