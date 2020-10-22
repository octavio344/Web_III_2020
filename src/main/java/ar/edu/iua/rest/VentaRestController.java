package ar.edu.iua.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IVentaBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.VentasDTO;

@RestController
@RequestMapping(value = Constantes.URL_VENTAS)
public class VentaRestController {

	
	
private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IVentaBusiness ventasBusiness;
	
	// curl "http://localhost:8080/api/v1/ventas/1" -v
		@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Venta> load(@PathVariable("id") Long id) {

			try {
				return new ResponseEntity<Venta>(ventasBusiness.load(id), HttpStatus.OK);
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<Venta>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<Venta>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		// curl "http://localhost:8080/api/v1/ventas"
		// curl "http://localhost:8080/api/v1/ventas?descripcion=arga"
		@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Venta>> list(
				@RequestParam(name = "descripcion", required = false, defaultValue = "*") String parte) {
			try {
				if (parte.equals("*")) {
					return new ResponseEntity<List<Venta>>(ventasBusiness.list(), HttpStatus.OK);
				} else {
					return new ResponseEntity<List<Venta>>(ventasBusiness.listByDescription(parte), HttpStatus.OK);
				}
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (NotFoundException e) {
				return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
			}
		}
		
		// curl "http://localhost:8080/api/v1/ventas/fecha"
		@GetMapping(value = "/fecha", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Venta>> listByFecha(){
			try {
				return new ResponseEntity<List<Venta>>(ventasBusiness.listOrderByDate(),HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		

		// curl -X POST "http://localhost:8080/api/v1/ventas" -H "Content-Type:
		// application/json" -d '{}' -v
		@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> add(@RequestBody Venta venta) {
			try {
				ventasBusiness.add(venta);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("location", Constantes.URL_VENTAS + "/" + venta.getId());
				return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		
		// curl -X PUT "http://localhost:8080/api/v1/ventas" -H "Content-Type:
		// application/json" -d '{}' -v
		@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> update(@RequestBody Venta venta) {
			try {
				ventasBusiness.update(venta);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		// curl -X DELETE "http://localhost:8080/api/v1/ventas/2" -v
		@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> delete(@PathVariable("id") Long id) {
			try {
				ventasBusiness.delete(id);
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (BusinessException e) {
			    log.error(e.getMessage(), e);
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		
		// curl "http://localhost:8080/api/v1/ventas/detalle-venta" -v
		@GetMapping(value = "/detalle-venta", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Venta>> listByProductoNombre(@RequestParam(name = "nombre", required = true) String nombre) {
			
			try {
				
				return new ResponseEntity<List<Venta>>(ventasBusiness.listByNombreProducto(nombre), HttpStatus.OK);
				
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<List<Venta>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (NotFoundException e) {
				return new ResponseEntity<List<Venta>>(HttpStatus.NOT_FOUND);
				
			}
		}
		
		
		@GetMapping(value = "/venta-fecha", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<VentasDTO>> listFechaByProductoNombre(@RequestParam(name = "nombre", required = true) String nombre) {
			
			try {
				
				return new ResponseEntity<List<VentasDTO>>(ventasBusiness.listFechaByNombre(nombre), HttpStatus.OK);
				
			} catch (BusinessException e) {
				log.error(e.getMessage(), e);
				return new ResponseEntity<List<VentasDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
}
