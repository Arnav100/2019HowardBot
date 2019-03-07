package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.OpenCVPipeline;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.core.Point;

@Autonomous

public class FishOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {
        RobotMap.init(hardwareMap, telemetry, gamepad1, gamepad2);
        Robot.init();

        // Set up detector
        FishDetector detector = new FishDetector();
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        detector.VUFORIA_KEY = "Afvi4BP/////AAABmUfKsGElb051pBetr7dvfo415RLZaAP348nlKMSIBCtm1ZwSzF15munL8Ofe5RvauwfTckevfVAwHLNbHwDMEVbngrYFgLo/hEmnTPEw+SDsGBu5WC7F+raUsd9VbqGffDqrDy9uSWN8kFCd9B7SooMFVIOJpPJE0ePJlAL6PnypyYTnQWzdlH8C2iaUg+qnapQhVkZEeFp2nTODuxP1jjp2JnnGPk9kAW10/BfUxWYku8YKptstXJE8ZetmclYEDhGCrnuv8LlB/gA6qBUVNTAyNqwuVAcAtXsmyPTlOYZ4tzEUU2/2v7WDdPoBEQvm1l2b8WsyA/aUbuAzqmgkbhzUccaF109pv95HqD9nA7aZ";
        detector.init(hardwareMap.appContext,CameraViewDisplay.getInstance(), DogeCV.CameraMode.WEBCAM, false, webcamName);
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
            Robot.driveTrain.mecanumMoveWithRotation(detector.y, detector.x);
        }

        // if(detector != null) detector.disable();
    }
}
