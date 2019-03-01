package org.firstinspires.ftc.teamcode;

public class DriveTrain {


    public void drive()
    {
        mecanumMove(RobotMap.g1.right_stick_x, RobotMap.g1.right_stick_y);
    }

    public void move(double rightSpeed, double leftSpeed)
    {
        RobotMap.rightFront.setPower(rightSpeed);
        RobotMap.rightBack.setPower(rightSpeed);
        RobotMap.leftFront.setPower(leftSpeed);
        RobotMap.leftBack.setPower(leftSpeed);
    }

    public void mecanumMove(double x, double y)
    {
        // No normalization needed since the d-pad is circular

        // leftFront is inverted
        RobotMap.leftFront.setPower(-(x + y) / 2);
        RobotMap.rightFront.setPower((-x + y) / 2);
        RobotMap.leftBack.setPower((-x + y) / 2);
        RobotMap.rightBack.setPower((x + y) / 2);
    }
}
