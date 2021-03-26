package org.firstinspires.ftc.teamcode.baseClasses

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix
import org.firstinspires.ftc.teamcode.baseClasses.navigation.AutonomousTarget
import org.firstinspires.ftc.teamcode.baseClasses.navigation.VuforiaNavigation
import kotlin.jvm.Throws
import kotlin.math.atan2
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

abstract class MecanumAutoOpMode: BaseLinearOpMode() {
    private val navigation = VuforiaNavigation(::logger)

    abstract var autonomousSide: AutonomousSide
    private var target: OpenGLMatrix? = null

    /**
     * The main action loop
     */
    @Throws(InterruptedException::class)
    abstract fun mecaLoop()


    fun hasReachedTarget(): Boolean {
        return target == null

    }


    private fun driveToTarget() {
        if (target == null) return

        if (navigation.getLastKnownRobotPosition() == null) {
            logger { log ->
                log.text("No known robot location")
            }
            return
        }

        navigation.getLastKnownRobotPosition().also { location ->
            logger { log -> log.location(location!!) }
            val translation = target!!.subtracted(location).translation
            val magnitude = sqrt(translation[0].toDouble().pow(2) + translation[1].toDouble().pow(2)) / 10
            val direction = UnitAngle.radians(atan2(translation[1], translation[0]).toDouble())
            val driveParams = MecanumDriveParameters.fromDirectionAndMagnitude(direction, min(magnitude, 1.0), hardware)
            val instructions = PowerInstructions.fromDriveParams(driveParams)
            instructions.applyOnHardware(hardware)
        }
    }


    fun setTarget(target: AutonomousTarget) {
        this.target = target.positionMatrix(autonomousSide);
    }


    override fun runLoop() {
        // Initialize the navigation. Hold either the left or right trigger on gamepad 1 when you press the init
        // button to setup Vuforia in debug mode where it will log all values
        //FIXME: For now always logging since I don't have a gamepad, remove the `|| true` before competition
//        navigation.init(hardwareMap, logging = gamepad1.right_bumper || gamepad1.left_bumper || true)
        waitForStart()

        while (opModeIsActive()) {
            driveToTarget()
            mecaLoop()
        }

        navigation.deinit()
    }


    enum class AutonomousSide {
        RED, BLUE
    }
}
