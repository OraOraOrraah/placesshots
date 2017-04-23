package com.kugiojotaro.placesshots.repository;

import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;

import com.kugiojotaro.placesshots.entity.Fixture;

public interface FixtureQDataTablesRepository extends QDataTablesRepository<Fixture, Long> {
	
}