package org.firstinspires.ftc.teamcode;

public class DriveTrain {


    public void drive()
    {
     //   move(RobotMap.g1.right_stick_y, RobotMap.g1.left_stick_y);
        move(0.2, 0.2);
    }

    public void move(double rightSpeed, double leftSpeed)
    {
        RobotMap.tel.addData("RightSpeed: " ,rightSpeed);
        RobotMap.rightFront.setPower(rightSpeed);
        RobotMap.rightBack.setPower(rightSpeed);
        RobotMap.leftFront.setPower(leftSpeed);
        RobotMap.leftBack.setPower(leftSpeed);
    }
}
