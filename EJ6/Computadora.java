//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

//* Clase que representa una Computadora como dispositivo electrónico.

public class Computadora implements DispositivoElectronico {
    private String marca;
    private String estado;
    private int codigo;

//Constructor de la clase computadora 
    public Computadora(int codigo, String marca, String estado) {
        this.codigo = codigo;
        this.marca = marca;
        this.estado = estado;
    }

    @Override
    public void encender() {
        estado = "Encendido";
    }

    @Override
    public void apagar() {
        estado = "Apagado";
    }

    @Override
    public boolean estaEncendido() {
        return estado.equals("Encendido");
    }

    @Override
    public String obtenerInformacion() {
        return "Computadora - Marca: " + marca + ", Estado: " + estado + ", Código: " + codigo;
    }
// Obtiene el código único de la computadora.
    public int getCodigo() {
        return codigo;
    }
//obtiene la marca de la computadora
    public String getMarca() {
        return marca;
    }
}
