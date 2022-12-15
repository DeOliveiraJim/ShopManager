package com.manager.shopmanager.entity;

import java.time.DayOfWeek;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnCreateValidation;
import com.manager.shopmanager.entity.interfaces.ValidationGroups.OnPatchValidation;

@Entity
public class OpeningTime {
    private final static String HOUR_REGEX = "([0-1]?\\d|2[0-3]):[0-5]\\d";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @ElementCollection
    @Enumerated
    @NotEmpty(groups = { OnCreateValidation.class, OnPatchValidation.class })
    private Collection<DayOfWeek> days;

    @NotEmpty
    @Pattern(regexp = HOUR_REGEX)
    private String start;

    @NotEmpty
    @Pattern(regexp = HOUR_REGEX)
    private String end;

    public Collection<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(Collection<DayOfWeek> days) {
        this.days = days;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OpeningTime [id=" + id + ", days=" + days + ", start=" + start + ", end=" + end + "]";
    }

}
