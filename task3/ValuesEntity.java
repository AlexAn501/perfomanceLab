package com.perfomanceLab.task3;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ValuesEntity {
    public int id;
    public String value;

    @Override
    public String toString() {
        return "ValueEntity{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
