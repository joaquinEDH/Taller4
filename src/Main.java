import Services.SistemaImpl;
public class Main {
    public static void main(String[] args) {
        SistemaImpl sistema = SistemaImpl.getInstance();// Inicialización del sistema

        sistema.mostrarMenu();

    }
}