package com.diamoncode.diamonbank.accounts.controller.dto;


import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertiesDto {

    private String msg;
    private String buildVersion;
    private Map<String, String> mailDetails;
    private List<String> activeBranches;

    public PropertiesDto(String msg, String buildVersion, Map<String, String> mailDetails, List<String> activeBranches) {
        this.msg = msg;
        this.buildVersion = buildVersion;
        this.mailDetails = mailDetails;
        this.activeBranches = activeBranches;
    }

}