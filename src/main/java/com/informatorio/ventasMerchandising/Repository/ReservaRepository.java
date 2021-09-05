package com.informatorio.ventasMerchandising.Repository;

import com.informatorio.ventasMerchandising.Entity.Reserva;
import com.informatorio.ventasMerchandising.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long>{
    Reserva getById(Long id);
}