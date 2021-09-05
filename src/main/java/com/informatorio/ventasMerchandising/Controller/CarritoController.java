package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Entity.Carrito;
import com.informatorio.ventasMerchandising.Entity.Detalle;
import com.informatorio.ventasMerchandising.Entity.Producto;
import com.informatorio.ventasMerchandising.Repository.CarritoRepository;
import com.informatorio.ventasMerchandising.Repository.DetalleRepository;
import com.informatorio.ventasMerchandising.Repository.ProductoRepository;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarritoController {
    private  final UsuarioRepository usuariosRepository;
    private  final CarritoRepository carritosRepository;
    private  final ProductoRepository productosRepository;
    private final DetalleRepository detallesRepository;

    public CarritoController(UsuarioRepository usuariosRepository, CarritoRepository carritosRepository, ProductoRepository productosRepository, DetalleRepository detallesRepository) {
        this.usuariosRepository = usuariosRepository;
        this.carritosRepository = carritosRepository;
        this.productosRepository = productosRepository;
        this.detallesRepository = detallesRepository;
    }

    @GetMapping(value = "/carritos")
    public Iterable<Carrito> todosLostCarritos() {
        return carritosRepository.findAll();
    }
    @GetMapping(value = "/carritos/{id}")
    public Optional<Carrito> buscarCarritoPorId(@PathVariable("id") Long id) {
        return carritosRepository.findById(id);
    }

    @PutMapping(value = "/carrito/{id_carrito}/close")
    public Carrito cerrarCarrito(@PathVariable("id_carrito") Long id_carrito){
        Carrito carrito = carritosRepository.getById(id_carrito);
        carrito.setEstado(false);
        return carritosRepository.save(carrito);
    }

    @PutMapping(value = "/carrito/{id_carrito}/addproducto/{id_producto}")
    public Detalle addProducto(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_producto") Long id_producto){
        Carrito carrito = carritosRepository.getById(id_carrito);
        if (carrito.getEstado()) {
            Producto producto = productosRepository.getById(id_producto);
            Detalle detalle = new Detalle();
            detalle.setProducto(producto);
            detalle.setCarrito(carrito);
            Boolean producto_existente = false;
            List<Detalle> detalles_del_carrito = carrito.getDetalle();
            for (Detalle d : detalles_del_carrito) {
                if (d.getProducto().equals(producto)) {
                    producto_existente = true;
                    return d;
                }
            }
            carrito.addDetalle(detalle);
            carritosRepository.save(carrito);
            return detalle;
        }
        return null;
    }

    @PutMapping(value = "/carrito/{id_carrito}/decproducto/{id_producto}")
    public Detalle decremetarProducto(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_producto") Long id_producto){
        Carrito carrito = carritosRepository.getById(id_carrito);
        if (carrito.getEstado()) {
            Producto producto = productosRepository.getById(id_producto);
            List<Detalle> detalles_del_carrito = carrito.getDetalle();
            for (Detalle d : detalles_del_carrito) {
                if (d.getProducto().getId().equals(producto.getId())) {
                    if (d.getCantidad() == 1) {
                        carrito.removeDetalle(d);
                        detallesRepository.save(d);
                        break;
                    } else {
                        d.decCantidad();
                        return detallesRepository.save(d);
                    }
                }
            }
        }
        return null;
    }

    @PutMapping(value = "/carrito/{id_carrito}/incproducto/{id_producto}")
    public Detalle incremetarProducto(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_producto") Long id_producto){
        Carrito carrito = carritosRepository.getById(id_carrito);
        if (carrito.getEstado()) {
            Producto producto = productosRepository.getById(id_producto);
            List<Detalle> detalles_del_carrito = carrito.getDetalle();
            for (Detalle d : detalles_del_carrito) {
                if (d.getProducto().getId().equals(producto.getId())) {
                    d.incCantidad();
                    return detallesRepository.save(d);
                }
            }
        }
        return null;
    }

    @PutMapping(value = "/carrito/{id_carrito}/delproducto/{id_producto}")
    public Detalle delProducto(@PathVariable("id_carrito") Long id_carrito, @PathVariable("id_producto") Long id_producto){
        Carrito carrito = carritosRepository.getById(id_carrito);
        if (carrito.getEstado()) {
            Producto producto = productosRepository.getById(id_producto);
            List<Detalle> detalles_del_carrito = carrito.getDetalle();
            for (Detalle d : detalles_del_carrito) {
                if (d.getProducto().getId().equals(producto.getId())) {
                    carrito.removeDetalle(d);
                    detallesRepository.delete(d);
                    return d;
                }
            }
        }
        return null;
    }
    @DeleteMapping(value = "/carritos/{id}")
    public void deleteCarrito(@PathVariable("id") Long id) {
        carritosRepository.deleteById(id);
    }



}


