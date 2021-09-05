package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Entity.Detalle;
import com.informatorio.ventasMerchandising.Repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LineaCarritoController {
    @Autowired
    DetalleRepository lineasCarritosRepository;

    @GetMapping(value = "/LineasCarritos")
    public Iterable<Detalle> todosLasLineasCarritos() {
        return lineasCarritosRepository.findAll();
    }
    @GetMapping(value = "/LineasCarritos/{id}")
    public Optional<Detalle> buscarCarritoPorId(@PathVariable("id") Long id) {
        return lineasCarritosRepository.findById(id);
    }

}
