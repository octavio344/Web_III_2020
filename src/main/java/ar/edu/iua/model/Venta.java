package ar.edu.iua.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ventas")
public class Venta implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1861830004923572175L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String detalleVenta;
	
	@Column(columnDefinition = "DATE" ,nullable = false)
	private String fechaVenta;
	
	
	@ManyToMany(mappedBy = "ventasList")
	@JsonBackReference
	private List<Producto> productosList;
	
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDetalleVenta() {
		return detalleVenta;
	}



	public void setDetalleVenta(String detalleVenta) {
		this.detalleVenta = detalleVenta;
	}



	public List<Producto> getProductosList() {
		return productosList;
	}



	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}
	
	public String getFechaVenta() {
		return fechaVenta;
	}



	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


}
