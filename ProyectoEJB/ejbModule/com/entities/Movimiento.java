package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.enumerated.tipoMovimiento;

/**
 * Entity implementation class for Entity: Movimiento
 *
 */
@Entity
@Table(name="MOVIMIENTOS")

public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MOV_ID")
	private int id;
	
	@Column(name = "MOV_FECHA", nullable=false)
	private Date fecha;
	
	@Column(name = "MOV_CANTIDAD", nullable=false)
	private int cantidad;
	
	@Column(length=250, name = "MOV_DESCRIPCION")
	private String descripcion;
	
	@Column(name = "MOV_COSTO", nullable=false, columnDefinition = "FLOAT(5,2)")
	private double costo;
	
	@Column(name = "MOV_TIPO", length = 1, nullable=false)
	@Enumerated(value = EnumType.STRING)
	private tipoMovimiento tipoMov;

	@ManyToOne (optional = false)
	@JoinColumn(name = "MOV_PROD_ID")
	private Producto producto;
	
	@ManyToOne (optional = false)
	@JoinColumn(name = "MOV_ALMA_ID")
	private Almacenamiento almacenamiento;

	public Movimiento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public tipoMovimiento getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(tipoMovimiento tipoMov) {
		this.tipoMov = tipoMov;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	   
}
