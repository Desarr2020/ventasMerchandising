package com.informatorio.ventasMerchandising.Service;

import com.informatorio.ventasMerchandising.Entity.Usuario;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Iterable<Usuario> index(){
        return usuarioRepository.findAll();
    }

    public Usuario store(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario show(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public static Date creacion() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
