package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.librarysystem.dao.AlbumRepository;
import ca.mcgill.ecse321.librarysystem.dao.BookRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.JournalRepository;
import ca.mcgill.ecse321.librarysystem.dao.MovieRepository;
import ca.mcgill.ecse321.librarysystem.dao.NewspaperRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
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
	JournalRepository journalRepository;
	
	@Autowired 
	MovieRepository movieRepository;
	
	@Autowired 
	NewspaperRepository newspaperRepository;
	
	@Autowired 
	PatronRepository patronRepository;
	
	@Transactional
	public Item returnItem(int itemId, String itemName, int patronId) throws IllegalArgumentException {
		
		// Identify the specific patron
		Patron specificPatron;
		if (patronRepository.existsById(patronId)) {
			specificPatron = patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron ID does not exist."); 
		}
		
		
		int numborrowedItems = specificPatron.getBorrowedAlbums().size() + specificPatron.getBorrowedMovies().size() + specificPatron.getBorrowedBooks().size();
		
		// Remove item from patron's borrowed list by making a copy without the specificItem
		if(itemRepository.existsItemById(itemId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
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
		}
		
		// need if damaged case
		// overdue count?
		
		// filler for now
		Book book = new Book();
		return book;
	}
}
