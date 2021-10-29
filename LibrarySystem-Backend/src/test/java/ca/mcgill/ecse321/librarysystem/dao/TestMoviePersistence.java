package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMoviePersistence {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@AfterEach
	public void clearDatabase() {
		movieRepository.deleteAll();
		itemRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadMovie() {
		
		String title = "TestTitle";
		String director = "TestDirector";
		int id = 1;
		Movie movie = new Movie();
		
		movie.setDirector(director);
		movie.setTitle(title);
		movie.setId(id);
		movieRepository.save(movie);
		itemRepository.save(movie);
		
		movie = null;
		
		movie = movieRepository.findMovieById(id);
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
		assertEquals(director, movie.getDirector());
		assertEquals(id, movie.getId());
		
		movie = null;
		
		movie = movieRepository.findMovieByTitleAndDirector(title, director);
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
		assertEquals(director, movie.getDirector());
		assertEquals(id, movie.getId());
		
		movie = null;
		
		movie = (Movie) itemRepository.findItemById(id);
		assertNotNull(movie);
		assertEquals(title, movie.getTitle());
		assertEquals(director, movie.getDirector());
		assertEquals(id, movie.getId());
	}

}
