package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.Angle;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Trebuchet {
    public DcMotorEx TrebuchetMotor;

    public Trebuchet(HardwareMap hardwareMap) {
        // Connect Motors
        TrebuchetMotor = hardwareMap.get(DcMotorEx.class, "Catapult");

        // Set Directions
        TrebuchetMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set Motor Mode
        TrebuchetMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set Zero Power Behavior
        TrebuchetMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Stops Motors on INIT
        TrebuchetMotor.setPower(0);

    }

    public void power(double x) {
        TrebuchetMotor.setPower(x);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addLine("TREBUCHET");
        telemetry.addLine(String.format("Trebuchet Motor Power: %f", TrebuchetMotor.getPower()));
        telemetry.addLine();
    }
}
