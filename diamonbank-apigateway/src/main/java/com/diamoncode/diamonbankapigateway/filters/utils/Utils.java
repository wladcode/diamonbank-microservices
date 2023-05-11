package com.diamoncode.diamonbankapigateway.filters.utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utils {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
}
