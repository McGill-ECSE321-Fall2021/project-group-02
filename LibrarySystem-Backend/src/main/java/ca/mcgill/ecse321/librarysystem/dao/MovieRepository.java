package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Movie;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	Movie findMovieByTitleAndDirector(String title, String directorName);
	Movie findMovieById(int id);
	List<Movie> findMovieByTitle(String title);
	List<Movie> findMovieByDirector(String directorName);

	List<Movie> findMovieByIsArchived(boolean isArchived);
	List<Movie> findMovieByIsBorrowed(boolean isBorrowed);
	List<Movie> findMovieByIsDamaged(boolean isDamaged);

	boolean existsMovieByTitleAndDirector(String title, String directorName);
	boolean existsMovieByTitle(String title);
	boolean existsMovieByDirector(String directorName);
} 
	
