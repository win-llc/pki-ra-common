package com.winllc.acme.common.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.systemDefault());

    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

}
