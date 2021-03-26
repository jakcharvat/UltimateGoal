package org.firstinspires.ftc.teamcode.baseClasses

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.hardware.*
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference
import org.firstinspires.ftc.robotcore.external.navigation.Orientation

class RobotHardware(hardwareMap: HardwareMap) {
    val base: Base = Base(hardwareMap)
    val shooter: Shooter = Shooter(hardwareMap)
    val pickup: Pickup = Pickup(hardwareMap)
    val wobbleArm: WobbleArm = WobbleArm(hardwareMap)
    val colorChecker: ColorChecker = ColorChecker(hardwareMap)

    /// I couldn't get `hardwareMap.get(BNO055IMU::class, "imu")` to work in Kotlin, so I wrote
    /// a helper class `ImuBridge` in Java to fetch the IMU for me from an environment where
    /// `hardwareMap.get(BNO055IMU::class, "imu")` works
    /**
     * The IMU sensor on the robot. Used for getting the robot's orientation among other things
     */
    private val imu: BNO055IMU = ImuBridge.get(hardwareMap)

    fun getOrientation(): Orientation {
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


    init {
        initImu()
    }


    private fun initImu() {
        val imuParams = BNO055IMU.Parameters()
        imuParams.angleUnit = BNO055IMU.AngleUnit.DEGREES
        imu.initialize(imuParams)
    }


    class Base(hardwareMap: HardwareMap) {
        companion object {
            @JvmStatic private fun getMotor(name: String, hardwareMap: HardwareMap, reverse: Boolean = false): DcMotor {
                val motor = hardwareMap.dcMotor.get(name)
                if (reverse) {
                    motor.direction = DcMotorSimple.Direction.REVERSE
                }
                return motor
            }
        }

        val leftFront: DcMotor = Base.getMotor("leftFrontMotor", hardwareMap)
        val leftBack: DcMotor = Base.getMotor("leftBackMotor", hardwareMap)
        val rightFront: DcMotor = Base.getMotor("rightFrontMotor", hardwareMap, reverse = true)
        val rightBack: DcMotor = Base.getMotor("rightBackMotor", hardwareMap, reverse = true)

        val motors: Array<DcMotor>
            get() = arrayOf(leftFront, leftBack, rightFront, rightBack)
    }


    class Shooter(hardwareMap: HardwareMap) {
        private val leftMotor: DcMotor = hardwareMap.dcMotor.get("left")
        private val rightMotor: DcMotor = {
            val motor = hardwareMap.dcMotor.get("right")
            motor.direction = DcMotorSimple.Direction.REVERSE
            motor
        }()

        private val servo: Servo = hardwareMap.servo.get("servo")
        private var enabled = false

        fun prepare() {
            servo.controller.pwmEnable()
            leftMotor.power = 1.0
            rightMotor.power = 1.0
            enabled = true
        }

        fun shutdown() {
            leftMotor.power = 0.0
            rightMotor.power = 0.0
            pushDown()
            enabled = false
        }

        fun pushUp() {
            if (enabled) {
                servo.position = 0.7
            }
        }

        fun pushDown() {
            if (enabled) {
                servo.position = 0.3
            }
        }
    }


    class Pickup(hardwareMap: HardwareMap) {
        private val motor: DcMotor = hardwareMap.dcMotor.get("pick_up")

        fun start() {
            motor.power = 1.0
        }

        fun stop() {
            motor.power = 0.0
        }
    }


    class WobbleArm(hardwareMap: HardwareMap) {
        private val servo: Servo = hardwareMap.servo.get("grab_servo")

        fun up() {
            servo.position = 0.63
        }

        fun down() {
            servo.position = 0.39
        }

        fun out() {
            servo.position = 0.44
        }
    }


    class ColorChecker(hardwareMap: HardwareMap) {
        private val colorSensor: ColorSensorBridge = ColorSensorBridge(hardwareMap)

        fun isOverLine(): Boolean {
            return colorSensor.isOverLine
        }

        fun colours(): List<Int> {
            return colorSensor.colors().toList()
        }
    }
}