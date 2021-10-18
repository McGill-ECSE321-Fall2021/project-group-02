package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAlbumPersistence {
@Autowired
private AlbumRepository albumRepository;
@Autowired
private LibraryRepository libraryRepository;
@AfterEach
public void clearDatabase() {
	albumRepository.deleteAll();
	libraryRepository.deleteAll();
}
	@Test
	public void testPersistAndLoadAlbum() {
		Library l = new Library();
		LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
		ls.setOpeningHours(l);
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		l.setOpeningHour(startTime);
		l.setClosingHour(endTime);
		l.setLibrarySoftwareSystem(ls);
		libraryRepository.save(l);
		Album a = new Album();
		String title = "testTitle";
		String artist = "testArtist";
		a.setArtist(artist);
		a.setTitle(title);
		a.setId(1);
		a.setLibrarySoftwareSystem(ls);
		albumRepository.save(a);
		
		a = null;
		a = albumRepository.findAlbumById(1);
		assertNotNull(a);
		assertEquals(title,a.getTitle());
		assertEquals(artist,a.getArtist());
		assertEquals(1,a.getId());
		
		a = null;
		a = albumRepository.findAlbumByTitleAndArtist(title, artist);
		assertNotNull(a);
		assertEquals(title,a.getTitle());
		assertEquals(artist,a.getArtist());
		assertEquals(1,a.getId());
	}
}
