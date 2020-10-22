package ar.edu.iua.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;

//https://docs.spring.io/spring-data/jpa/docs/2.3.2.RELEASE/reference/html/#repositories.query-methods.details

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	 List<Producto> findByNombreContainingOrDescripcionContainingOrderByNombreDesc(String nombre, String descripcion);
	
	 List<Producto> findAllByOrderByPrecioListaDesc();
	
	 List<Producto> findByPrecioListaLessThanEqual(double precioLista);
	
	 List<Producto> findByPrecioListaGreaterThan(double precioLista);
	
	 List<Producto> findByDescripcionContaining(String descripcion);
	
	 List<Producto> findByNombreStartingWith(String nombre);
	
	 List<Producto> findByVentasListDetalleVentaContaining(String detalle);
	 
	 @Query(value="select * from produtos where precio_lista > ?",nativeQuery=true)
	 List<Producto> findByprecioMayorQue(double precio);
	 
	 

}


