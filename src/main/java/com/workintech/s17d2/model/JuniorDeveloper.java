package com.workintech.s17d2.model;

import com.workintech.s17d2.rest.Experience;

public class JuniorDeveloper extends Developer{
    public JuniorDeveloper(){super(); // Developer içindeki boş constructor'ı çağırır
        this.setExperience(Experience.JUNIOR);}
    public JuniorDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.JUNIOR); // 4 parametreli super'e gönderiyoruz
    }

}
