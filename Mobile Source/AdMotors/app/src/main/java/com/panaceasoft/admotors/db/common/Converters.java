package com.panaceasoft.admotors.db.common;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * Created by Panacea-Soft on 12/27/17.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
