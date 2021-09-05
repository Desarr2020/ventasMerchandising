package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Entity.Categoria;
import com.informatorio.ventasMerchandising.Entity.Producto;
import com.informatorio.ventasMerchandising.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private final ProductoRepository productosRepository;


    public ProductoController(ProductoRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    //Operacion para  dar de alta de un producto.
    @PostMapping
    public Producto crearProductos(@Valid @RequestBody Producto productos) {
        return productosRepository.save(productos);
    }
    //Operacion para dar de baja un producto por su id.
    @DeleteMapping(value = "productos/{id}")
    public void deleteProducto(@PathVariable("id") Long id) {
        productosRepository.deleteById(id);
    }


    //Operacion para obtener todos los productos.
    @GetMapping(value = "/productos")
    public Iterable<Producto> todosLosProductos() {
        return productosRepository.findAll();
    }


    //Operacion para buscar un producto por su id.
    @GetMapping(value = "productos/{id}")
    public Optional<Producto> getProductoById(@PathVariable("id") Long id) {
        return productosRepository.findById(id);
    }

    @PutMapping(value = "productos/{id}")
    public Producto modificarUsuario(@PathVariable("id") Long id, @RequestBody Producto producto){
        Producto productoS = productosRepository.getById(id);
        productoS.setNombre(producto.getNombre());
        productoS.setDescripcion(producto.getDescripcion());
        productoS.setPrecio_unitario(producto.getPrecio_unitario());
        productoS.setCodigo_inventario(producto.getCodigo_inventario());
        productoS.setCategoria(producto.getCategoria());
        productoS.setContenido(producto.getContenido());
        productoS.setFecha_creacion(producto.getFecha_creacion());
        productoS.setPublicado(producto.getPublicado());

        return productosRepository.save(productoS);
    }

    //Operacion para  buscar por categoria
    @GetMapping(value = "productos/buscaC")
    public List<Producto> buscarPorCategoria(@RequestParam(value="categoria") Categoria categoria){
        return productosRepository.findByCategoria(categoria);
    }

    //Operacion para buscar todos los productos que contenga una palabra dada en el nombre
    @RequestMapping(value = "productos/Contenga/{nombre}", method = RequestMethod.GET)
    @ResponseBody
    public List<Producto> findByNameContaining(@PathVariable String nombre) {
        List<Producto> productoResponse = (List<Producto>) productosRepository.findByNombreContaining(nombre);
        return productoResponse;
    }
    //Operacion para  traer todos los productos sin publicar
    @GetMapping(value = "productos/NoPublicado")
    public List<Producto> buscarProductoNoPublicados(){
        return productosRepository.findByPublicadoFalse();
    }
    //Operacion para traer todos los productos publicados
    @GetMapping(value = "productos/SiPublicado")
    public List<Producto> buscarProductoSiPublicados(){
        return productosRepository.findByPublicadoTrue();
    }



}