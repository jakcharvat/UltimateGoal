package org.firstinspires.ftc.teamcode.baseClasses;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import java.lang.reflect.Array;


public class ColorSensorBridge {
    ColorSensorBridge(HardwareMap hardwareMap) {
        this.colorSensor = hardwareMap.colorSensor.get("colorSensor");
    }

    private final ColorSensor colorSensor;

    boolean isOverLine() {
        int red = colorSensor.red();
        int blue = colorSensor.blue();
        int green = colorSensor.green();

        int average = (red + blue + green) / 3;

        int threshold = 150;
        return (average >= threshold);
    }

    int[] colors() {
        return new int[]{ colorSensor.red(), colorSensor.green(), colorSensor.blue() };
    }
}
