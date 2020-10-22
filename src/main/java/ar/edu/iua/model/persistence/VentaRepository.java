package ar.edu.iua.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.VentasDTO;

@Repository
public interface VentaRepository extends JpaRepository<Venta , Long>{
	
	 List<Venta> findByDetalleVentaContaining(String descripcion);
	
	 List<Venta> findByIdEquals(Long id);
	
	 List<Venta> findAllByOrderByFechaVentaDesc();

	 List<Venta> findByProductosListNombreContaining(String nombre)throws BusinessException,NotFoundException;
	 
	 @Query(nativeQuery = true)
	 List<VentasDTO> findFechaByNombre(String nombre);
}
