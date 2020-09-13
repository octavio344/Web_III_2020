package ar.edu.iua.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proveedores")
public class Proveedor implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -8328672099403732928L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String nombre;
	
	@OneToMany(targetEntity=Producto.class, mappedBy="proveedor", fetch = FetchType.LAZY)
	private List<Producto> productoList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}
	
	
}
