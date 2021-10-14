package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, String>{
	Item findById(Integer id);

	List<Item> findItemByIsArchived(boolean isArchived);
	List<Item> findItemByIsBorrowed(boolean isBorrowed);
	List<Item> findItemByIsDamaged(boolean isDamaged);

	boolean existsItemById(Integer id);
	boolean existsItemByTitle(String title);
}
