package org.firstinspires.ftc.robotcontroller.internal;
// depression
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/*
0 FL
1 FR
2 BR
3 BL
 */

@TeleOp(name = "CLICK THIS !!!")
public class MainTeleOp extends OpMode {
    Drivetrain drivetrain;
    IMU imu;
    Orientation angles;


    @Override
    public void init() {
        drivetrain  = new Drivetrain(hardwareMap);

        // IMU
        RevHubOrientationOnRobot.LogoFacingDirection logo = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;  // logo facing up
        RevHubOrientationOnRobot.UsbFacingDirection usb = RevHubOrientationOnRobot.UsbFacingDirection.UP;   // usb facing forward
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logo, usb);

        IMU.Parameters parameters = new IMU.Parameters(orientationOnRobot);

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.resetYaw();
        imu.initialize(parameters);
        angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    public void init_loop() {}

    public void start() {}

    @Override
    public void loop() {
        drivetrain.drive(gamepad1.left_stick_x * (1 - 0.9 * gamepad1.right_trigger),
                        -gamepad1.left_stick_y * (1 - 0.9 * gamepad1.right_trigger),
                        gamepad1.right_stick_x * (1 - 0.9 * gamepad1.right_trigger));
//        drivetrain.drive(0.1, 0, 0);
        telemetry();
    }

    public void telemetry() {
        telemetry.addLine("GAMEPAD");
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addLine();

        drivetrain.telemetry(telemetry);

        telemetry.addLine("ANGLES");
        telemetry.addData("First Angle", angles.firstAngle);
        telemetry.addData("Second Angle", angles.secondAngle);
        telemetry.addData("Third Angle", angles.thirdAngle);
        telemetry.addData("Yaw Angle", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("Pitch Angle", imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
        telemetry.addData("Roll Angle", imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));
        telemetry.addLine();
        telemetry.update();
    }
}
