package com.cg.bookStore.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.bookStore.entities.BookInformation;
import com.cg.bookStore.exceptions.BookException;
import com.cg.bookStore.service.ManageBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**************************************************************************************************
 *          @author         Rohita, Aishwarya, Amardeep, Sachin
 *          Description      It is a controller class having Request Mapping functions
 *          				 for functionalities of Manage books.
 *          Version             2.0
 *          Created Date    17-July-2020
 **************************************************************************************************/
@RestController
public class ManageBookController {

	@Autowired
	private ManageBookService bookStoreService;
	
	/*******************************************
	 * Method: deleteBook
     *Description: To take a delete request to delete book, return message.. 
	 * @param name              - bookId.
	 * @returns shows(String)   - Message that book deleted or not
	 * @throws BookException - When that book does not exists in database, exception is thrown. 
                *Created By                              - Aishwarya srivastava
                *Created Date                            - 17-Jul-2020                           	 
	 ********************************************/
	@DeleteMapping("/manageBook/delete/{bookId}")
	public String deleteBook(@PathVariable(name="bookId") int bookId) throws BookException {
		return bookStoreService.deleteBook(bookId);
	}
	
	/*******************************************
	 * Method: createBook
     *Description: To take a post request to create book, return message.. 
	 * @param name              - book.
	 * @returns shows(String)   - Message that book added or not
	 * @throws BookException - When that book does already exists in database, exception is thrown. 
                *Created By                              - Rohita Rani
                *Created Date                            - 17-Jul-2020                           	 
	 * @throws IOException 
	 ********************************************/
	@PostMapping("/manageBook/create")
	public String createBook(
			@RequestParam ("image") MultipartFile file,
	@RequestBody BookInformation book)throws BookException, IOException {
		/*BookInformation bookInfo=null;
		try {
			bookInfo= new ObjectMapper().getValuesAs(file,BookInformation.class);
		}
		catch (Exception e) {
	            e.printStackTrace();
	        }*/
		
		return bookStoreService.createBook(book, file);
	}
	
	
	/*******************************************
	 * Method: updateBook
     *Description: To take a update request to update book, return message.. 
	 * @param name              - book.
	 * @returns shows(String)   - Message that book update or not
	 * @throws BookException - When that book does already exists/ or not in database, exception is thrown. 
                *Created By                              - Sachin kumar
                *Created Date                            - 17-Jul-2020                           	 
	 /********************************************/
	@PostMapping("/manageBook/update")
	public String updateBook(@RequestBody  BookInformation book) throws BookException {
		return bookStoreService.updateBook(book);
	}
	
	/*******************************************
	 * Method: displayBooks
     *Description: To take a get request to display all books in database. 
	 * @returns List<BookInformation>   - List of all books in database
	 * @throws BookException - When no book  exists in database, exception is thrown. 
                *Created By                              - Amardeep
                *Created Date                            - 17-Jul-2020                           	 
	 ********************************************/
	@GetMapping("/manageBook/display")
	public List<BookInformation> displayBooks() throws BookException {
		return bookStoreService.displayBooks();
	}
}