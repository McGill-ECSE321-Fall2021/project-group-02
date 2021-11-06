package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
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
public class TestJournalPersistence {
	@Autowired
	private JournalRepository journalRepository;

	@AfterEach
	public void clearDatabase() {
		journalRepository.deleteAll();
	}
	@Test
	public void testPersistAndLoadJournal() {
		Journal j = new Journal();
		String name = "testJournal";
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, 10, 16));
		j.setDate(date);
		j.setName(name);
		journalRepository.save(j);
		int id = j.getId();
		
		j = null;
		j = journalRepository.findJournalById(id);
		assertNotNull(j);
		assertEquals(name,j.getName());
		assertEquals(date,j.getDate());
		
		j = null;
		j = journalRepository.findJournalByNameAndDate(name, date);
		assertNotNull(j);
		assertEquals(name,j.getName());
		assertEquals(date,j.getDate());
	}
}
