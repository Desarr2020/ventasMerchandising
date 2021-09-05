package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Entity.*;
import com.informatorio.ventasMerchandising.Repository.CarritoRepository;
import com.informatorio.ventasMerchandising.Repository.OrdenRepository;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/Orden")
    public List<Orden> verOrden(){
        return ordenRepository.findAll();
    }


    @GetMapping(value = "/orden/{id}")
    public Orden verOrden(@PathVariable("id") Long id){
        return ordenRepository.getById(id);
    }

    @PostMapping(value = "/ordenadd/{id_carrito}")
    public Orden crearOrden(@PathVariable("id_carrito") Long id_carrito,@RequestBody Orden orden){
        Carrito carrito = carritoRepository.getById(id_carrito);
        if (carrito.getEstado()) {
            orden.setCarrito_id(id_carrito);
            orden.setEstado(Estado.Confirmada);
            orden.setUsuario(carrito.getUsuario());
            List<Detalle> detalles_del_carrito = carrito.getDetalle();
            for  (Detalle d : detalles_del_carrito) {
                Linea l = new Linea();
                l.setProducto_id(d.getProducto().getId());
                l.setCantidad(d.getCantidad());
                l.setPrecio(d.getProducto().getPrecio_unitario());
                l.setOrden(orden);
                orden.addLinea(l);
                ordenRepository.save(orden);
            }
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return ordenRepository.save(orden);
        }
        return null;
    }

    @PutMapping(value = "usuario/{id_usuario}/orden/{id_carrito}/close")
    public Orden cancelarOrden(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_usuario") Long id_usuario){
        Orden orden = ordenRepository.getById(id_carrito);
        Usuario usuario = usuarioRepository.getById(id_usuario);
        if ((usuario.getRol()==Rol.Comerciante) && (orden.getEstado()==Estado.Confirmada)){
            orden.setEstado(Estado.Cancelada);
            return ordenRepository.save(orden);
        }
        return null;
    }
}
