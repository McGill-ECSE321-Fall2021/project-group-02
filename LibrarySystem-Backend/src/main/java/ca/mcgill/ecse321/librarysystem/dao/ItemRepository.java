package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, String>{
	Item findByID(Integer iD);
	List<Item> findItemByType(boolean isAlbum, boolean isBook, boolean isJournal, boolean isMovie, boolean isNewspaper);
	List<Item> findItemByTitle(String title);

	List<Item> findItemByIsBorrowable(boolean isNotArchived, boolean isNotBorrowed, boolean isNotDamaged);
	List<Item> findItemByIsArchived(boolean isArchived);
	List<Item> findItemByIsBorrowed(boolean isBorrowed);
	List<Item> findItemByIsDamaged(boolean isDamaged);

	boolean existsItemByID(Integer iD);
	boolean existsItemByTitle(String title);
}
