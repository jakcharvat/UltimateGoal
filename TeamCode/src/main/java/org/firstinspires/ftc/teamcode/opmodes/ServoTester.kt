package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.baseClasses.MecanumTeleOpMode
import kotlin.properties.Delegates


@TeleOp(name = "Servo Tester")
class ServoTester: MecanumTeleOpMode() {
    lateinit var servo: Servo

    private var servoPosition: Double by Delegates.observable(0.0) { _, _, newValue ->
        servo.position = newValue
        logger { log ->
            log.text("Servo Position :: $servoPosition")
        }
    }


    private fun getStepAmount(): Double {
        return if (gamepad1.right_bumper || gamepad1.left_bumper) 0.1 else 0.01
    }

    override fun beforeStart() {
        servo = hardwareMap.servo.get("servo")

        listeners.addListener({ gamepad1.dpad_up }) { isPressed ->
            if (isPressed) {
                servoPosition += getStepAmount()
            }
        }

        listeners.addListener({ gamepad1.dpad_down }) { isPressed ->
            if (isPressed) {
                servoPosition -= getStepAmount()
            }
        }

        listeners.addListener({ gamepad1.left_trigger > 0.5 || gamepad1.right_trigger > 0.5}) { isPressed ->
            if (isPressed) {
                servo.controller.pwmDisable()
            } else {
                servo.controller.pwmEnable()
            }
        }

        servo.position = servoPosition
    }


    override fun mecaLoop() { }
}