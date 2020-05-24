package com.example.mytableview;
import android.os.Build;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class TableModal {
    private int rank;
    private String title;
    private String subtitle;
    private Date date;
    private int priority;
    private boolean isSelected;
    private boolean rowHeader;
    private String type;


    public TableModal(int rank, String title, String subtitle, Date date, int priority, boolean isSelected, String type) {
        this.rank = rank;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.priority = priority;
        this.isSelected = isSelected;
        this.type = type;
        this.rowHeader = false;
    }
    public TableModal() {
        this.rowHeader = true;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day+":"+month+":"+year;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isRowHeader() {
        return rowHeader;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public static List<TableModal> createTableModal(int from,int num){
        List<TableModal> datalist = new ArrayList<>();
        TableModal tableModal1 = new TableModal();
        datalist.add(tableModal1);
        for(int i=from+1;i<=num+from;i++)
        {
            int year = 1100 + (int)(i/10);
            int month = 1+(i*10+7)%12;
            int day =  1+(i*i)%29;
            Date date = new Date(year,month,day);
            TableModal tableModal = new TableModal(i,"A"+i,"a"+2*i, date, 1+(i*6)%7, true,"abc");
            datalist.add(tableModal);
        }

        return datalist;
    }

}
