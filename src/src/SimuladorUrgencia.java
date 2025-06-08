import java.util.*;

public class SimuladorUrgencia {
    private Hospital hospital;
    private List<Paciente> pacientes;
    private int pacientesIngresados;

    public SimuladorUrgencia(){
        this.hospital = new Hospital();
        this.pacientesIngresados = 0;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void simular(int pacientesPorDia){
        this.pacientes = GeneradorPacientes.generar(pacientesPorDia);
        this.pacientesIngresados = 0;

        int totalMinutos = 1440;
        int pacientesNuevos = 0;

        for(int minutoActual = 0; minutoActual < totalMinutos; minutoActual++){
            if(minutoActual % 10 == 0 && pacientesIngresados < pacientesPorDia){
                Paciente p = pacientes.get(pacientesIngresados++);
                hospital.registrarPaciente(p);
                pacientesNuevos++;
            }

            if(minutoActual % 15 == 0){
                atenderPaciente(minutoActual);
            }

            if(pacientesNuevos == 3){
                atenderPaciente(minutoActual);
                atenderPaciente(minutoActual);
                pacientesNuevos = 0;
            }

            if (minutoActual == 500) {
                Paciente paciente = pacientes.get(42);
                int vieja = paciente.getCategoria();
                hospital.reasignarCategoria(paciente.getID(), 1); // forzar cambio a C1
                System.out.println("Se cambió la categoría del paciente " + paciente.getID() +
                        " de " + vieja + " a 1");
            }
            revisarPacientesUrgentes(minutoActual);
        }

        System.out.println("Terminada la simulación.");
        System.out.println("Total de pacientes atendidos: " + hospital.obtenerPacientesAtendidos().size());
    }



    private void atenderPaciente(int minutoActual){
        Paciente atendido = hospital.atenderSiguiente();
        if(atendido != null){
            long espera = atendido.tiempoEsperaActual(minutoActual);
            atendido.setEstado("atendido");
            System.out.println("[Minuto " + minutoActual + "] Paciente atendido: " + atendido);
            System.out.println("  → Esperó: " + espera + " minutos");
        }
    }

    private void revisarPacientesUrgentes(int minutoActual){
        List<Paciente> enEspera = hospital.getPacientesEnEspera();

        for(Paciente p : enEspera){
            long espera = p.tiempoEsperaActual(minutoActual);
            int categoria = p.getCategoria();
            int maxEspera = tiempoMaximoPermitido(categoria);
            if(espera > maxEspera){
                System.out.println("Paciente " + p.getID() + " superó el tiempo máximo (" + espera + " min)");
                atenderPaciente(minutoActual);
                break;
            }
        }
    }
    private int tiempoMaximoPermitido(int categoria) {
        switch (categoria) {
            case 1: {
                return 0;
            }
            case 2: {
                return 30;
            }
            case 3: {
                return 90;
            }
            case 4: {
                return 180;
            }
            case 5: {
                return 1440;
            }
            default: {
                return 9999;
            }
        }
    }
}


