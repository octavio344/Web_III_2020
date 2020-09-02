package ar.edu.iua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="productosDetalle")
public class ProductoDetalle implements Serializable {


	private static final long serialVersionUID = 1928685291018379116L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 300)
	private String fabrica;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFabrica() {
		return fabrica;
	}

	public void setFabrica(String fabrica) {
		this.fabrica = fabrica;
	}

	public ProductoDetalle() {
		super();
	}

	public ProductoDetalle(Integer id, String fabrica) {
		super();
		this.id = id;
		this.fabrica = fabrica;
	}
	
	
	
	
}
