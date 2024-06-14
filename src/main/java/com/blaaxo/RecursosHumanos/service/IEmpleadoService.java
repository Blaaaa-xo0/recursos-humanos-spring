package com.blaaxo.RecursosHumanos.service;

import com.blaaxo.RecursosHumanos.dto.EmpleadoDTO;
import com.blaaxo.RecursosHumanos.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public Empleado getEmpleadoById(Integer id);

    public List<Empleado> getAllEmpleados();

    public Empleado createEmpleado(Empleado empleado);

    public Empleado updateEmpleado(Integer id, EmpleadoDTO empleadoDTO);

    public void deleteEmpleado(Empleado empleado);

}
