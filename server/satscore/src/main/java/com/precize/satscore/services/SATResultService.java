package com.precize.satscore.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precize.satscore.entitities.SATResult;
import com.precize.satscore.repos.SATResultRepository;

import jakarta.transaction.Transactional;

@Service
public class SATResultService {
    @Autowired
    private SATResultRepository repository;

    public SATResult insertSATResult(SATResult satResult) {
        // Calculate "Passed" based on SAT score
        satResult.setPassed(satResult.getSatScore() > 30);
        return repository.save(satResult);
    }

    public Iterable<SATResult> getAllSATResults() {
        return repository.findAll();
    }

    public Integer getRank(String name) {
        List<SATResult> students= new ArrayList<>();
        repository.findAll().forEach(students::add);

        students.sort(Comparator.comparingInt(SATResult::getSatScore).reversed());

        int rank = 1;
        for (SATResult student : students) {
            if (student.getName().equals(name)) {
                return rank;
            }
            rank++;
        }
        return null;

    }

    public SATResult updateSATResult(String name, int newScore) {
        SATResult satResult = repository.findByName(name);
        if (satResult != null) {
            satResult.setSatScore(newScore);
            satResult.setPassed(newScore > 30);
            return repository.save(satResult);
        }
        return null;
    }

    @Transactional
    public void deleteSATResult(String name) {
        repository.deleteByName(name);
    }
}

