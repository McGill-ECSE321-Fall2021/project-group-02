package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Item;

public interface ItemRepository extends CrudRepository<Item, String>{
	Item findById(Integer Id);
	List<Item> findByIsArchived(boolean isArchived);
	List<Item> findByIsBorrowed(boolean isBorrowed);
	List<Item> findByIsDamaged(boolean isDamaged);
}
