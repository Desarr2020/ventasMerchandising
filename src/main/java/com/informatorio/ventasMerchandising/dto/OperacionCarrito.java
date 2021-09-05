package com.informatorio.ventasMerchandising.dto;

public class OperacionCarrito {
    private  Integer cantidad;
    private  long  productosId;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public long getProductosId() {
        return productosId;
    }

    public void setProductosId(long productosId) {
        this.productosId = productosId;
    }
}
