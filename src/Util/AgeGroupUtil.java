/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Test
 */
public class AgeGroupUtil {
    public static final String FIRST = "5-10";
    public static final String SECOND = "11-15";
    public static final String THIRD = "16-20";
    public static final String FOURTH = "21-25";
    public static final String FIFTH = "26-30";
    
    public static double round(double value)
    {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
