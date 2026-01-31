package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class SwerveTeleop extends LinearOpMode {
    final double robotWidth = 18, robotLength= 18; //width = distance of robot along forwards/backwards direction, length = distance of robot along left/right direction

    final Vector2 flPos = new Vector2(robotWidth/2, -robotLength/2);
    final Vector2 frPos = new Vector2(robotWidth/2, robotLength/2);
    final Vector2 blPos = new Vector2(-robotWidth/2, -robotLength/2);
    final Vector2 brPos = new Vector2(-robotWidth/2, robotLength/2);

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

            frAngle = frontRightSwerve.getDesiredAngle(dx,dy,dr,frPos);
            flAngle = frontLeftSwerve.getDesiredAngle(dx,dy,dr,flPos);
            brAngle = backRightSwerve.getDesiredAngle(dx,dy,dr,brPos);
            blAngle = backLeftSwerve.getDesiredAngle(dx,dy,dr,blPos);

            if(gamepadMagnitude >= 0.05)
            {
                frontRightSwerve.setAngle(frAngle);
                frontLeftSwerve.setAngle(flAngle);
                backRightSwerve.setAngle(brAngle);
                backLeftSwerve.setAngle(blAngle);
            }
            else gamepadMagnitude = 0;

            frontLeftSwerve.setPower(frontLeftSwerve.getDesiredPower(dx,dy,dr,flPos));
            frontRightSwerve.setPower(frontRightSwerve.getDesiredPower(dx,dy,dr,frPos));
            backLeftSwerve.setPower(backLeftSwerve.getDesiredPower(dx,dy,dr,blPos));
            backRightSwerve.setPower(backRightSwerve.getDesiredPower(dx,dy,dr,brPos));
        }
    }
}
