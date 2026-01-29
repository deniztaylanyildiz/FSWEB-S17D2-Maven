package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers") // Test /workintech kısmını context-path'den alıyor
public class DeveloperController {

    // Test dosyası bu değişkenin "public" veya erişilebilir olmasını bekliyor olabilir
    public Map<Integer, Developer> developers;
    private final Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        this.developers = new HashMap<>();
    }

    @GetMapping
    public List<Developer> getAll() {
        return new ArrayList<>(developers.values());
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable int id) {
        return developers.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Test status().isCreated() bekliyor
    public Developer save(@RequestBody Developer developer) {
        Developer savedDeveloper;
        double salary = developer.getSalary();

        // Tecrübe tipine göre vergi düşümü
        if (developer.getExperience() == Experience.JUNIOR) {
            salary -= (salary * taxable.getSimpleTaxRate() / 100);
            savedDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), salary);
        } else if (developer.getExperience() == Experience.MID) {
            salary -= (salary * taxable.getMiddleTaxRate() / 100);
            savedDeveloper = new MidDeveloper(developer.getId(), developer.getName(), salary);
        } else if (developer.getExperience() == Experience.SENIOR) {
            salary -= (salary * taxable.getUpperTaxRate() / 100);
            savedDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), salary);
        } else {
            savedDeveloper = developer;
        }

        developers.put(savedDeveloper.getId(), savedDeveloper);
        return savedDeveloper;
    }

    @PutMapping("/{id}")
    public Developer update(@PathVariable int id, @RequestBody Developer developer) {
        developer.setId(id);
        developers.put(id, developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public Developer delete(@PathVariable int id) {
        return developers.remove(id);
    }
}