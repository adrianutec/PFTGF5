package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RenglonPedido
 */

@Entity
@Table(name="RENGLONESPEDIDOS")

public class RenglonPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "REN_ID")
	private int id;
	
	@Column(name = "REN_NRO", nullable=false)
	private int rennro;
	
	@Column(name = "REN_CANT", nullable=false)
	private int rencant;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "REN_PRO_ID")
	private Producto producto;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "REN_PED_ID")
	private Pedido pedido;

	public RenglonPedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRennro() {
		return rennro;
	}

	public void setRennro(int rennro) {
		this.rennro = rennro;
	}

	public int getRencant() {
		return rencant;
	}

	public void setRencant(int rencant) {
		this.rencant = rencant;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

  
}
