package Services;


import Model.Vehiculos;

public interface Sistema {
    /**
     * Menu principal del sistema
     */
    void mostrarMenu();
    /**
     * Métodos de opción 1 (Ver vehículos): Listar todos los vehículos, buscar vehículo por tipo y
     * buscar vehículo por id
     */
    void verVehiculos();
    void ListarVehiculos();
    void buscarVehiculosPorTipo(String tipoVehiculo);
    Vehiculos buscarVehiculoporId(int id);

    /**
     * Método de opción 2 (Ver historial de mantenimiento): Mostrar historial por vehículo
     */
    void mostrarHistorialPorVehiculo(int idVehiculo);

    /**
     * Método de opción 3, Agregar vehiculo
     */
    void agregarVehiculo();

    /**
     * Método de opción 4, Registrar mantenimiento
     */
    void registrarMantenimiento();

    /**
     * Método de opción 5, Generar boleta mantenimiento
     */
    void generarBoleta();

    /**
     * Método de opción 6 (Ver boletas): Listado de todas las boletas
     */
    void listadoDeBoletas();

    /**
     * Salir y Guardar los datos actualizados en mantenimientos.csv y vehiculos.csv.
     */
    void SaliryGuardar();


}
