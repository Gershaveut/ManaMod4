package com.gershaveut.manamod;

import java.util.List;

public class Util {
    private static boolean blinking;
    
    public static List<Float> blinkingColor(List<Float> current, List<Float> original, float to, float speed) {
        if (original.get(0) <= current.get(0))
            blinking = true;
        else if (original.get(0) * to >= current.get(0))
            blinking = false;
        
        for (int i = 0; i < current.size(); i++) {
            //current.set(i, transformingNumber(blinking ? original.get(i) * to : original.get(i), current.get(i), speed));
        }
        
        return current;
    }
    
    public static double transformingNumber(double target, double current, float speed) {
        return current + Math.signum(target / speed - current / speed) * speed;
    }
    
    public static float transformingNumber(float target, float current, float speed) {
        return current + Math.signum(target / speed - current / speed) * speed;
    }
}
