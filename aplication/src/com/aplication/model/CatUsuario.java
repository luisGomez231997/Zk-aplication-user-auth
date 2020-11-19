package com.aplication.model;

public class CatUsuario {

	// varibles
	// ----------------------------------------------------------------------
	private int codigo;
	private String documento;
	private String nombre;
	private String apellido;
	private String correo;
	private String direccion;
	private String telefono;
	private String password;

	// methods
	// -----------------------------------------------------------------------
	public CatUsuario(int codigo, String documento, String nombre, String apellido, String correo, String direccion,
			String telefono, String password) {
		super();
		this.codigo = codigo;
		this.documento= documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		
	}
	

	public String getDocumento() {
		return documento;
	}



	public void setDocumento(String documento) {
		this.documento = documento;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
