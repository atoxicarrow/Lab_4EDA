import java.util.*;

public class Hospital {
    Map<String, Paciente> pacientesTotales;
    PriorityQueue<Paciente> colaAtencion;
    Map<String, AreaAtencion> areasAtencion;
    List<Paciente> pacientesAtendidos;

    public Hospital(){
        this.pacientesTotales = new HashMap<String, Paciente>();
        this.pacientesAtendidos = new ArrayList<>();

        this.colaAtencion = new PriorityQueue<>(new Comparator<Paciente>() {
            @Override
            public int compare(Paciente p1, Paciente p2) {
                if(p1.getCategoria() != p2.getCategoria()){
                    return Integer.compare(p1.getCategoria(), p2.getCategoria());
                }
                return Long.compare(p2.getTiempoLlegada(), p1.getTiempoLlegada());
            }
        });
        this.areasAtencion = new HashMap<>();
        areasAtencion.put("SAPU", new AreaAtencion("SAPU",100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto",100));
        areasAtencion.put("infantil", new AreaAtencion("infantil",100));
    }

    public void registrarPaciente(Paciente p){
        pacientesTotales.put(p.getID(), p);
        colaAtencion.offer(p);
    }

    public void reasignarCategoria(String id, int nuevaCategoria){
        Paciente p = pacientesTotales.get(id);
        if (p != null) {
            colaAtencion.remove(p);
            p.registrarCambio("Cambio de categoría de " + p.getCategoria() + " a " + nuevaCategoria);
            p.setCategoria(nuevaCategoria);
            colaAtencion.offer(p);
        }
    }

    public Paciente atenderSiguiente(){
    if(colaAtencion.isEmpty()){
        return null;
    }
    Paciente p = colaAtencion.poll();
    AreaAtencion area = areasAtencion.get(p.getArea());

    if(area != null && !area.estaSaturada()){
        area.ingresarPaciente(p);
    }
    p.setEstado("atendido");
    pacientesAtendidos.add(p);
    return p;
    }

    public List<Paciente>obtenerPacientesPorCategoria(int categoria){
        List<Paciente> lista = new ArrayList<>();
        for(Paciente p: colaAtencion){
            if(p.getCategoria()== categoria){
                lista.add(p);
            }
        }
        return lista;
    }

    public AreaAtencion obtenerArea(String nombre){
        return areasAtencion.get(nombre);
    }
    public List<Paciente> obtenerPacientesAtendidos(){
        return pacientesAtendidos;
    }
    public List<Paciente> getPacientesEnEspera() {
        return new ArrayList<>(new PriorityQueue<>(colaAtencion));
    }



}