package org.example.Lab5.repository;

import org.example.Lab5.model.SimplifiedBookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedBookGenreRepository extends JpaRepository<SimplifiedBookGenre, UUID>
{
}
