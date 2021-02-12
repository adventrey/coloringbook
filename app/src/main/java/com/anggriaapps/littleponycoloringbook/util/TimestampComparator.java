package com.anggriaapps.littleponycoloringbook.util;

import com.anggriaapps.littleponycoloringbook.model.bean.LocalImageBean;

import java.util.Comparator;

public class TimestampComparator implements Comparator<LocalImageBean> {
    @Override
    public int compare(LocalImageBean localImageBean, LocalImageBean t1) {
        long diff = t1.getLastModTimeStamp() - localImageBean.getLastModTimeStamp();
        if (diff > 0)
            return 1;
        if (diff < 0)
            return -1;
        else
            return 0;
    }
}
