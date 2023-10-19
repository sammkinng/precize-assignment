package com.precize.satscore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.precize.satscore.entitities.SATResult;
import com.precize.satscore.services.SATResultService;

@RestController
@RequestMapping("/api/sat-results")
public class SATResultController {
    @Autowired
    private SATResultService service;

    @PostMapping("/insert")
    public SATResult insertSATResult(@RequestBody SATResult satResult) {
        return service.insertSATResult(satResult);
    }

    @GetMapping("/view-all")
    public Iterable<SATResult> getAllSATResults() {
        return service.getAllSATResults();
    }

    @GetMapping("/get-rank/{name}")
    public Integer getRank(@PathVariable String name) {
        return service.getRank(name);
    }

    @PutMapping("/update-score/{name}")
    public SATResult updateSATResult(@PathVariable String name, @RequestParam int newScore) {
        return service.updateSATResult(name, newScore);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteSATResult(@PathVariable String name) {
        service.deleteSATResult(name);
    }
}

