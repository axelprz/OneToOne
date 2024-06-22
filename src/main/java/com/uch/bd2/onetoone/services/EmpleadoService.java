package com.uch.bd2.onetoone.services;

import com.uch.bd2.onetoone.models.Empleado;
import com.uch.bd2.onetoone.models.Usuario;
import com.uch.bd2.onetoone.repositories.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public ArrayList<Empleado> getEmpleados() {
        return (ArrayList<Empleado>) empleadoRepository.findAll();
    }

    public Optional<Empleado> getById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado updateById(Empleado request, Long id) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            empleado.setNombre(request.getNombre());
            empleado.setApellido(request.getApellido());
            empleado.setPuesto(request.getPuesto());

            Usuario usuario = empleado.getUsuario();
            if (usuario != null) {
                usuario.setUsername(request.getUsuario().getUsername());
                usuario.setEmail(request.getUsuario().getEmail());
            }
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    public boolean deleteEmpleado(Long id) {
        try {
            empleadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
