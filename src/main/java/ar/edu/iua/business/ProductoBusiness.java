package ar.edu.iua.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {

	@Autowired
	private ProductoRepository productoDAO;
	
	@Override
	public Producto load(Long id) throws NotFoundException, BusinessException {
		Optional<Producto> op;
		try {
			op=productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(!op.isPresent()) {
			throw new NotFoundException("El producto con id "+id+" no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto add(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			productoDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el producto id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Producto update(Producto producto) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> list(String parte) throws BusinessException {
		
		try {
			return productoDAO.findByNombreContainingOrDescripcionContainingOrderByNombreDesc(parte, parte);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Producto> listPrice() throws BusinessException, NotFoundException {
		
		List<Producto> l= new ArrayList<Producto>();
		
		try {
			l = productoDAO.findAllByOrderByPrecioListaDesc();
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		if(l.isEmpty()) {
			throw new NotFoundException("No hay!");
		}
		return l;
	}

	@Override
	public List<Producto> loadByPrice(double precioLista, String modoBus) throws BusinessException, NotFoundException {
		
		List<Producto> l= new ArrayList<Producto>();
		
		try {
			if(modoBus.contains("mayor")) {
				l =  productoDAO.findByPrecioListaGreaterThan(precioLista);
			}else if(modoBus.contains("menor")) {
				l = productoDAO.findByPrecioListaLessThanEqual(precioLista);
			}
		}catch(Exception e) {
			throw new BusinessException(e);
		}if(l.isEmpty()) {
			throw new NotFoundException("No hay!");
		}
		return l;
		
	}

	@Override
	public List<Producto> listByDescription(String description) throws BusinessException, NotFoundException {
		List<Producto> l= new ArrayList<Producto>();
		
		try {
			
			l = productoDAO.findByDescripcionContaining(description);
			
		}catch(Exception e) {
			
			throw new BusinessException(e);
			
		}if(l.isEmpty()) {
			throw new NotFoundException("No hay!");
		}
		return l;
		
	}

	@Override
	public List<Producto> listByA() throws BusinessException , NotFoundException {
		
		List<Producto> l= new ArrayList<Producto>();
		
		try {
			
			l = productoDAO.findByNombreStartingWith("a");
			
		}catch(Exception e) {
			throw new BusinessException(e);
		}
		if(l.isEmpty()) {
			throw new NotFoundException("No hay!");
		}
		return l;
		
		
	}

	@Override
	public List<Producto> listByVentaDetalle(String detalle) throws BusinessException, NotFoundException {
		List<Producto> op;
		try {
			op=productoDAO.findByVentasListDetalleVentaContaining(detalle);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(op.isEmpty()) {
			throw new NotFoundException("El producto con detalle de venta: "+detalle+" no se encuentra en la BD");
		}
		return op;
	}

}

//@Autowired
//private IProductoBusiness productoBusiness
