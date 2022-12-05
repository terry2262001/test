package com.example.nguyentruongtho_dh51900920.Utils;

import java.text.DecimalFormat;

public class FormatUtil {
    static DecimalFormat decimalFormat =new DecimalFormat("##,### VND");
    public static String formatPrice(String string){
        return decimalFormat.format(string);
    }
   // public static long formatPriceL(long lo){return  decimalFormat.format(lo);}
}
