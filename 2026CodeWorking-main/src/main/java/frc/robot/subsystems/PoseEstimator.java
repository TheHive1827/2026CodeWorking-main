package frc.robot.subsystems;

import java.util.Collections;
import java.util.List;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;

public class PoseEstimator {
    private final PhotonSubsystem m_photonSubsystem = new PhotonSubsystem();
    private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();



    private static final List<Pose3d> targetPoses = Collections.unmodifiableList(List.of(
        new Pose3d(3, 0, 0, new Rotation3d(0, 0, 0)), new Pose3d(0, 0, 0, new Rotation3d(0, 0, 0))));
     public void periodic() {
        m_photonSubsystem.read();
    }
}
