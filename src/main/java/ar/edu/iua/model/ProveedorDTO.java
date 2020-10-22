package ar.edu.iua.model;

public class ProveedorDTO {
	
	private String nombreProv;

	public ProveedorDTO(String nombreProv) {
		super();
		this.nombreProv = "proveedor_"+nombreProv;
	}

	public String getNombreProv() {
		return nombreProv;
	}

	public void setNombreProv(String nombreProv) {
		this.nombreProv = nombreProv;
	}
	
	
	

}
