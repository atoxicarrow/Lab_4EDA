public class Main {
 public static void main(String[] args) {
  int pacientesPorDia = 144; //los 1440 mins del dia divididos en 10 xd

  System.out.println("Iniciando simulación...");
  SimuladorUrgencia simulador = new SimuladorUrgencia();
  simulador.simular(pacientesPorDia);

  GeneradorPacientes.TXT(simulador.getPacientes(), "Pacientes_24h.txt");
 }
}

