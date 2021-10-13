package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	Movie findMovieByName(String name);
} 
	
