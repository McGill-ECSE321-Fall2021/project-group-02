package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Movie;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	Movie findMovieByNameAndDirector(String name, String directorName);
	Movie findMovieById(int id);
	List<Movie> findMovieByName(String name);
	List<Movie> findMovieByDirector(String directorName);

	List<Movie> findMovieByIsArchived(boolean isArchived);
	List<Movie> findMovieByIsBorrowed(boolean isBorrowed);
	List<Movie> findMovieByIsDamaged(boolean isDamaged);

	boolean existsMovieByNameAndDirector(String name, String directorName);
	boolean existsMovieByName(String name);
	boolean existsMovieByDirector(String directorName);
} 
	
