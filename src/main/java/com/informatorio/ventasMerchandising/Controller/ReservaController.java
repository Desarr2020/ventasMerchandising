package com.informatorio.ventasMerchandising.Controller;

import com.informatorio.ventasMerchandising.Service.ReservaService;
import com.informatorio.ventasMerchandising.dto.ReservaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class ReservaController {

    private final ReservaService reservasService;

    public ReservaController(ReservaService reservasService) {
        this.reservasService = reservasService;
    }

    @GetMapping("/usuarios/{id}/reservas")
    public ResponseEntity<?> createReserva(@PathVariable("id") Long userId,
                                           @Valid @RequestBody ReservaDto reservasDto) {
        return new ResponseEntity<>(reservasService.crearReservas(reservasDto , userId), HttpStatus.CREATED);
    }
}
