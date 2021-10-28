package ca.mcgill.ecse321.librarysystem.service;

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
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Patron;

@Service
public class LibrarySystemService {
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
	public Item borrowItem(int itemId, String itemName, int patronId) {
		Patron patronOfInterest;
		if (patronRepository.existsById(patronId)) {
			patronOfInterest= patronRepository.findPatronById(patronId);
		}
		else {
			return null; //should be replaced by some error telling us that "patron has invalid ID"
		}
		
		if(patronOfInterest.getBorrowedAlbums().size()+patronOfInterest.getBorrowedMovies().size()+patronOfInterest.getBorrowedBooks().size()>=5) {
			return null; //should be replaced by "Can't borrow because already has 5 books"
		}
		
		if(itemRepository.existsItemById(itemId)) {
			Item itemOfInterest=itemRepository.findItemById(itemId); //should be name not id
			if (!itemOfInterest.getIsArchived()) {
				if(!itemOfInterest.getIsBorrowed()) {
					for(Album a : patronOfInterest.getBorrowedAlbums()) {
						if(a.getTitle().equals(itemName)) {
							List<Album> albums=patronOfInterest.getBorrowedAlbums();
							albums.add(a);
							patronOfInterest.setBorrowedAlbums(albums);
							return a;
						}
					}
					
					for (Movie m : patronOfInterest.getBorrowedMovies()) {
						if(m.getTitle().equals(itemName)) {
							List<Movie> movies=patronOfInterest.getBorrowedMovies();
							movies.add(m);
							patronOfInterest.setBorrowedMovies(movies);
							return m;
						}
					}
					
					for (Book b : patronOfInterest.getBorrowedBooks()) {
						if(b.getTitle().equals(itemName)) {
							List<Book> books=patronOfInterest.getBorrowedBooks();
							books.add(b);
							patronOfInterest.setBorrowedBooks(books);
							return b;
						}
					}
					
				}
				else {
					return null; //error telling us that "item is already borrowed"
				}
			}
			else {
				return null; //error telling us that "item is archived"
			}
		}
		return null; // error telling us that "item does not exist"
	}
}
