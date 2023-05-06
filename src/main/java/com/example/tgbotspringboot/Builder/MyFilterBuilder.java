package com.example.tgbotspringboot.Builder;

import com.example.tgbotspringboot.Entity.Filter;
import org.springframework.stereotype.Component;

@Component
public class MyFilterBuilder implements FilterBuilder{
    private String nameVacancy;
    private String nameRegion;
    private String experience;
    private int salary;
    private String requestId;

    @Override
    public void setNameVacancy(String nameVacancy) {
        this.nameVacancy = nameVacancy;
    }

    @Override
    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    @Override
    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Filter getFilter() {
        return new Filter(nameVacancy, nameRegion, experience, salary, requestId);
    }
}
