package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.OpenCVPipeline;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Mat;
import org.opencv.core.Point;

@TeleOp

public class FishOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        RobotMap.init(hardwareMap, telemetry, gamepad1, gamepad2);
        Robot.init();

        // Set up detector
        FishDetector detector = new FishDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.enable();

        telemetry.addData("Status", "Initialized DogeCV 2019.1");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("x", detector.x);
            telemetry.addData("y", detector.y);
            telemetry.update();
            Robot.driveTrain.drive();
        }

        // if(detector != null) detector.disable();
    }
}
