package com.blaaxo.RecursosHumanos.controller;

import com.blaaxo.RecursosHumanos.dto.EmpleadoDTO;
import com.blaaxo.RecursosHumanos.model.Empleado;
import com.blaaxo.RecursosHumanos.service.EmpleadoService;
import com.blaaxo.RecursosHumanos.service.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/empleados/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Integer id) {

        //verificamos que el empleado existe
        Empleado empleado = empleadoService.getEmpleadoById(id);

        //si no existe mandamos una exepcion
        if (empleado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El empleado con el id: " + id + " no existe"
            );
        }

        empleadoService.deleteEmpleado(empleado);

        // mapeamos la respuesta del Json {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
