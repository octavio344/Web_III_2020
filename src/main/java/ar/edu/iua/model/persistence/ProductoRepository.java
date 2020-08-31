package ar.edu.iua.model.persistence;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;

//https://docs.spring.io/spring-data/jpa/docs/2.3.2.RELEASE/reference/html/#repositories.query-methods.details

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	public List<Producto> findByNombreContainingOrDescripcionContainingOrderByNombreDesc(String nombre, String descripcion);
	
	public List<Producto> findAllByOrderByPrecioListaDesc();
	
	public List<Producto> findByPrecioListaLessThanEqual(double precioLista);
	
	public List<Producto> findByPrecioListaGreaterThan(double precioLista);
	
	public List<Producto> findByDescripcionContaining(String descripcion);
	
	public List<Producto> findByNombreStartingWith(String nombre);
}


