package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;

public interface IVentaBusiness {

	public List<Venta> list() throws BusinessException;
	
	public Venta load(Long id) throws BusinessException, NotFoundException;
	
	public Venta add(Venta v)throws BusinessException;
	
	public Venta update(Venta v) throws BusinessException, NotFoundException;
	
	public void delete(Long id) throws BusinessException,NotFoundException;
	
	public List<Venta> listOrderByDate() throws BusinessException;
	
	public List<Venta> listByDescription(String descripcion) throws BusinessException, NotFoundException;
}
