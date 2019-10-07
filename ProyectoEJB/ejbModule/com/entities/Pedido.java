package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.enumerated.estadoPedido;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity
@Table(name="PEDIDOS")

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PED_ID")
	private int id;
	
	@Column(name = "PED_FEC_ESTIM_ENT", nullable=false)
	private Date pedfecestim;
	
	@Column(name = "PED_FECHA", nullable=false)
	private Date fecha;

	@Column(name = "PED_REC_CODIGO", nullable=false)
	private int pedreccodigo;

	@Column(name = "PED_REC_FECHA", nullable=false)
	private Date pedrecfecha;
	
	@Column(name = "PED_REC_COMENTARIO", length = 250, nullable=false)
	private String pedreccomentario;
	
	@Column(name = "PED_ESTADO", length = 1, nullable=false)
	@Enumerated(value = EnumType.STRING)
	private estadoPedido pedestado;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "PED_USU_CODIGO")
	private Usuario usuario;
	
	public Pedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPedfecestim() {
		return pedfecestim;
	}

	public void setPedfecestim(Date pedfecestim) {
		this.pedfecestim = pedfecestim;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPedreccodigo() {
		return pedreccodigo;
	}

	public void setPedreccodigo(int pedreccodigo) {
		this.pedreccodigo = pedreccodigo;
	}

	public Date getPedrecfecha() {
		return pedrecfecha;
	}

	public void setPedrecfecha(Date pedrecfecha) {
		this.pedrecfecha = pedrecfecha;
	}

	public String getPedreccomentario() {
		return pedreccomentario;
	}

	public void setPedreccomentario(String pedreccomentario) {
		this.pedreccomentario = pedreccomentario;
	}

	public estadoPedido getPedestado() {
		return pedestado;
	}

	public void setPedestado(estadoPedido pedestado) {
		this.pedestado = pedestado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
   
	
	
}
