package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.robotcore.external.Telemetry


@TeleOp(name = "Test OpMode")
class TestOpMode: LinearOpMode() {
    override fun runOpMode() {
        val motor: DcMotor = hardwareMap.dcMotor.get("testMotor")

        waitForStart()


        motor.mode = DcMotor.RunMode.RUN_USING_ENCODER

        motor.power = 1.0
        Thread.sleep(1000)
        motor.power = 0.0

        Thread.sleep(1000)


        telemetry.addData("Status", "Start")
        telemetry.update()

        val ticksPerRotation = 537.6

        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION

        motor.targetPosition = 0
        motor.power = 0.5

        telemetry.addData("Status", "Running Motor 1")
        telemetry.update()

        while (motor.isBusy);

        motor.power = 0.0
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION

        Thread.sleep(1000)

        motor.targetPosition = (ticksPerRotation * 2).toInt()
        motor.power = 0.5

        telemetry.addData("Status", "Running Motor 2")
        telemetry.update()

        while (motor.isBusy);

        telemetry.addData("Status", "Ended")
        telemetry.update()
    }
}