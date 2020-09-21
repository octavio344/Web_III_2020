package ar.edu.iua.business;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.persistence.VentaRepository;

@Service
public class VentaBusiness implements IVentaBusiness{

	
	@Autowired
	private VentaRepository ventaDAO;
	
	@Override
	public List<Venta> list() throws BusinessException {
		
		try {
			return ventaDAO.findAll();
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Venta load(Long id) throws BusinessException, NotFoundException {
		Optional<Venta> op;
		try {
			op=ventaDAO.findById(id);
		}catch(Exception e) {
			throw new BusinessException(e);
		}
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro la venta con el id: "+id);
		}
		return op.get();
	}

	@Override
	public Venta add(Venta v) throws BusinessException {
		
		Venta ven;
		
		try {
			ven=ventaDAO.save(v);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		return ven;
	}

	@Override
	public Venta update(Venta v) throws BusinessException, NotFoundException {
		Venta ven=load(v.getId());
		if(ven ==null) {
			throw new NotFoundException("No se ha encontrado la venta con id = "+v.getId());
		}
		try {
			return add(v);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		
		try {
			ventaDAO.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("No se encontro la venta con id: "+id);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Venta> listOrderByDate() throws BusinessException {
		try {
			return ventaDAO.findAllByOrderByFechaVentaDesc();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	
	@Override
	public List<Venta> listByDescription(String descripcion) throws BusinessException, NotFoundException {
		List<Venta> l = new ArrayList<Venta>();
		try {
			l = ventaDAO.findByDetalleVentaContaining(descripcion);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(l.isEmpty()) {
			throw new NotFoundException("No se han encontrado ventas con la descripción proporcionada!");
		}
		
		return l;
	}

	
	@Override
	public List<Venta> listByNombreProducto(String nombre) throws BusinessException, NotFoundException {
		List<Venta> l = new ArrayList<Venta>();
		try {
			l = ventaDAO.findByProductosListNombreContaining(nombre);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if(l.isEmpty()) {
			throw new NotFoundException("No se han encontrado ventas con el nombre proporcionado!");
		}
	
		return l;
	}

}
