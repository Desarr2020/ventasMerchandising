package com.informatorio.ventasMerchandising.Repository;

import com.informatorio.ventasMerchandising.Entity.Categoria;
import com.informatorio.ventasMerchandising.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto getById(Long id);

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByPublicadoFalse();
    List<Producto> findByPublicadoTrue();
}
