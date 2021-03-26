package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.baseClasses.MecanumTeleOpMode
import org.firstinspires.ftc.teamcode.baseClasses.left_trigger_pressed
import org.firstinspires.ftc.teamcode.baseClasses.right_trigger_pressed


@TeleOp(name = "Motors Test")
class MotorsTest: MecanumTeleOpMode() {
    override fun mecaLoop() {
        hardware.base.leftFront.power = if (gamepad1.left_bumper) 0.2 else 0.0
        hardware.base.rightFront.power = if (gamepad1.right_bumper) 0.2 else 0.0
        hardware.base.leftBack.power = if (gamepad1.left_trigger_pressed) 0.2 else 0.0
        hardware.base.rightBack.power = if (gamepad1.right_trigger_pressed) 0.2 else 0.0
    }
}