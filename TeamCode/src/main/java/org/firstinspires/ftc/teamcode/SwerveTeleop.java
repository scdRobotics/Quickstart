package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

public class SwerveTeleop extends LinearOpMode {

    public void runOpMode()
    {
        SwerveModule frontLeftSwerve = new SwerveModule(hardwareMap, telemetry, "frontLeft");
        SwerveModule frontRightSwerve = new SwerveModule(hardwareMap, telemetry, "frontRight");
        SwerveModule backLeftSwerve = new SwerveModule(hardwareMap, telemetry, "backLeft");
        SwerveModule backRightSwerve = new SwerveModule(hardwareMap, telemetry, "backRight");

        List<SwerveModule> swerves = List.of(frontLeftSwerve,frontRightSwerve,backLeftSwerve,backRightSwerve);

        waitForStart();
        while(!isStopRequested())
        {
            double dx = gamepad1.right_stick_x;
            double dy = -gamepad1.right_stick_y;
            double dr = gamepad1.left_stick_x;

            double gamepadMagnitude = Math.sqrt((dx*dx)+(dy*dy));

            double frAngle, brAngle, flAngle, blAngle;
            double frPower, brPower, flPower, blPower;

            for(SwerveModule swerve : swerves)
            {
                swerve.updateGamepad(dx,dy,dr);
            }

            flAngle = frontLeftSwerve.getDesiredAngle();
            frAngle = frontRightSwerve.getDesiredAngle();
            blAngle = backLeftSwerve.getDesiredAngle();
            brAngle = backRightSwerve.getDesiredAngle();

            flPower = frontLeftSwerve.getDesiredPower();
            frPower = frontRightSwerve.getDesiredPower();
            blPower = backLeftSwerve.getDesiredPower();
            brPower = backRightSwerve.getDesiredPower();

            if(gamepadMagnitude >= 0.05)
            {
                frontLeftSwerve.setAngle(flAngle);
                frontRightSwerve.setAngle(frAngle);
                backLeftSwerve.setAngle(blAngle);
                backRightSwerve.setAngle(brAngle);
            }
            else gamepadMagnitude = 0;

            frontLeftSwerve.setPower(flPower);
            frontRightSwerve.setPower(frPower);
            backLeftSwerve.setPower(blPower);
            backRightSwerve.setPower(brPower);
        }
    }
}
