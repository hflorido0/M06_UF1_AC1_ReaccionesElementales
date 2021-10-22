package model;

public class Personaje {
	private String nombre;
	private String tipo;
	
	public Personaje(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
