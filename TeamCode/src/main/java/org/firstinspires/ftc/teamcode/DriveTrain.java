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
        RobotMap.leftFront.setPower(-ensureRange(x + y));
        RobotMap.rightFront.setPower(ensureRange(-x + y));
        RobotMap.leftBack.setPower(ensureRange(-x + y));
        RobotMap.rightBack.setPower(ensureRange(x + y));
    }

    private double ensureRange(double value) {
        return Math.min(Math.max(value, -1), 1);
    }
}
