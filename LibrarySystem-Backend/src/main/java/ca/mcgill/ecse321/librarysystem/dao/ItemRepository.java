package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{
	Item findItemById(Integer id);

	List<Item> findItemByIsArchived(boolean isArchived);
	List<Item> findItemByIsBorrowed(boolean isBorrowed);
	List<Item> findItemByIsDamaged(boolean isDamaged);

	boolean existsItemById(Integer id);
}
