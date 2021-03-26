@file:Suppress("ControlFlowWithEmptyBody")

package org.firstinspires.ftc.teamcode.opmodes

import android.graphics.Color
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.baseClasses.MecanumAutoOpMode
import org.firstinspires.ftc.teamcode.baseClasses.MecanumDriveParameters
import org.firstinspires.ftc.teamcode.baseClasses.PowerInstructions
import org.firstinspires.ftc.teamcode.baseClasses.UnitAngle

@Autonomous(name = "Autonomous")
class Auto: MecanumAutoOpMode() {
    override var autonomousSide: AutonomousSide
        get() = TODO("Not yet implemented")
        set(value) {}


    var nextEventTime: Double? = null
    var event = 0

    private var drivingSpeed: Double? = null
    private var drivingBackward: Boolean? = null
    private var colour = true

    private fun move(speed: Double, back: Boolean = false) {
        drivingSpeed = speed
        drivingBackward = back
        val params = MecanumDriveParameters.fromDirectionAndMagnitude(UnitAngle.degrees(if (!back) 270.0 else 90.0), speed, hardware)
        val instructions = PowerInstructions.fromDriveParams(params)
        instructions.applyOnHardware(hardware)
    }

    private fun stopDrive() {
        stopMotion()
        drivingSpeed = null
        drivingBackward = null
    }

    override fun mecaLoop() {
        if (colour && drivingSpeed != null && drivingBackward != null) {
            move(drivingSpeed!!, drivingBackward!!)

            if (!drivingBackward!! && hardware.colorChecker.isOverLine()) {
                nextEventTime = time - 1.0
            }
        }

        if (nextEventTime == null && event == 0) {
            hardware.base.motors.forEach { motor ->
                motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            }
            move(0.3)
        } else if (nextEventTime == null) {
        } else if (time > nextEventTime!!) {
            nextEventTime = null

            when(event) {
                0 -> {
                    stopDrive()
                    nextEventTime = time + 0.5
                }
                1 -> {
                    move(0.2, back = true)
                    nextEventTime = time + 1.0
                }
                2 -> {
                    stopDrive()
                    hardware.shooter.prepare()
                    hardware.pickup.start()
                    nextEventTime = time + 1
                }
                3 -> {
                    hardware.shooter.pushUp()
                    nextEventTime = time + 1
                }
                4 -> {
                    hardware.shooter.pushDown()
                    nextEventTime = time + 1
                }
                5 -> {
                    hardware.shooter.pushUp()
                    nextEventTime = time + 1
                }
                6 -> {
                    hardware.shooter.pushDown()
                    nextEventTime = time + 1
                }
                7 -> {
                    hardware.shooter.pushUp()
                    nextEventTime = time + 1
                }
                8 -> {
                    hardware.shooter.pushDown()
                    nextEventTime = time + 1
                }
                9 -> {
                    hardware.shooter.pushUp()
                    nextEventTime = time + 1
                }
                10 -> {
                    hardware.shooter.pushDown()
                    nextEventTime = time + 1
                }
                11 -> {
                    hardware.shooter.pushUp()
                    nextEventTime = time + 1
                }
                12 -> {
                    hardware.shooter.pushDown()
                    hardware.shooter.shutdown()
                    hardware.pickup.stop()
                    nextEventTime = time + 1
                }
                13 -> {
                    move(0.2)
                    colour = false
                    nextEventTime = time + 1.0
                }
                14 -> {
                    stopMotion()
                }
                else -> {}
            }

            event += 1
        }
    }
}