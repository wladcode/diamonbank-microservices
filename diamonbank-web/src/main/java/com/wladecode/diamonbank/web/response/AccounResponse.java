package com.wladecode.diamonbank.web.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public record AccounResponse (
     long id,
     long customerId,
     String number,
     String type,
     BigDecimal balance,
     LocalDateTime createDt,
     String branchAddress
) {}

