package com.portfolio.portfolio.Security.Repository;

import com.portfolio.portfolio.Security.Entity.Rol;
import com.portfolio.portfolio.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
   Optional<Rol> findByRolNombre(RolNombre rolNombre); 
}
