package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Entity.Usuario;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping(value = "/usuarios")
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Usuario verUsuario(@PathVariable("id") Long id){
        return usuarioRepository.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public Usuario modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        Usuario user = usuarioRepository.getById(id);
        user.setNombre(usuario.getNombre());
        user.setApellido(usuario.getApellido());
        user.setPassword(usuario.getPassword());
        user.setFechaDeCreacion(usuario.getFechaDeCreacion());
        user.setCiudad(usuario.getCiudad());
        user.setProvincia(usuario.getProvincia());
        user.setPais(usuario.getPais());
        return usuarioRepository.save(user);
    }
    @DeleteMapping(value = "/{id}")
    public void borrarUsuario(@PathVariable("id") Long id){
        Usuario usuario =  usuarioRepository.getById(id);
        usuarioRepository.delete(usuario);
    }
    //Operacion para buscar a todos los usuarios que se encuentran viviendo en la ciudad de Resistencia.
    @RequestMapping(value = "/Contenga/{ciudad}", method = RequestMethod.GET)
    @ResponseBody
    public List<Usuario> findByNameStartingWith(@PathVariable String ciudad) {
        List<Usuario> usuarioResponse = (List<Usuario>) usuarioRepository.findByCiudadStartingWith(ciudad) ;
        return usuarioResponse;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios(
            @RequestParam(name = "fechaDeCreacion", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido)
    {if (fechaDeCreacion != null) {
        return new ResponseEntity<>(usuarioRepository.findByFechaDeCreacionAfter(fechaDeCreacion.atStartOfDay()), HttpStatus.OK);
    } else if (Objects.nonNull(nombre) && Objects.nonNull(apellido)) {
        return new ResponseEntity<>(usuarioRepository.findByNombreContainingAndApellidoContaining(nombre, apellido), HttpStatus.OK);
    }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }






}

