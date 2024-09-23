package org.lesson.library.repo;

import org.lesson.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer> {

	Object findByTitleContains(String title);



	//In automatico ho tutto il necessario, ma posso aggiungere
	//eventuali metodi e funzionalità
}
