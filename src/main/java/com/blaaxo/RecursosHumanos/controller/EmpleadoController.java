package com.blaaxo.RecursosHumanos.controller;

import com.blaaxo.RecursosHumanos.dto.EmpleadoDTO;
import com.blaaxo.RecursosHumanos.model.Empleado;
import com.blaaxo.RecursosHumanos.service.EmpleadoService;
import com.blaaxo.RecursosHumanos.service.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rh-api")
@CrossOrigin(value = "http://localhost:5173")
public class EmpleadoController {

    private  static final Logger logger =
            LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService empleadoService;

    // http://localhost:8080/rh-api/empleados
    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {

        // obtenemos todos los empleados
        var empleados = empleadoService.getAllEmpleados();

        empleados.forEach((empleado -> logger.info(empleado.toString())));

        return empleados;
    }

    @PostMapping("/empleados/create")
    public Empleado create(@RequestBody Empleado empleado) {
        empleadoService.createEmpleado(empleado);
        return empleado;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> show(@PathVariable Integer id){
        Empleado empleado = empleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/update/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoService.updateEmpleado(id, empleadoDTO);
        return ResponseEntity.ok(empleado);
    }
}
