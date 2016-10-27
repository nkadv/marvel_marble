package com.mm.sites.attendance;

/**
 * Created by nayerram on 14-10-2016.
 */
public class AttendanceModel {
    String day;
    int wageMultiplier;
    Boolean present;

    public AttendanceModel(String day){
        this.day = day;
        this.present = true;
        wageMultiplier = 1; // 1.5x (Position, wage_multiple.xml)
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getWageMultiplier() {
        return wageMultiplier;
    }

    public void setWageMultiplier(int wageMultiplier) {
        this.wageMultiplier = wageMultiplier;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
