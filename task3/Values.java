package com.perfomanceLab.task3;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class Values {
    public List<ValuesEntity> values;

    public Values(){};
}
