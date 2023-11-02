//Universidad del Valle de Guatemala	                                                                                            
//Adriana Palacios 
//CC2008 - Introducción a la Programación Orientada a Objetos 
//carné 23044
//Semestre II, 2023

/**
 * Clase principal que contiene el programa para gestionar dispositivos electrónicos.
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<DispositivoElectronico> stand = new ArrayList<>();

        // Cargar dispositivos de ejemplo
        stand.add(new Computadora(120, "Thinkpad", "Apagado"));
        stand.add(new Telefono(121, "iPhone 15", "Encendido"));
        stand.add(new Computadora(122, "Mcbook Pro", "Apagado"));
        stand.add(new Computadora(123, "Asus TUF", "Encendido"));
        stand.add(new Telefono(124, "Galaxy Note", "Encendido"));

        Scanner scanner = new Scanner(System.in);

        String opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Mostrar información de dispositivos");
            System.out.println("2. Mostrar dispositivos encendidos y apagados");
            System.out.println("3. Mostrar información de dispositivo por código");
            System.out.println("4. Guardar dispositivos en un archivo CSV");
            System.out.println("5. Cargar dispositivos desde un archivo CSV");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");

            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    mostrarInformacion(stand);
                    break;
                case "2":
                    mostrarEncendidosYApagados(stand);
                    break;
                case "3":
                    mostrarInformacionPorCodigo(stand);
                    break;
                case "4":
                    guardarEnCSV(stand, "dispositivos.csv");
                    break;
                case "5":
                    cargarDesdeCSV(stand, "dispositivos.csv");
                    break;
                case "6":
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (!opcion.equals("6"));

        scanner.close();
    }
///** Muestra la información de todos los dispositivos en el stand.
    private static void mostrarInformacion(List<DispositivoElectronico> stand) {
        for (DispositivoElectronico dispositivo : stand) {
            System.out.println(dispositivo.obtenerInformacion());
        }
    }
//Muestra la cantidad de dispositivos encendidos y apagados en el stand.
    private static void mostrarEncendidosYApagados(List<DispositivoElectronico> stand) {
        int encendidos = 0;
        int apagados = 0;

        for (DispositivoElectronico dispositivo : stand) {
            if (dispositivo.estaEncendido()) {
                encendidos++;
            } else {
                apagados++;
            }
        }

        System.out.println("Dispositivos encendidos: " + encendidos);
        System.out.println("Dispositivos apagados: " + apagados);
    }
//* Muestra la información de un dispositivo por su código
    private static void mostrarInformacionPorCodigo(List<DispositivoElectronico> stand) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código del dispositivo: ");
        int codigoBuscado = scanner.nextInt();
        scanner.nextLine();

        for (DispositivoElectronico dispositivo : stand) {
            if (dispositivo.getCodigo() == codigoBuscado) {
                System.out.println(dispositivo.obtenerInformacion());
                return;
            }
        }
        System.out.println("No se encontró ningún dispositivo con el código ingresado.");
    }

    private static void guardarEnCSV(List<DispositivoElectronico> stand, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Tipo,Modelo,Marca,Estado,Codigo");
            for (DispositivoElectronico dispositivo : stand) {
                if (dispositivo instanceof Telefono) {
                    writer.println("Telefono," + ((Telefono) dispositivo).getModelo() + ",," + dispositivo.estaEncendido() + "," + dispositivo.getCodigo());
                } else if (dispositivo instanceof Computadora) {
                    writer.println("Computadora,," + ((Computadora) dispositivo).getMarca() + "," + dispositivo.estaEncendido() + "," + dispositivo.getCodigo());
                }
            }
            System.out.println("Los dispositivos se han guardado en el archivo CSV: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Guarda la información de los dispositivos en un archivo CSV.
    private static void cargarDesdeCSV(List<DispositivoElectronico> stand, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String tipo = parts[0].trim();
                    String modelo = parts[1].trim();
                    String marca = parts[2].trim();
                    boolean encendido = Boolean.parseBoolean(parts[3].trim());
                    int codigo = Integer.parseInt(parts[4].trim());
                    if (tipo.equals("Telefono")) {
                        Telefono telefono = new Telefono(codigo, modelo, encendido ? "Encendido" : "Apagado");
                        stand.add(telefono);
                    } else if (tipo.equals("Computadora")) {
                        Computadora computadora = new Computadora(codigo, marca, encendido ? "Encendido" : "Apagado");
                        stand.add(computadora);
                    }
                }
            }
            System.out.println("Los dispositivos se han cargado desde el archivo CSV: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
