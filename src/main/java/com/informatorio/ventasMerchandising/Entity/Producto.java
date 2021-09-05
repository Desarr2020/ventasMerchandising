package com.informatorio.ventasMerchandising.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.informatorio.ventasMerchandising.Service.UsuarioService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Debe ingresar un nombre.")

    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "Debe ingresar una descripcion.")
    private String descripcion;

    @Column(nullable = false,precision = 2, scale = 0)
    private Double precio_unitario;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Ingrese el codigo del producto.")
    private String codigo_inventario;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Categoria categoria;


    @OneToOne(mappedBy = "producto")
    private Detalle detalle;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion = UsuarioService.creacion();

    @Column(nullable = false)
    private Boolean publicado;

    //Getters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public String getCodigo_inventario() {
        return codigo_inventario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public Boolean getPublicado() {
        return publicado;
    }


    //Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setCodigo_inventario(String codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }
}
