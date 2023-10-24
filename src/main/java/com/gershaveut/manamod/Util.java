package com.gershaveut.manamod;

import java.util.List;

public class Util {
    private static boolean blinkingColor;
    
    public static void blinkingColor(List<Float> current, List<Float> original, float to, float speed) {
        speed /= 10;
        
        if (original.get(0) <= current.get(0))
            blinkingColor = true;
        else if (original.get(0) * to >= current.get(0))
            blinkingColor = false;
        
        for (int i = 0; i < current.size(); i++) {
            current.set(i, transformingNumber(blinkingColor ? original.get(i) * to : original.get(i), current.get(i), speed));
        }
    }
    
    public static double transformingNumber(double target, double current, float speed) {
        return current + Math.signum(target - current) * Math.min(Math.abs(target - current), speed);
    }
    
    public static float transformingNumber(float target, float current, float speed) {
        return current + Math.signum(target - current) * Math.min(Math.abs(target - current), speed);
    }
}
