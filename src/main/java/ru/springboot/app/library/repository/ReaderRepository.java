package ru.springboot.app.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springboot.app.library.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
