package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Trebuchet {
    public DcMotorEx trebuchetMotor;
    public DcMotorEx trebuchetMotor2;

    public Trebuchet(HardwareMap hardwareMap) {
        // Connect Motors
        trebuchetMotor = hardwareMap.get(DcMotorEx.class, "Catapult");
        trebuchetMotor2 = hardwareMap.get(DcMotorEx.class, "Catapult2");

        // Set Directions
        trebuchetMotor.setDirection(DcMotor.Direction.FORWARD);
        trebuchetMotor2.setDirection(DcMotor.Direction.FORWARD);

        // Set Motor Mode
        trebuchetMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        trebuchetMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        trebuchetMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set Zero Power Behavior
        trebuchetMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        trebuchetMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Stops Motors on INIT
        trebuchetMotor.setPower(0);
        trebuchetMotor2.setPower(0);

    }

    public void setPower(double power) {
//        trebuchetMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if(trebuchetMotor.getCurrentPosition() > 60 && power > 0) {
            trebuchetMotor.setPower(-1);
        } else {
            trebuchetMotor.setPower(power);
        }
    }

    public void setPower2(double power) {
        trebuchetMotor2.setPower(power);
    }

    public void resetEncoder() {
        trebuchetMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        trebuchetMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

//    public void setPosition(int position) {
//        trebuchetMotor.setTargetPosition(position);
//        trebuchetMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        trebuchetMotor.setPower(1);
//    }

//    public void releaseUsingEncoder(double power) {
//        trebuchetMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        trebuchetMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        trebuchetMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addLine("TREBUCHET");
        telemetry.addLine(String.format("Trebuchet Motor Power: %f", trebuchetMotor.getPower()));
        telemetry.addLine(String.format("Trebuchet Current Position: %d", trebuchetMotor.getCurrentPosition()));
        telemetry.addLine(String.format("Trebuchet Target Position: %d", trebuchetMotor.getTargetPosition()));
        telemetry.addLine();
    }
}
