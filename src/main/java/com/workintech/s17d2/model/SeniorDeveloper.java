package com.workintech.s17d2.model;

import com.workintech.s17d2.rest.Experience;

public class SeniorDeveloper extends Developer{
  public SeniorDeveloper(){super(); // Developer içindeki boş constructor'ı çağırır
      this.setExperience(Experience.SENIOR);}
    public SeniorDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.SENIOR);
    }

}
