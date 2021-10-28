package ca.mcgill.ecse321.librarysystem.service;

import javax.transaction.Transactional;

import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;

public class ReturnItemService {
	@Transactional
	public Item returnItem(int itemId, String itemName, int patronId) {
		Book book = new Book();
		return book;
	}
}
