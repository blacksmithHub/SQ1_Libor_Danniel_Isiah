package com.example.libor.sq1_libor_danniel_isiah;

/**
 * Created by libor on 1/10/2018.
 */

public class MilkTeaClass {
    private int teaimage[];
    private int addonsimage[];
    private String sugarlevel[];
    private String teasize[];
    private String teaflavor[];
    private String teaaddons[];
    private Double addonsprice[];
    private Double teaprice[][];
public MilkTeaClass()
{
    teaimage = new int[] {R.drawable.milktea1,R.drawable.milktea2,R.drawable.milktea3,R.drawable.milktea4,R.drawable.milktea5};
    addonsimage = new int[] {R.drawable.noaddons,R.drawable.boba,R.drawable.coffeejelly,R.drawable.greenapplejelly,R.drawable.greenreadbean,
            R.drawable.greenteaboba,R.drawable.mangojelly};
    sugarlevel = new String[] {"No","25%","50%","75%","Full"};
    teasize = new String[] {"Small","Medium","Large"};
    teaflavor = new String[] {"Original","Emperor","Dragon","Black Pearl","Okinawa"};
    teaaddons = new String[] {"No Add Ons","Boba Jelly","Coffee Jelly","Green Apple Jelly","Green/Red Beans"
            ,"Green TeaBoba","Mango Jelly"};
    addonsprice = new Double[] {0.00,10.00,11.00,13.00,12.00,14.00,15.00};
    teaprice = new Double[][] {{70.00,80.00,90.00},{75.00,85.00,95.00},{70.00,80.00,90.00},{72.00,82.00,92.00},{80.00,90.00,100.00}};
}
    public int getTeaImg(int index) {
        return teaimage[index];
    }
    public int getAddOnsImg(int index) {
        return addonsimage[index];
    }
    public String getSugarLevel(int index) {
        return sugarlevel[index];
    }
    public String getTeaSize(int index) {
        return teasize[index];
    }
    public String getTeaFlavor(int index) {
        return teaflavor[index];
    }
    public String getTeaAddOns(int index) {
        return teaaddons[index];
    }
    public Double getAddOnsPrice(int index) {
        return addonsprice[index];
    }
    public Double getTeaPrice(int index1, int index2) {
        return teaprice[index1][index2];
    }
}
