import java.util.Stack;

public class Paciente {
    //Atributos;
    String nombre;
    String apellido;
    String id; //Rut o Pasaporte
    int categoria;
    long tiempoLlegada;
    String estado;
    String area;
    Stack<String> historialCambios;

    public <paciente> Paciente(String nombre, String apellido, String id, int categoria, long tiempoLlegada, String estado, String area) {
    this.nombre=nombre;
    this.apellido=apellido;
    this.id=id;
    this.categoria=categoria;
    this.tiempoLlegada=tiempoLlegada;
    this.estado=estado;
    this.area=area;
    this.historialCambios= new Stack<>();
    }


    //getters
    public void setEstado(String status){
        this.estado = status;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    //setters
    public String getID() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public int getCategoria() {
        return categoria;
    }
    public long getTiempoLlegada() {
        return tiempoLlegada;
    }
    public String getEstado() {
        return estado;
    }
    public String getArea() {
        return area;
    }

    // Metodos;
    public long tiempoEsperaActual(long tiempoActual) {
        long llegadaEnMinutos = tiempoLlegada / 60;
        return tiempoActual - llegadaEnMinutos;
    }

    public void registrarCambio(String descripcion) {
            historialCambios.push(descripcion);
    }

    public String obtenerUltimoCambio() {
        if( historialCambios.isEmpty()){
            return null;
        }else {
            return historialCambios.pop();
        }
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " | C" + categoria + " | " + area + " | " + estado;
    }

}


