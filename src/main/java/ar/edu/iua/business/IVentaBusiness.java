package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.VentasDTO;

public interface IVentaBusiness {

	 List<Venta> list() throws BusinessException;
	
	 Venta load(Long id) throws BusinessException, NotFoundException;
	
	 Venta add(Venta v)throws BusinessException;
	
	 Venta update(Venta v) throws BusinessException, NotFoundException;
	
	 void delete(Long id) throws BusinessException,NotFoundException;
	
	 List<Venta> listOrderByDate() throws BusinessException;
	
	 List<Venta> listByDescription(String descripcion) throws BusinessException, NotFoundException;
	 
	 List<Venta> listByNombreProducto(String nombre) throws BusinessException, NotFoundException;
	 
	 List<VentasDTO> listFechaByNombre(String nombre)throws BusinessException;
}
