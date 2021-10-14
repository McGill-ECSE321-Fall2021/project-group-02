package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	Movie findMovieByNameAndDirector(String name, String directorName);
	List<Movie> findMovieByName(String name);
	List<Movie> findMovieByDirector(String directorName);

	List<Movie> findMovieByIsArchived(boolean isArchived);
	List<Movie> findMovieByIsBorrowed(boolean isBorrowed);
	List<Movie> findMovieByIsDamaged(boolean isDamaged);

	boolean existsovieByNameAndDirector(String name, String directorName);
	boolean existsMovieByName(String name);
	boolean existsMovieByDirector(String directorName);
} 
	
