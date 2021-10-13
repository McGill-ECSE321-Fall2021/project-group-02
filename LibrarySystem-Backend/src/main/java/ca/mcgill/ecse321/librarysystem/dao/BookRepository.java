package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{
	Book findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
}
