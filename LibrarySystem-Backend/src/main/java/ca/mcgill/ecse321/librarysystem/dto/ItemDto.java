package ca.mcgill.ecse321.librarysystem.dto;

public class ItemDto {

	private int id;
	private boolean isArchived;
	private boolean isBorrowed;
	private boolean isDamaged;
	
	public ItemDto() {
		
	}
	
	public ItemDto(int id) {
		this.id = id;
	}
	
	public Integer getID() {
		return id;
	}
	
	public boolean isArchived() {
		return isArchived;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
	
	public boolean isDamaged() {
		return isDamaged;
	}
	
}
