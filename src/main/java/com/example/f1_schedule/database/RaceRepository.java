package com.example.f1_schedule.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends CrudRepository<RaceRecord, Long> {
    List<RaceRecord> findAll();
    boolean existsByCountryIgnoreCase(String country);
    RaceRecord findByCountryIgnoreCase(String country);
}
