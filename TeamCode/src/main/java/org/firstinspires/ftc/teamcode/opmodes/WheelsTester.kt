package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.baseClasses.MecanumDriveParameters
import org.firstinspires.ftc.teamcode.baseClasses.MecanumTeleOpMode
import org.firstinspires.ftc.teamcode.baseClasses.PowerInstructions
import org.firstinspires.ftc.teamcode.baseClasses.UnitAngle


@TeleOp(name = "Wheels Tester")
class WheelsTester: MecanumTeleOpMode() {
    override fun mecaLoop() {
        val params = MecanumDriveParameters.fromDirectionAndMagnitude(UnitAngle.degrees(0.0), 0.1, hardware)
        val instructions = PowerInstructions.fromDriveParams(params)
        instructions.applyOnHardware(hardware)

        @Suppress("ControlFlowWithEmptyBody")
        while (!isStopRequested);

        hardware.base.motors.forEach { motor ->
            motor.power = 0.0
        }
    }
}