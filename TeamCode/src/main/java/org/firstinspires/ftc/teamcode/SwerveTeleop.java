package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class SwerveTeleop extends LinearOpMode {

    public void runOpMode()
    {
        SwerveModule frontLeftSwerve = new SwerveModule(hardwareMap, telemetry, "frontLeft");
        SwerveModule frontRightSwerve = new SwerveModule(hardwareMap, telemetry, "frontRight");
        SwerveModule backLeftSwerve = new SwerveModule(hardwareMap, telemetry, "backLeft");
        SwerveModule backRightSwerve = new SwerveModule(hardwareMap, telemetry, "backRight");

        waitForStart();
        while(!isStopRequested())
        {
            double dx = gamepad1.right_stick_x;
            double dy = -gamepad1.right_stick_y;
            double dr = gamepad1.left_stick_x;

            double gamepadMagnitude = Math.sqrt((dx*dx)+(dy*dy));

            double frAngle, brAngle, flAngle, blAngle;

            frAngle = frontRightSwerve.getDesiredAngle(dx,dy,dr);
            flAngle = frontLeftSwerve.getDesiredAngle(dx,dy,dr);
            brAngle = backRightSwerve.getDesiredAngle(dx,dy,dr);
            blAngle = backLeftSwerve.getDesiredAngle(dx,dy,dr);

            if(gamepadMagnitude >= 0.05)
            {
                frontRightSwerve.setAngle(frAngle);
                frontLeftSwerve.setAngle(flAngle);
                backRightSwerve.setAngle(brAngle);
                backLeftSwerve.setAngle(blAngle);
            }
            else gamepadMagnitude = 0;

            frontLeftSwerve.setPower(frontLeftSwerve.getDesiredPower(dx,dy,dr));
            frontRightSwerve.setPower(frontRightSwerve.getDesiredPower(dx,dy,dr));
            backLeftSwerve.setPower(backLeftSwerve.getDesiredPower(dx,dy,dr));
            backRightSwerve.setPower(backRightSwerve.getDesiredPower(dx,dy,dr));
        }
    }
}
