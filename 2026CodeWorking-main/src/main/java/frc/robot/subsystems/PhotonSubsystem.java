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
// import org.photonvision.targeting.AprilTagFieldLayout;

public class PhotonSubsystem {
    PhotonCamera camera = new PhotonCamera("photonvision");
    // private final PhotonCamera photonCamera;
    PhotonTrackedTarget target;

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

    public void read(){
        var results = camera.getAllUnreadResults();
        if (!results.isEmpty()) {
            // Camera processed a new frame since last
            // Get the last one in the list.
            boolean targetVisible = false;
            var targetYaw = 0.0;
            var targetPitch = 0.0;
            var camToTarget = target.getBestCameraToTarget();
            // double targetRoll = 0.0;
            var result = results.get(results.size() - 1);
            if (result.hasTargets()) {
                // At least one AprilTag was seen by the camera
                for (var target : result.getTargets()) {
                    if (target.getFiducialId() == 16) {
                        // Found Tag 7, record its information
                        targetYaw = target.getYaw();
                        targetPitch = target.getPitch();
                        // targetRoll = target.getSkew();
                        targetVisible = true;
                        // if (aprilTagFieldLayout.getTagPose(target.getFiducialId()).isPresent()) {
                        //     Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(target.getBestCameraToTarget(), aprilTagFieldLayout.getTagPose(target.getFiducialId()).get(), cameraToRobot);
                        // Pose3D robotPose = PhotonUtils.estimateFieldToRobot(
//   kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);

}

                    }
                }
            }
            
    }
    // Calculate robot's field relative pose
// if (aprilTagFieldLayout.getTagPose(target.getFiducialId()).isPresent()) {
//   Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(target.getBestCameraToTarget(), aprilTagFieldLayout.getTagPose(target.getFiducialId()).get(), cameraToRobot);
// Pose2D robotPose = PhotonUtils.estimateFieldToRobot(
//   kCameraHeight, kTargetHeight, kCameraPitch, kTargetPitch, Rotation2d.fromDegrees(-target.getYaw()), gyro.getRotation2d(), targetPose, cameraToRobot);

}

    