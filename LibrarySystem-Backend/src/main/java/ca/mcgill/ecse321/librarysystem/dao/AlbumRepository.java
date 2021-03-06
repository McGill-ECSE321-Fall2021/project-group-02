package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Album;

//@RepositoryRestResource()
public interface AlbumRepository extends CrudRepository<Album, Integer>{
	Album findAlbumById(int id);
	Album findAlbumByTitleAndArtist(String title, String artist);
	List<Album> findAlbumByTitle(String title);
	List<Album> findAlbumByArtist(String artist);

	List<Album> findAlbumByIsArchived(boolean isArchived);
	List<Album> findAlbumByIsBorrowed(boolean isBorrowed);
	List<Album> findAlbumByIsDamaged(boolean isDamaged);

	boolean existsByTitleAndArtist(String title, String artist);
}

