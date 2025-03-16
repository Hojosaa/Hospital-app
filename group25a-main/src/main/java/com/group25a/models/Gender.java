package com.group25a.models;

import java.util.ArrayList;
import java.util.List;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Other("Other"),
    PreferNotToSay("PreferNotToSay");
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static String[] genders() {
        List<String> genders = new ArrayList<>();
        var genderlist = Gender.values();
        for (Gender gender : genderlist) {
            genders.add (gender.getGender());
        }
        return genders.toArray(new String[genders.size()]);
    }

    public String getGender() {
        return gender;
    }
}
