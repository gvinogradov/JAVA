package main.repository;

import main.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
