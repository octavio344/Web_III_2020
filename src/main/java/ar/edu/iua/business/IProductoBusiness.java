package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

public interface IProductoBusiness {

	public Producto load(Long id) throws NotFoundException, BusinessException;

	public List<Producto> list() throws BusinessException;
	
	public List<Producto> list(String parte) throws BusinessException;
	
	public Producto add(Producto producto) throws BusinessException;
	
	public Producto update(Producto producto) throws NotFoundException, BusinessException;
	
	public void delete(Long id) throws NotFoundException, BusinessException;
	
	public List<Producto> listByDescription(String description) throws BusinessException ,NotFoundException;
	
	public List<Producto> listByA() throws BusinessException,NotFoundException;
	
	public List<Producto> listPrice() throws BusinessException ,NotFoundException;
	
	public List<Producto> loadByPrice(double precio,String modoBus) throws BusinessException, NotFoundException;
	
	public List<Producto> listByVentaDetalle(String detalle)throws BusinessException,NotFoundException;
	
	List<Producto> findByprecioMayorQue(double precioMayor)throws BusinessException;
}
