package com.blaaxo.RecursosHumanos.repository;

//import org.springframework.data.repository.CrudRepository;

import com.blaaxo.RecursosHumanos.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Optional<Empleado> findByNombre(String nombre);
}
