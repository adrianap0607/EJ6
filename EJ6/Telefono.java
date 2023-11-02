//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

//* Clase que representa un telefono como dispositivo electrónico.
public class Telefono implements DispositivoElectronico {
    private String modelo;
    private String estado;
    private int codigo;
//Constructor clase telefono
    public Telefono(int codigo, String modelo, String estado) {
        this.codigo = codigo;
        this.modelo = modelo;
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
        return "Teléfono - Modelo: " + modelo + ", Estado: " + estado + ", Código: " + codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getModelo() {
        return modelo;
    }
}
