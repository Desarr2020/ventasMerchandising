package com.informatorio.ventasMerchandising.Converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.informatorio.ventasMerchandising.Entity.Reserva;
import com.informatorio.ventasMerchandising.dto.ReservaDto;

public  class ReserDtoToReservaEntity implements Converter<ReservaDto,Reserva>{
    @Override
    public  Reserva convert(ReservaDto reservaDto){
        return null;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}