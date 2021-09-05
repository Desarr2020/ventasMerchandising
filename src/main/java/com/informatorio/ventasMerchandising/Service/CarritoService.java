package com.informatorio.ventasMerchandising.Service;

import com.informatorio.ventasMerchandising.Entity.Carrito;
import com.informatorio.ventasMerchandising.Entity.Usuario;

import com.informatorio.ventasMerchandising.Repository.CarritoRepository;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CarritoService {
    @Autowired
    private static CarritoRepository carritosRepository;

    @Autowired
    private static UsuarioRepository usuariosRepository;

    public static Date creacion() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    ;

    public static Boolean nuevo_carrito(Usuario usuario) {
        List<Carrito> carritos_del_user = usuario.getCarritos();
        if (carritos_del_user.size() >= 1) {
            Carrito ultimo = carritos_del_user.get(carritos_del_user.size() - 1);
            ultimo.setEstado(false);
        }
        return true;
    }
}