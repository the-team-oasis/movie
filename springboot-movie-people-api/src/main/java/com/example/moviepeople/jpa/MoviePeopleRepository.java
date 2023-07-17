package com.example.moviepeople.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviepeople.repository.entity.Movie_People;

@Repository
public interface MoviePeopleRepository extends JpaRepository<Movie_People, Integer> {
    public Optional<Movie_People> findById(String id);
    public Optional<Movie_People> findByName(String name);
    public Page<Movie_People> findByFilmographyContains(Pageable pageable, String filmography);
}