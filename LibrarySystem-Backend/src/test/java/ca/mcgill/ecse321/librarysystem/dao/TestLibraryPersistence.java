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
public class TestLibraryPersistence {
	@Autowired
	private LibraryRepository libraryRepository;
	
@AfterEach
public void clearDatabse() {
	libraryRepository.deleteAll();
}
@Test
public void testPersistAndLoadLibary() {
	Library l = new Library();
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
	Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
	Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
	l.setOpeningHour(startTime);
	l.setClosingHour(endTime);
	l.setLibrarySoftwareSystem(ls);
	libraryRepository.save(l);
	
	l = null;
	l = libraryRepository.findLibraryByClosingHour(endTime);
	assertNotNull(l);
	assertEquals(startTime,l.getOpeningHour());
	assertEquals(endTime,l.getClosingHour());
	
	l = null;
	l = libraryRepository.findLibraryByOpeningHour(startTime);
	assertEquals(endTime,l.getClosingHour());
	assertEquals(startTime,l.getOpeningHour());
}
}
