package ru.springboot.app.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springboot.app.library.model.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}
