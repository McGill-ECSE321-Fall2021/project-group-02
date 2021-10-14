package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{
	List<Book> findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
	Book findBookByTitleAndAuthor(String title, String author);

	List<Book> findBookByIsArchived(boolean isArchived);
	List<Book> findBookByIsBorrowed(boolean isBorrowed);
	List<Book> findBookByIsDamaged(boolean isDamaged);

	boolean existsByTitleAndAuthor(String title, String author);	
	boolean existsBookByTitle(String title);
	boolean existsBookByAuthor(String author);

}
