package com.blaaxo.RecursosHumanos.service;

import com.blaaxo.RecursosHumanos.dto.EmpleadoDTO;
import com.blaaxo.RecursosHumanos.model.Empleado;
import com.blaaxo.RecursosHumanos.repository.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Empleado getEmpleadoById(Integer id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "El empleado con el id: " + id + " no existe"
                ));

        return empleado;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado createEmpleado(Empleado empleado) {

        // Validar si este empleado existe
//        Optional<Empleado> empleadoOptional = empleadoRepository.findByNombre(empleado.getNombre());
//
//        if (empleadoOptional.isPresent()) {
//            throw new IllegalStateException("El empleado existe");
//        }

        // guarda al usuario
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmpleado(Integer id, EmpleadoDTO empleadoDTO) {

        // Validar si este empleado existe
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "El empleado con el id: " + id + " no existe"
                ));

        // mapeamos y guardamos los cambios del empleado
        modelMapper.map(empleadoDTO, empleado);

        empleadoRepository.save(empleado);

        return empleado;
    }

    @Override
    public void deleteEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
}
