package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.baseClasses.MecanumTeleOpMode


@TeleOp(name = "Colour Tester")
class ColourTester: MecanumTeleOpMode() {
    override fun mecaLoop() {
        val colours = hardware.colorChecker.colours()
        logger { log ->
            log.text("Red   :: ${colours[0]}")
            log.text("Green :: ${colours[1]}")
            log.text("Blue  :: ${colours[2]}")
        }
    }
}