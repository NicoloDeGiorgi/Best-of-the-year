package org.lesson.library.controller;



import java.util.List;

import org.lesson.library.model.Book;
import org.lesson.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/books")
public class BookController {
	
	//repository field con autowired per d.i.
	@Autowired
	private BookRepository repository;
	
	@GetMapping()
	public String index(Model model) {
		List<Book> books= repository.findAll();
		//prendo i dati da consegnare a books/index
		
		model.addAttribute("books", books);
		// li inserisco nel modello
		return "/books/index";
	}
	@GetMapping("/findByTitle/{title}")
	public String findByTitle (@PathVariable("title") String title, Model model) {
		model.addAttribute("books", repository.findByTitleContains(title));
		return "/books/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("books", repository.findById(id).get());
		return "/books/show";

}
	@GetMapping("/create")
	public String create (Model model) {
		model.addAttribute("book", new Book());
		return "/books/create";
}
	@PostMapping("/create")
	public String store(
	@Valid @ModelAttribute("book") Book formBook,
	BindingResult bindingResult,
	Model model){
	if(bindingResult.hasErrors()){
	return "/books/create";
	}
	repository.save(formBook);
	return "redirect:/books";
}
}
