package com.workintech.s17d2.model;

import com.workintech.s17d2.rest.Experience;

public class MidDeveloper extends  Developer{
  public MidDeveloper(){super(); // Developer içindeki boş constructor'ı çağırır
      this.setExperience(Experience.MID);}
    public MidDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.MID);
    }

}
