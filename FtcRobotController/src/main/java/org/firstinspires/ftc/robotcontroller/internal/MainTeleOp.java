package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.internal.Drivetrain;

@TeleOp
public class MainTeleOp extends OpMode {
    Drivetrain drivetrain;

    @Override
    public void init() {
        drivetrain  = new Drivetrain(hardwareMap);
    }

    public void init_loop() {}

    public void start() {}

    @Override
    public void loop() {
        drivetrain.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        telemetry();
    }

    public void telemetry() {
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addLine();

        drivetrain.telemetry(telemetry);
        telemetry.update();
    }
}
