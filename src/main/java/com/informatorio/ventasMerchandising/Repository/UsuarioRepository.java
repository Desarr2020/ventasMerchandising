package com.informatorio.ventasMerchandising.Repository;

import com.informatorio.ventasMerchandising.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    Usuario getById(Long id);
    public List<Usuario> findByCiudadStartingWith(String ciudad);
    List<Usuario> findByFechaDeCreacionAfter(LocalDateTime dateTime);
    List<Usuario> findByNombreContainingAndApellidoContaining(String nombre, String apellido);
}