package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class RobotController {
    SwerveModule frontLeftSwerve;
    SwerveModule frontRightSwerve;
    SwerveModule backLeftSwerve;
    SwerveModule backRightSwerve;
    List<SwerveModule> swerves;

    IMU imu;

    Telemetry telemetry;

    public RobotController(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        frontLeftSwerve = new SwerveModule(hardwareMap, telemetry, "frontLeft");
        frontRightSwerve = new SwerveModule(hardwareMap, telemetry, "frontRight");
        backLeftSwerve = new SwerveModule(hardwareMap, telemetry, "backLeft");
        backRightSwerve = new SwerveModule(hardwareMap, telemetry, "backRight");
        swerves = List.of(frontLeftSwerve, frontRightSwerve, backLeftSwerve, backRightSwerve);

        imu = hardwareMap.get(IMU.class, "imu");
    }
    public void moveAtAngle(double angle, float power, int distance)
    {
        for(SwerveModule swerve : swerves)
        {
            swerve.setAngle(angle);
            swerve.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            swerve.motor.setTargetPosition(distance);
            swerve.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            swerve.setPower(power);
        }
    }

    public void turnToAngle(double angle, float power)
    {
        if(imu.getRobotYawPitchRollAngles().getYaw() < angle)
        {
            for(SwerveModule swerve : swerves)
            {
                swerve.setAngle(swerve.getAngleToTurn());
            }
        }
        while(imu.getRobotYawPitchRollAngles().getYaw() < angle)
        {
            double distFromAngle = angle-imu.getRobotYawPitchRollAngles().getYaw();
            double powerMult = Math.min(1, distFromAngle/10);
            for(SwerveModule swerve : swerves)
            {
                swerve.setPower(power);
            }
        }

        if(imu.getRobotYawPitchRollAngles().getYaw() > angle)
        {
            for(SwerveModule swerve : swerves)
            {
                swerve.setAngle(-swerve.getAngleToTurn());
            }
        }
        while(imu.getRobotYawPitchRollAngles().getYaw() > angle)
        {
            double distFromAngle = angle-imu.getRobotYawPitchRollAngles().getYaw();
            double powerMult = Math.min(1, distFromAngle/10);
            for(SwerveModule swerve : swerves)
            {
                swerve.setPower(power);
            }
        }
    }
}
