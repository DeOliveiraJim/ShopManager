package com.manager.shopmanager.validation;

import java.time.DayOfWeek;
import java.util.*;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.manager.shopmanager.entity.OpeningTime;

public class OpeningTimesValidator implements ConstraintValidator<ValidOpeningTimes, Collection<OpeningTime>> {
    private final static Pattern HOUR_REGEX = Pattern.compile("([0-1]?\\d|2[0-3]):[0-5]\\d");

    private class StartEnd {
        private int start;

        private int end;

        public StartEnd(String start, String end) {
            String[] s = start.split(":");
            String[] e = end.split(":");
            this.start = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            this.end = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    @Override
    public boolean isValid(Collection<OpeningTime> value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        Map<DayOfWeek, List<StartEnd>> timesPerDay = new HashMap<>();
        for (OpeningTime ot : value) {
            // true si c'est mal formé car les erreurs de regex sont géré par OpeningTime
            if (!HOUR_REGEX.matcher(ot.getStart()).find())
                return true;
            if (!HOUR_REGEX.matcher(ot.getEnd()).find())
                return true;
            for (DayOfWeek d : ot.getDays()) {
                if (timesPerDay.get(d) == null)
                    timesPerDay.put(d, new LinkedList<>());

                List<StartEnd> startEnds = timesPerDay.get(d);
                StartEnd se = new StartEnd(ot.getStart(), ot.getEnd());
                if (!isValid(startEnds, se))
                    return false;
                startEnds.add(se);
            }
        }
        return true;
    }

    private boolean isValid(Collection<StartEnd> startEnds, StartEnd se) {
        if (se.getStart() >= se.getEnd())
            return false;
        for (StartEnd x : startEnds) {
            if ((x.getStart() >= se.getStart() && x.getStart() <= se.getEnd())
                    || (x.getEnd() >= se.getStart() && x.getEnd() <= se.getEnd())
                    || (se.getStart() >= x.getStart() && se.getStart() <= x.getEnd())) {
                return false;
            }
        }
        return true;
    }

}
