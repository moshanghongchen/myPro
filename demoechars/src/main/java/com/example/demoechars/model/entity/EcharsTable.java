package com.example.demoechars.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Repository
public class EcharsTable implements Serializable,Comparable<EcharsTable>{
    public static final String TYPE_YD="运动";
    public static final String TYPE_TZ="体重";

    private Integer id;

    private String name;

    private String type;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyyMMdd")
    private Date date;

    private String data;

    private ArrayList<String> datas=new ArrayList<>();
    private Map<String,Integer> dataMap=new LinkedHashMap<>();

    private LinkedHashSet<String> dates=new LinkedHashSet<>();

    public EcharsTable(String name) {
        this.name=name;
    }
    public EcharsTable() {
    }

   /**
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(EcharsTable o) {
        if (this.getDate().getTime()>o.getDate().getTime()){
            return 1;
        }
        return -1;
    }
}