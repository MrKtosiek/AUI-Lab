package org.example.Lab4.repository;

import org.example.Lab4.model.SimplifiedBookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedBookGenreRepository extends JpaRepository<SimplifiedBookGenre, UUID>
{
}
