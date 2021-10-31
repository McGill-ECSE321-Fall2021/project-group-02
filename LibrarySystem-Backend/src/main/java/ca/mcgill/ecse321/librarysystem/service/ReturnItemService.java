package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.librarysystem.dao.AlbumRepository;
import ca.mcgill.ecse321.librarysystem.dao.BookRepository;
import ca.mcgill.ecse321.librarysystem.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.JournalRepository;
import ca.mcgill.ecse321.librarysystem.dao.MovieRepository;
import ca.mcgill.ecse321.librarysystem.dao.NewspaperRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Journal;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Patron;


public class ReturnItemService {
	@Autowired 
	AlbumRepository albumRepository;
	
	@Autowired 
	BookRepository bookRepository;
	
	@Autowired 
	ItemRepository itemRepository;
	
	@Autowired 
	MovieRepository movieRepository;
	
	@Autowired 
	PatronRepository patronRepository;
	
	@Autowired 
	HeadLibrarianRepository headLibrarianRepository;
	
	@Transactional
	public Item returnItem(int itemId, int patronId) throws IllegalArgumentException {
		
		// Identify the specific patron
		Patron specificPatron;
		if (patronRepository.existsById(patronId)) {
			specificPatron = patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron ID does not exist."); 
		}

		if(itemRepository.existsItemById(itemId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
			// Remove item from patron's borrowed list by making a copy without the specificItem
			if (specificItem.getClass() == Album.class) {
				List<Album> copyList = new ArrayList<Album>();
				for (Album a : specificPatron.getBorrowedAlbums()) {
					if (a.getId() != specificItem.getId()) {
						copyList.add(a);
					}
				}
				specificPatron.setBorrowedAlbums(copyList);
				
			} else if (specificItem.getClass() == Book.class) {
				List<Book> copyList = new ArrayList<Book>();
				for (Book b : specificPatron.getBorrowedBooks()) {
					if (b.getId() != specificItem.getId()) {
						copyList.add(b);
					}
				}
				specificPatron.setBorrowedBooks(copyList);
				
			} else if (specificItem.getClass() == Movie.class) {
				List<Movie> copyList = new ArrayList<Movie>();
				for (Movie m : specificPatron.getBorrowedMovies()) {
					if (m.getId() != specificItem.getId()) {
						copyList.add(m);
					}
				}
				specificPatron.setBorrowedMovies(copyList);
			}
			specificItem.setIsBorrowed(false);
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Item ID does not exist.");
		}
	}
	
	// these should be in ItemService.java
	@Transactional
	public Item setDamagedItem(int itemId, int userId) throws IllegalArgumentException {
		
		if (headLibrarianRepository.existsById(userId)) {
			
			Item specificItem = itemRepository.findItemById(itemId); 
			if(itemRepository.existsItemById(itemId)) {
				specificItem.setIsBorrowed(false);
				specificItem.setIsDamaged(true);
			} else {
				throw new IllegalArgumentException("Item ID does not exists.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Head librarian ID does not exist.");
		}
			
	}
	
	@Transactional
	public Item discardItem(int itemId, int userId) throws IllegalArgumentException {
		
	if (headLibrarianRepository.existsById(userId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
			if(itemRepository.existsItemById(itemId)) {
				itemRepository.delete(specificItem);
			} else {
				throw new IllegalArgumentException("Item ID does not exists.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Head librarian ID does not exist.");
		}
		
	}
	
}
