package com.cko.sampleSpringProject.dao;

import com.cko.sampleSpringProject.model.Films;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmDAO extends CrudRepository<Films, Long> {
Films findFilmById(Long id);
List<Films> findAll();

}
