package com.latour.corporative.country.repository;

import com.latour.corporative.country.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Alioth Latour
 * @datetime 4/30/2021 1:17 PM
 */

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {
	
	@Query("{'uuid' : ?0}")
	Optional<Country> findBy(String uuid);
	
	Page<Country> findAll(Pageable pageable);
	
}
