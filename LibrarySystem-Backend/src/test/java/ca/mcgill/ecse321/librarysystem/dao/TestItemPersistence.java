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
public class TestItemPersistence {
@Autowired
private ItemRepository itemRepository;
@AfterEach
public void clearDatabase() {
	itemRepository.deleteAll();
}
@Test
public void testPersistAndLoadItem() {
	Library l = new Library();
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
	Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
	Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
	l.setOpeningHour(startTime);
	l.setClosingHour(endTime);
	l.setLibrarySoftwareSystem(ls);
	Item i = new Item();
	i.setIsBorrowed(true);
	i.setIsDamaged(true);
	i.setId(20);
	i.setLibrarySoftwareSystem(ls);
	itemRepository.save(i);
	
	i = null;
	i = itemRepository.findItemById(20);
	assertNotNull(i);
	assertEquals(20,i.getId());
	assertEquals(false,i.getIsArchived());
	assertEquals(true,i.getIsBorrowed());
	assertEquals(true,i.getIsDamaged());
}
}
