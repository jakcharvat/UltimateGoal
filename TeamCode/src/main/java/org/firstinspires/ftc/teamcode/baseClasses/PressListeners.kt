package org.firstinspires.ftc.teamcode.baseClasses


private class Listener constructor(private val getValue: () -> Boolean, private val onChange: (Boolean) -> Unit) {
    private var lastValue: Boolean? = null

    fun check() {
        val newValue = getValue()
        if (newValue == lastValue) return

        lastValue = newValue
        onChange(newValue)
    }
}


class PressListeners {
    private val listeners: MutableList<Listener> = mutableListOf()

    fun addListener(getValue: () -> Boolean, onChange: (Boolean) -> Unit) {
        listeners.add(Listener(getValue, onChange))
    }


    fun check() {
        listeners.forEach { listener ->
            listener.check()
        }
    }
}