package com.precize.satscore.repos;

import org.springframework.data.repository.CrudRepository;

import com.precize.satscore.entitities.SATResult;

public interface SATResultRepository extends CrudRepository<SATResult,Long> {
    SATResult findByName(String name);
    void deleteByName(String name);
}
