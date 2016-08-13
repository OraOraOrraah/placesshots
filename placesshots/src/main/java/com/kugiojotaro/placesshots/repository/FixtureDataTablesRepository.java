package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.kugiojotaro.placesshots.entity.Fixture;

public interface FixtureDataTablesRepository extends DataTablesRepository<Fixture, Long> {
	
}