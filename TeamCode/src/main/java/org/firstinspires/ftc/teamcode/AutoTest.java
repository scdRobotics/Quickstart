package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;

@Autonomous
public class AutoTest extends LinearOpMode {

    public void runOpMode()
    {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        List<DcMotor> motors = List.of(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        Lifting lift = new Lifting(hardwareMap, telemetry);
        lift.moveSlidesToPosition(lift.slideStartPosition);

        waitForStart();

        moveForward(motors, 300, .5);
        moveBackwards(motors, 300, .5);
    }

    public boolean allMotorsDone(List<DcMotor> motors)
    {
        for(DcMotor motor: motors)
        {
            if(motor.isBusy()) return false;
        }
        return true;
    }

    public void moveForward(List<DcMotor> motors, int ticks, double speed)
    {
        for(DcMotor motor : motors)
        {
            motor.setTargetPosition(motor.getCurrentPosition()+ticks);
            motor.setPower(speed);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        while(!allMotorsDone(motors))
        {

            telemetry.addData("frontLeftMotor position", motors.get(0).getCurrentPosition());
            telemetry.addData("frontRightMotor position", motors.get(1).getCurrentPosition());
            telemetry.addData("BackLeftMotor position", motors.get(2).getCurrentPosition());
            telemetry.addData("BackRightMotor position", motors.get(3).getCurrentPosition());

            telemetry.addData("moving forwards", ticks + " ticks");
            telemetry.update();
        }
    }
    public void moveBackwards(List<DcMotor> motors, int ticks, double speed)
    {
        for(DcMotor motor : motors)
        {
            motor.setTargetPosition(motor.getCurrentPosition()-ticks);
            motor.setPower(speed);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        while(!allMotorsDone(motors))
        {
            telemetry.addData("moving backwards", ticks + " ticks");
            telemetry.update();
        }
    }
    public void moveLeft(List<DcMotor> motors, int ticks, double speed)
    {
        motors.get(0).setTargetPosition(motors.get(0).getCurrentPosition()-ticks);
        motors.get(0).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(0).setPower(speed);

        motors.get(3).setTargetPosition(motors.get(3).getCurrentPosition()-ticks);
        motors.get(3).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(3).setPower(speed);


        motors.get(1).setTargetPosition(motors.get(1).getCurrentPosition()+ticks);
        motors.get(1).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(1).setPower(speed);

        motors.get(2).setTargetPosition(motors.get(2).getCurrentPosition()+ticks);
        motors.get(2).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(2).setPower(speed);

        while(!allMotorsDone(motors))
        {
            telemetry.addData("moving left", ticks + " ticks");
            telemetry.update();
        }
    }
    public void moveRight(List<DcMotor> motors, int ticks, double speed)
    {
        motors.get(0).setTargetPosition(motors.get(0).getCurrentPosition()+ticks);
        motors.get(0).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(0).setPower(speed);

        motors.get(3).setTargetPosition(motors.get(3).getCurrentPosition()+ticks);
        motors.get(3).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(3).setPower(speed);


        motors.get(1).setTargetPosition(motors.get(1).getCurrentPosition()-ticks);
        motors.get(1).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(1).setPower(speed);

        motors.get(2).setTargetPosition(motors.get(2).getCurrentPosition()-ticks);
        motors.get(2).setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motors.get(2).setPower(speed);

        while(!allMotorsDone(motors))
        {
            telemetry.addData("moving right", ticks + " ticks");
            telemetry.update();
        }
    }
}
