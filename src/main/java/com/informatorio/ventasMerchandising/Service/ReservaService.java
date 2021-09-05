package com.informatorio.ventasMerchandising.Service;

import com.informatorio.ventasMerchandising.Entity.Reserva;
import com.informatorio.ventasMerchandising.Repository.ProductoRepository;
import com.informatorio.ventasMerchandising.Repository.ReservaRepository;
import com.informatorio.ventasMerchandising.Repository.UsuarioRepository;
import com.informatorio.ventasMerchandising.dto.ReservaDto;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuariosRepository;
    private final ReservaRepository reservaRepository;

    public ReservaService(ProductoRepository productoRepository, UsuarioRepository usuariosRepository, ReservaRepository reservaRepository) {
        this.productoRepository = productoRepository;
        this.usuariosRepository = usuariosRepository;
        this.reservaRepository = reservaRepository;
    }

    public boolean crearReservas(ReservaDto reservasDto, Long userId) {
        Reserva reservas = new Reserva();
        reservas.setProductoId(reservasDto.getProductoId());
        reservas.setNombreProduct(productoRepository.getById(reservasDto.getProductoId()).getNombre());
        reservas.setUsername(usuariosRepository.getById(userId).getEmail());
        reservas.setEstaActivo(true);
        reservas = reservaRepository.save(reservas);
        return true;
    }
}
