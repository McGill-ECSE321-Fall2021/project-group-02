package ca.mcgill.ecse321.librarysystem.service;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;



@ExtendWith(MockitoExtension.class)
public class TestBorrowItemsService {
	
	@Mock
	private PatronRepository patronDao;
	
	@Mock
	private ItemRepository itemDao;
	
	@InjectMocks
	private ItemService service;
	
	private static final int EXISTINGPERSON_ID = 0;
	private static final int NONEXISTINGPERSON_ID = -1;

	/*
	@BeforeEach
	public void setMockOutput() {
		lenient().when(patronDao.findPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				
			}
			
		});
	}
	*/
	
}




















