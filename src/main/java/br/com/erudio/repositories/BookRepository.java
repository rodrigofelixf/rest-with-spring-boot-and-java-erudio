package br.com.erudio.repositories;


import br.com.erudio.model.Book;
import br.com.erudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
