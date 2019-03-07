package org.firstinspires.ftc.teamcode;

public class DriveTrain {


    public void drive()
    {
        move(-RobotMap.g1.right_stick_y / 4, -RobotMap.g1.left_stick_y / 4);
    }

    public void move(double rightSpeed, double leftSpeed)
    {
        // Left front is inverted
        RobotMap.rightFront.setPower(rightSpeed);
        RobotMap.rightBack.setPower(rightSpeed);
        RobotMap.leftFront.setPower(-leftSpeed);
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

    public void mecanumMoveWithRotation(double x, double y)
    {
        // Rotate at extreme values
        if(Math.abs(x) > 0.06)
        {
            move(-x * 1.5, x * 1.5);
            return;
        }

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
