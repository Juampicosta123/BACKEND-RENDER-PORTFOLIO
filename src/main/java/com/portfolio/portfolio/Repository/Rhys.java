package com.portfolio.portfolio.Repository;

import com.portfolio.portfolio.Entity.hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rhys extends JpaRepository<hys, Integer>{
    public Optional<hys> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
