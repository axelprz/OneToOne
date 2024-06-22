package com.uch.bd2.onetoone.controllers;

import com.uch.bd2.onetoone.models.Empleado;
import com.uch.bd2.onetoone.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ArrayList<Empleado> getEmpleados() {
        return this.empleadoService.getEmpleados();
    }

    @GetMapping(path = "/{id}")
    public Optional<Empleado> getEmpleadoById(@PathVariable Long id) {
        return this.empleadoService.getById(id);
    }

    @PostMapping
    public Empleado create(@RequestBody Empleado empleado) {
        if (empleado.getUsuario() != null) {
            empleado.getUsuario().setEmpleado(empleado);
        }
        return empleadoService.saveEmpleado(empleado);
    }

    @PutMapping(path = "/{id}")
    public Empleado updateEmpleadoById(@RequestBody Empleado request, @PathVariable Long id) {
        return this.empleadoService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.empleadoService.deleteEmpleado(id);

        if (ok) {
            return "Empleado con id " + id + " eliminado!";
        }
        return "Error, no se pudo eliminar el empleado con id " + id;
    }
}
