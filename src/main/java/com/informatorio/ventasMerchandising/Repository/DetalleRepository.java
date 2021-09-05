package com.informatorio.ventasMerchandising.Repository;

import com.informatorio.ventasMerchandising.Entity.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long>{
    Detalle getById(Long id);
}
