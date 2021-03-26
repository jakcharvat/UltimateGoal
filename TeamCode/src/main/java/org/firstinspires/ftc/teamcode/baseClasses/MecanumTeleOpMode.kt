package org.firstinspires.ftc.teamcode.baseClasses

import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.robotcore.external.navigation.Orientation
import kotlin.math.cos
import kotlin.math.sin


abstract class MecanumTeleOpMode: BaseLinearOpMode() {
    /**
     * Drive the robot according to the gamepad
     */
    private fun mecaDrive() {
        val params = MecanumDriveParameters.fromGamepad(gamepad1, hardware = hardware, reduced = gamepad1.b)
        val instructions = PowerInstructions.fromDriveParams(params)
        instructions.applyOnHardware(hardware)
    }


    /**
     * The action executed before the motion of the robot
     */
    open fun preMoveLoop() { }

    /**
     * The setup actions before the loop starts
     */
    open fun beforeStart() { }

    /**
     * The action executed after the motion of the robot
     */
    @Throws(InterruptedException::class)
    abstract fun mecaLoop()

    override fun runLoop() {
        initShootingListeners()
        initPickup()
        initArm()

        beforeStart()
        waitForStart()

        while (opModeIsActive()) {
            preMoveLoop()
            mecaDrive()
            listeners.check()
            mecaLoop()
        }
    }


    private fun initShootingListeners() {
        listeners.addListener({ gamepad1.right_trigger_pressed }) { isPressed ->
            if (isPressed) {
                hardware.shooter.prepare()
            } else {
                hardware.shooter.shutdown()
            }
        }

        listeners.addListener({ gamepad1.right_bumper }) { isPressed ->
            if (isPressed) {
                hardware.shooter.pushUp()
            } else {
                hardware.shooter.pushDown()
            }
        }
    }


    private fun initPickup() {
        listeners.addListener({ gamepad1.left_trigger_pressed }) { isPressed ->
            if (isPressed) {
                hardware.pickup.start()
            } else {
                hardware.pickup.stop()
            }
        }
    }


    private fun initArm() {
        listeners.addListener({ gamepad1.dpad_up }) { isPressed ->
            if (isPressed) {
                hardware.wobbleArm.up()
            }
        }


        listeners.addListener({ gamepad1.dpad_down }) { isPressed ->
            if (isPressed) {
                hardware.wobbleArm.down()
            }
        }


        listeners.addListener({ gamepad1.dpad_left || gamepad1.dpad_right }) { isPressed ->
            if (isPressed) {
                hardware.wobbleArm.out()
            }
        }
    }
}
