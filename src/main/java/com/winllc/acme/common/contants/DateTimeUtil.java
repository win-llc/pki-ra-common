package com.winllc.acme.common.contants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.systemDefault());

}
