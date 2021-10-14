package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, String>{
	List<HeadLibrarian> findHeadLibrarianByFirstName(String firstName);
    List<HeadLibrarian> findHeadLibrarianByLastName(String lastName);
    HeadLibrarian findHeadLibrarianByFirstAndLastName(String firstName, String lastName);
    HeadLibrarian findHeadLibrarianByID(Integer iD);

    boolean existsHeadLibrarianByFirstName(String firstName);
    boolean existsHeadLibrarianByLastName(String lastName);
    boolean existsHeadLibrarianByFirstAndLastName(String firstName, String lastName);
    boolean existsHeadLibrarianByID(Integer iD);
}
