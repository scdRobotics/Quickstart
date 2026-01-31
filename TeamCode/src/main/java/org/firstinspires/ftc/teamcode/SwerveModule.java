package org.firstinspires.ftc.teamcode;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SwerveModule {
    DcMotor motor;
    Servo servo;

    Telemetry telemetry;

    public SwerveModule(HardwareMap hardwareMap, Telemetry telemetry, String moduleName)
    {
        this.telemetry = telemetry;

        this.motor = hardwareMap.get(DcMotor.class, moduleName+"Motor");
        this.servo = hardwareMap.get(Servo.class, moduleName+"Servo");
    }
    public void setPower(double power)
    {
        motor.setPower(power);
    }
    //servo.getPosition = 0 is 0 radians, servo.getPosition = 1 is 2pi radians
    public void setAngle(double radians)
    {
        servo.setPosition((radians+Math.PI)/(2*Math.PI));
    }
    public void setAngleDegrees(double degrees)
    {
        servo.setPosition((degrees+180)/(360));
    }
    public double getDesiredAngle(double dx, double dy, double dr, Vector2 pos)
    {
        double rx = -dr*pos.y;
        double ry = dr*pos.x;
        double wx = dx+rx;
        double wy = dy+ry;

        return Math.atan2(wy,wx);
    }
    public double getDesiredPower(double dx, double dy, double dr, Vector2 pos)
    {
        double rx = -dr*pos.y;
        double ry = dr*pos.x;
        double wx = dx+rx;
        double wy = dy+ry;

        return Math.hypot(wx, wy);
    }
}
