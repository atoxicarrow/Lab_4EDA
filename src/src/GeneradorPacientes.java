import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GeneradorPacientes {
    public static List<Paciente> generar(int cantidad) {
        List<Paciente> lista = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = generarNombre();
            String apellido = generarApellido();
            String id = "p" + (i + 1);
            int categoria = generarCategoria();
            String area = area();

            long tiempoLlegada = i * 600;

            Paciente p = new Paciente(nombre, apellido, id, categoria, tiempoLlegada, "En_espera", area);
            System.out.println(id + " llegará en el minuto: " + (tiempoLlegada / 60));
            lista.add(p);
        }

        return lista;
    }

    private static int generarCategoria(){
        Random rand = new Random();
        int numero = rand.nextInt(100)+1;

        if(numero <= 10){
            return 1;
        } else if(numero <= 25){
            return 2;
        } else if(numero <= 43){
            return 3;
        } else if(numero <= 70){
            return 4;
        } else {
            return 5;
        }
    }

    private static String generarNombre(){
        String[] nombres = {"Juan", "Maria", "Pedro", "Ana", "Luis"};
        return nombres[new Random().nextInt(nombres.length)];
    }
    private static String generarApellido(){
        String[] apellidos = {"Garcia", "Lopez", "Martinez", "Perez", "Gonzalez"};
        return apellidos[new Random().nextInt(apellidos.length)];
    }
    private static String area(){
        String[] areas= {"SAPU", "urgencia_adulto", "infantil"};
        Random rand = new Random();
        return areas[rand.nextInt(areas.length)];
    }

    public static void TXT(List<Paciente> pacientes, String papucientes){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(papucientes))){
            for(Paciente p: pacientes){
                String linea = p.getID() + ";" + p.getNombre() + " " + p.getApellido() + ";" + p.getCategoria() + ";" + p.getTiempoLlegada() + ";"+ p.getEstado() + ";" + p.getArea();
            writer.write(linea);
            writer.newLine();
            }
            System.out.println("Archivo generado" + papucientes);
        }   catch (IOException e){
            System.out.println("Error al generar archivo" + e.getMessage());
        }
    }
}
