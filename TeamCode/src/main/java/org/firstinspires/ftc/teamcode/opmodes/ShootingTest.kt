package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorSimple


@TeleOp(name = "Shooting Test")
class ShootingTest : LinearOpMode() {
    override fun runOpMode() {
        val left = hardwareMap.dcMotor.get("left")
        val right = hardwareMap.dcMotor.get("right")
        right.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        left.power = 1.0
        right.power = 1.0

        @Suppress("ControlFlowWithEmptyBody")
        while (!isStopRequested);

        left.power = 0.0
        right.power = 0.0
    }
}