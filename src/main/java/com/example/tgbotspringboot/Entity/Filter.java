package com.example.tgbotspringboot.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Filter {
    private final String nameVacancy;
    private final String nameRegion;
    private final String experience;
    private final int salary;
    private final String requestId;

    @Override
    public String toString() {
        return "Filter{" +
                "nameVacancy='" + nameVacancy + '\'' +
                ", nameRegion='" + nameRegion + '\'' +
                ", experience='" + experience + '\'' +
                ", salary=" + salary +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
