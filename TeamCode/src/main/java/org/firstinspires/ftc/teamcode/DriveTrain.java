package org.firstinspires.ftc.teamcode;

public class DriveTrain {

    private double[] desiredVals;
    public double[] currentVals;

    public DriveTrain()
    {
        reset();
    }

    public void reset() {
        desiredVals = new double[2];
        currentVals = new double[2];
    }

    public void drive()
    {
        sigmoidMecanumMove(RobotMap.g1.right_stick_x, RobotMap.g1.right_stick_y, 10);
    }

    public void move(double rightSpeed, double leftSpeed)
    {
        RobotMap.rightFront.setPower(rightSpeed);
        RobotMap.rightBack.setPower(rightSpeed);git a
        RobotMap.leftFront.setPower(leftSpeed);
        RobotMap.leftBack.setPower(leftSpeed);
    }

    public void sigmoidMecanumMove(double x, double y, double a)
    {
        x = getSigVal(x, true, a);
        y = getSigVal(y, false, a);
        mecanumMove(x, y);
    }

    private double getSigVal(double val, boolean isX, double a)
    {
        //Determines the index of the array, left - 0, right - 1
        int index = 0;
        if(isX)
            index = 1;

        //The sigmoid function can never reach 1 or -1, so this caps the input
        if(val > 0.99 || val < -0.99)
            val = val * 0.99;

        //Records the desired speed, and if that has been roughly reached by
        //the function, return the desired speed.
        desiredVals[index] = val;
        if (Math.abs(desiredVals[index] - currentVals[index]) < 0.01) {
            return desiredVals[index];
        }

        //Calculates the current x value in the function
        double startTime = inverseSig(currentVals[index], a);

        //Used to get the next value the robot should use
        double cycleTime = 0.02;
        if (desiredVals[index] < currentVals[index])
            cycleTime = -cycleTime;

        return sigmoid(startTime + cycleTime, a);
    }

    private double sigmoid(double time, double a) {
        return (1 / (1 + Math.pow(Math.E, -time * a))) -0.5;
    }


    private double inverseSig(double speed, double a) {
        return -Math.log(1 / (speed + 0.5) - 1) / a;
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
