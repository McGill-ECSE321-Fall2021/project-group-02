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
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Patron;

@Service
public class ItemsService {
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
	public Book createBook(String author, String title, Patron patron, boolean isArchived) {
		Item booker = new Book();
		booker.setIsBorrowed(false);
		booker.setIsDamaged(false);
		booker.setIsArchived(isArchived);
		Book book=(Book)booker;
		book.setAuthor(author);
		book.setTitle(title);
		book.setPatron(patron);
		return book;
	}
	
	@Transactional
	public Book getBook(String title, String author) {
		Book book=bookRepository.findBookByTitleAndAuthor(title, author);
		return book;
	}
	
	@Transactional
	public List<Book> getAllBooks(){
		return toList(bookRepository.findAll());
	}
	
	//ADD GET NEWSPAPERS AND JOURNAL
	
	@Transactional 
	public Movie createMovie(String director, String title, Patron patron, boolean isArchived) {
		Item mover = new Movie();
		mover.setIsBorrowed(false);
		mover.setIsDamaged(false);
		mover.setIsArchived(isArchived);
		Movie movie=(Movie)mover;
		movie.setDirector(title);
		movie.setTitle(title);
		movie.setPatron(patron);
		return movie;
	}
	
	@Transactional
	public Movie getMovie(String director, String title) {
		Movie movie=movieRepository.findMovieByTitleAndDirector(title, director);
		return movie;
	}
	
	@Transactional
	public List<Movie> getAllMovies(){
		return toList(movieRepository.findAll());
	}
	
	
	@Transactional 
	public Album createAlbum(String artist, String title, Patron patron, boolean isArchived) {
		Item albumer = new Album();
		albumer.setIsBorrowed(false);
		albumer.setIsDamaged(false);
		albumer.setIsArchived(isArchived);
		Album album=(Album)albumer;
		album.setArtist(artist);
		album.setTitle(title);
		album.setPatron(patron);
		return album;
	}
	
	@Transactional
	public Album getAlbum(String artist, String title) {
		
		Album album=albumRepository.findAlbumByTitleAndArtist(title, artist);
		return album;
	}
	
	@Transactional
	public List<Album> getAllAlbums(){
		return toList(albumRepository.findAll());
	}
	
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	@Transactional
	public Item borrowItem(int itemId, String itemName, int patronId) {
		Patron patronOfInterest;
		if (patronRepository.existsPatronById(patronId)) {
			patronOfInterest= patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron has invalid ID");
		}
		
		if(patronOfInterest.getBorrowedAlbums().size()+patronOfInterest.getBorrowedMovies().size()+patronOfInterest.getBorrowedBooks().size()>=5) {
			throw new IllegalArgumentException("Patron can't borrow because he has already borrowed 5 books");
		}
		
		if(itemRepository.existsItemById(itemId)) {
			Item itemOfInterest=itemRepository.findItemById(itemId); 
			if (!itemOfInterest.getIsArchived()) {
				if(!itemOfInterest.getIsBorrowed()) {
					for(Album a : patronOfInterest.getBorrowedAlbums()) {
						if(a.getTitle().equals(itemName)) {
							List<Album> albums=patronOfInterest.getBorrowedAlbums();
							albums.add(a);
							patronOfInterest.setBorrowedAlbums(albums); 
							patronRepository.save(patronOfInterest);
							return a;
						}
					}
					
					for (Movie m : patronOfInterest.getBorrowedMovies()) {
						if(m.getTitle().equals(itemName)) {
							List<Movie> movies=patronOfInterest.getBorrowedMovies();
							movies.add(m);
							patronOfInterest.setBorrowedMovies(movies);
							patronRepository.save(patronOfInterest);
							return m;
						}
					}
					
					for (Book b : patronOfInterest.getBorrowedBooks()) {
						if(b.getTitle().equals(itemName)) {
							List<Book> books=patronOfInterest.getBorrowedBooks();
							books.add(b);
							patronOfInterest.setBorrowedBooks(books);
							patronRepository.save(patronOfInterest);
							return b;
						}
					}
					
				}
				else {
					throw new IllegalArgumentException("The item you are looking for has already been borrowed.");
				}
			}
			else {
				throw new IllegalArgumentException("The item you are looking for is in the archives");
			}
		}
		throw new IllegalArgumentException("The item you are looking for does not exist.");
	}
}
