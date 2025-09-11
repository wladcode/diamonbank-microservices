package com.diamondcode.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class AuditData implements Serializable {

    @Setter
    @Getter
    private  String status;

    @Getter
    private final String creator;

    @Getter
    private final LocalDateTime createdDate;

    @Setter
    @Getter
    private  LocalDateTime updatedDate;

    @Setter
    @Getter
    private final LocalDateTime deletedDate;


    public static AuditData dataForCreation(){
        return AuditData.builder()
                .createdDate(LocalDateTime.now())
                .creator("WL_HP")
                .status("A")
                .build();
    }


    public static AuditData dataForDelete(){
        return AuditData.builder().deletedDate(LocalDateTime.now()).status("D").build();
    }

}
