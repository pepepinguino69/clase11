package org.example;


public class Empleado {

    private String Id, nombre, apellido, DNI, tipo;

    public static String EmpleadoStatic(String id, String nombre, String apellido, String DNI, String tipo) {
        return "INSERT INTO EMPLEADO ( ID, NOMBRE, APELLIDO, DNI, TIPO) VALUES ( " + id + "," + nombre + "," + apellido + "," + DNI + "," + tipo + ")";
    }
}
