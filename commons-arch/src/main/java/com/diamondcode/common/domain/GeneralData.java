package com.diamondcode.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@SuperBuilder
@AllArgsConstructor
public class GeneralData implements Serializable {

    private  final Long id;
    private  final String name;
    private  final String description;


}
