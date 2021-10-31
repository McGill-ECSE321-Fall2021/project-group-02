package ca.mcgill.ecse321.librarysystem.dao;

import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Integer>{
    HeadLibrarian findHeadLibrarianById(Integer id);
    List<HeadLibrarian> findHeadLibrarianByAddress(String address);
    
    boolean existsHeadLibrarianById(Integer id);
    boolean existsHeadLibrarianByAddress(String address);
}
