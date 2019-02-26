package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RobotMap {
    public static DcMotor rightFront;
    public static DcMotor rightBack;
    public static DcMotor leftFront;
    public static DcMotor leftBack;

    public static HardwareMap hwMap;
    public static Telemetry tel;
    public static Gamepad g1;
    public static Gamepad g2;

    public static void init(HardwareMap hardwareMap, Telemetry telem)
    {
        init(hardwareMap, tel, null, null);
    }

    public static void init(HardwareMap hardwareMap, Telemetry telem, Gamepad gamePad1, Gamepad gamePad2)
    {
        hwMap = hardwareMap;
        tel = telem;
        g1 = gamePad1;
        g2 = gamePad2;
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
    }


}
