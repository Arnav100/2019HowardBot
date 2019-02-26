package org.firstinspires.ftc.teamcode;

public class DriveTrain {


    public void drive()
    {
     //   move(RobotMap.g1.right_stick_y, RobotMap.g1.left_stick_y);
//        move(0.2, 0.2);
        mecanumDrive(RobotMap.g1.right_stick_x, RobotMap.g1.right_stick_y, 0);
    }

    public void move(double rightSpeed, double leftSpeed)
    {
        RobotMap.tel.addData("RightSpeed: " ,rightSpeed);
        RobotMap.rightFront.setPower(rightSpeed);
        RobotMap.rightBack.setPower(rightSpeed);
        RobotMap.leftFront.setPower(leftSpeed);
        RobotMap.leftBack.setPower(leftSpeed);
    }

    public void mecanumDrive2(double x, double y)
    {
        RobotMap.leftFront.setPower((x + y) / 2);
        RobotMap.rightFront.setPower((-x + y) / 2);
        RobotMap.leftBack.setPower((-x + y) / 2);
        RobotMap.rightBack.setPower((x + y) / 2);
    }

    public void mecanumDrive(double x, double y, double rotation)
    {
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = x + y + rotation;
        wheelSpeeds[1] = -x + y - rotation;
        wheelSpeeds[2] = -x + y + rotation;
        wheelSpeeds[3] = x + y - rotation;

        normalize(wheelSpeeds);

        RobotMap.leftFront.setPower(wheelSpeeds[0]);
        RobotMap.rightFront.setPower(wheelSpeeds[1]);
        RobotMap.leftBack.setPower(wheelSpeeds[2]);
        RobotMap.rightBack.setPower(wheelSpeeds[3]);
    }   //mecanumDrive_Cartesian

    private void normalize(double[] wheelSpeeds)
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }   //normalize
}
