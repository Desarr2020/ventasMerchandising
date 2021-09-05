package com.informatorio.ventasMerchandising.Repository;

import com.informatorio.ventasMerchandising.Entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden getById(Long id);
}
