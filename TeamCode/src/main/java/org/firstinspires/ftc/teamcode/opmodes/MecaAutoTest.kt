package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.baseClasses.MecanumAutoOpMode
import org.firstinspires.ftc.teamcode.baseClasses.navigation.AutonomousTarget


@Autonomous
class MecaAutoTest: MecanumAutoOpMode() {
    override var autonomousSide: AutonomousSide = AutonomousSide.BLUE

    override fun mecaLoop() {
        if (hasReachedTarget()) {
            setTarget(AutonomousTarget.FIELD_CENTRE)
        }
    }
}