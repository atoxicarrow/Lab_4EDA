import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;

public class AreaAtencion{
    //Atributos
    String nombre;
    PriorityQueue<Paciente> pacientesHeap;
    int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima){
        this.nombre=nombre;
        this.capacidadMaxima=capacidadMaxima;
        this.pacientesHeap = new PriorityQueue<>(new Comparator<Paciente>(){
            @Override
            public int compare(Paciente p1, Paciente p2){
                return Integer.compare(p1.getCategoria(), p2.getCategoria());
            }
        });
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    public int getCantidadPacientes() {
        return pacientesHeap.size();
    }

    public List<Paciente> getPacientesEnEspera() {
        return new ArrayList<>(pacientesHeap);
    }


    //Metodos

    public void ingresarPaciente(Paciente p){
        if(!estaSaturada()){
            pacientesHeap.offer(p);
        }
    }
    public Paciente atanderPaciente(){
        return pacientesHeap.poll();
    }

    public boolean estaSaturada(){
        return pacientesHeap.size() >=capacidadMaxima;
    }

    public List<Paciente>obtenerPacientesPorHeapSort(){
        PriorityQueue<Paciente> copia = new PriorityQueue<>(pacientesHeap);
        List<Paciente> resultado = new ArrayList<>();
        while (!copia.isEmpty()) {
            resultado.add(copia.poll());
        }
        return resultado;
    }



}
