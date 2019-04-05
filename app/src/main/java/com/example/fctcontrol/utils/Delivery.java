package com.example.fctcontrol.utils;

import com.example.fctcontrol.dto.BusinessForDialog;
import com.example.fctcontrol.dto.VisitsForDialog;

import java.util.List;

public class Delivery {

    public static String[] deliverDaysArray(List<VisitsForDialog> visits) {
        String[] sDeliver = new String[visits.size()];
        for (int i = 0; i < sDeliver.length; i++) {
            sDeliver[i] = visits.get(i).getVisitDay();
        }
        return sDeliver;
    }

    public static String[] deliverCompaniesArray(BusinessForDialog[] business) {
        String[] sDeliver = new String[business.length];
        for (int i = 0; i < sDeliver.length; i++) {
            sDeliver[i] = business[i].getName();
        }
        return sDeliver;
    }


    private Delivery() {

    }
}
