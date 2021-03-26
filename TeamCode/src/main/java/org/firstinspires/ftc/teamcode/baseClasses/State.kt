package org.firstinspires.ftc.teamcode.baseClasses


//interface State {
//    val startTime: Double
//    val runState: (RobotHardware, Double) -> State?
//}
//
//class ShootState(override val startTime: Double, shootCount: Int, nextState: State?): State {
//    private class ArbitraryState(override val startTime: Double, override val runState: (RobotHardware, Double) -> State?) : State { }
//
//    fun prepareToShoot(hardware: RobotHardware, time: Double): State {
//
//    }
//
//
//    override val runState: (RobotHardware, Double) -> State? = { hardware, time ->
//        hardware.shooter.prepare()
//        ArbitraryState(time + 1, ::shoot)
//    }
//    }
//
//    fun shoot(hardware: RobotHardware, time: Double): State {
//
//    }
//
//    private class Shoot(override val startTime: Double): State {
//        override val runState: (RobotHardware, Double) -> State?
//            get() = TODO("Not yet implemented")
//    }
//
//    private class Retract(override val startTime: Double): State {
//        override val runState: (RobotHardware, Double) -> State?
//            get() = TODO("Not yet implemented")
//    }
//
//}


class State { }