package Services;

import Model.Boleta;
import Model.Mantenimiento;
import Model.Vehiculos;
import edu.princeton.cs.stdlib.In;
import edu.princeton.cs.stdlib.Out;

import java.util.*;

public class SistemaImpl implements Sistema{
    private static SistemaImpl instance;
    private List<Mantenimiento> historialMantenimiento;
    private List<Vehiculos> vehiculos;
    private List<Boleta> listaBoletas;
    private Scanner scanner;

    public SistemaImpl(){
        this.historialMantenimiento= new ArrayList<>();
        this.vehiculos= new ArrayList<>();
        this.listaBoletas = new ArrayList<>();
        this.scanner= new Scanner(System.in);
        cargarDatos();
    }
    public static SistemaImpl getInstance(){
        if(instance == null){
            instance = new SistemaImpl();
        }
        return instance;
    }
    private void cargarDatos() {
        cargarVehiculos("vehiculos.csv");
        cargarMantenimiento("mantenimientos.csv");

    }
    private void cargarVehiculos(String filename){
        In archivoEntrada = new In(filename);
        String line = archivoEntrada.readLine(); // Lee la primera línea (encabezados)
        line = archivoEntrada.readLine(); // Lee la primera línea de datos
        while (line != null) {
            String[] elementos = line.split(",");
            int id = Integer.parseInt(elementos[0]);
            String marca = elementos[1];
            String modelo = elementos[2];
            int anio = Integer.parseInt(elementos[3]);
            int kilometraje = Integer.parseInt(elementos[4]);
            String tipoVehiculo = elementos[5];
            String atributo1 = elementos[6];
            String atributo2 = elementos[7];
            Vehiculos vehiculo = new Vehiculos(id, marca, modelo, anio, kilometraje, tipoVehiculo, atributo1, atributo2);
            vehiculos.add(vehiculo);
            line = archivoEntrada.readLine(); // Lee la siguiente línea de datos
        }

    }
    private void cargarMantenimiento(String filename){
        In archivoEntrada = new In(filename);
        String line = archivoEntrada.readLine(); // Lee la primera línea (encabezados)
        line = archivoEntrada.readLine(); // Lee la primera línea de datos
        while (line != null) {
            String[] elementos = line.split(",");
            int idMantenimiento = Integer.parseInt(elementos[0]);
            int idVehiculo = Integer.parseInt(elementos[1]);
            String fecha = elementos[2];
            String tipoMantenimiento = elementos[3];
            String descripcion = elementos[4];
            Mantenimiento mantenimiento = new Mantenimiento(idMantenimiento, idVehiculo, fecha, tipoMantenimiento, descripcion);
            historialMantenimiento.add(mantenimiento);
            line = archivoEntrada.readLine(); // Lee la siguiente línea de datos
        }
    }

    @Override
    public void mostrarMenu() {
        int opcionPrincipal = 0;

        while (opcionPrincipal != 7) {
            System.out.println("Menu");
            System.out.println("1. Ver Vehiculos");
            System.out.println("2. Ver Historial de Mantenimiento");
            System.out.println("3. Agregar Vehiculo");
            System.out.println("4. Registrar mantenimiento");
            System.out.println("5. Generar Boleta Mantenimiento");
            System.out.println("6. Ver Boletas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar entrada inválida
                System.out.print("Seleccione una opción: ");
            }
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrincipal) {
                case 1:
                    verVehiculos();
                    break;
                case 2:
                    System.out.println("Ingrese el id del vehiculo:");
                    while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.next(); // Limpiar entrada inválida
                        System.out.println("Ingrese el id del vehiculo:");
                    }
                    int idVehiculo= scanner.nextInt();
                    mostrarHistorialPorVehiculo(idVehiculo);
                    break;
                case 3:
                    agregarVehiculo();
                    break;
                case 4:
                    registrarMantenimiento();
                    break;
                case 5:
                    generarBoleta();
                    break;
                case 6:
                    listadoDeBoletas();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    SaliryGuardar();
                    break;
                default:
                    System.out.println("Opcion no valida, porfavor intentelo de nuevo.");
                    break;
            }
        }

    }

    /**
     * Método ver vehiculos para la opción 1 del Menu Principal muestra un submenú con tres opciones más.
     * TODOS LOS METODOS DE LA OPCION UNO (VER VEHICULOS) IMPLEMENTADOS.
     */
    @Override
    public void verVehiculos() {
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\nVer Vehículos:");
            System.out.println("1. Listar todos los vehículos");
            System.out.println("2. Buscar vehículo por tipo");
            System.out.println("3. Buscar vehículo por ID");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar entrada inválida
                System.out.print("Seleccione una opción: ");
            }
            opcion = scanner.nextInt();

            scanner.nextLine();
            switch (opcion) {
                case 1:
                    ListarVehiculos();
                    break;
                case 2:
                    System.out.print("Ingrese el tipo de vehículo (Con tildes): ");
                    String tipo = scanner.nextLine();
                    if (tipo.equalsIgnoreCase("AUTOMÓVIL")){
                        buscarVehiculosPorTipo(tipo);
                    } else if(tipo.equalsIgnoreCase("MOTOCICLETA")) {
                        buscarVehiculosPorTipo(tipo);
                    } else if (tipo.equalsIgnoreCase("CAMIÓN")) {
                        buscarVehiculosPorTipo(tipo);
                    } else if (tipo.equalsIgnoreCase("AUTOBÚS")) {
                        buscarVehiculosPorTipo(tipo);
                    } else {
                        System.out.println("Tipo de vehículo inválido.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el ID del vehículo: ");
                    while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
                        System.out.println("Por favor, ingrese un número válido.");
                        scanner.next(); // Limpiar entrada inválida
                        System.out.print("Ingrese el ID del vehículo: ");
                    }
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer de entrada
                    Vehiculos vehiculo = buscarVehiculoporId(id);
                    if (vehiculo != null) {
                        System.out.println("----------------------------------");
                        System.out.println("Vehículo encontrado:");
                        System.out.println("  Marca: " + vehiculo.getMarca());
                        System.out.println("  Modelo: " + vehiculo.getModelo());
                        System.out.println("  Año: " + vehiculo.getAnio());
                        System.out.println("  Kilometraje: " + vehiculo.getKilometraje());
                        System.out.println("  Tipo: " + vehiculo.getTipoVehiculo());
                        System.out.println("  Atributo 1: " + vehiculo.getAtributo1());
                        System.out.println("  Atributo 2: " + vehiculo.getAtributo2());
                        System.out.println("-----------------------------------");
                    } else {
                        System.out.println("Vehículo no encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

    }

    /**
     * Listar Vehiculos metodo opción 1 submenu "Ver Vehiculos".
     */
    @Override
    public void ListarVehiculos() { //METODO LISTO
        int paginas = 4;
        int paginaActual = 0;
        int totalPaginas = (int) Math.ceil((double) vehiculos.size() / paginas);

        while (true) {
            int inicio = paginaActual * paginas;
            int fin = Math.min(inicio + paginas, vehiculos.size());

            System.out.println("\nLista de Vehículos \n- Página " + (paginaActual + 1) + " de " + totalPaginas);
            for (int i = inicio; i < fin; i++) {
                Vehiculos vehiculo = vehiculos.get(i);
                System.out.println("ID: " + vehiculo.getId() + "\nMarca: " + vehiculo.getMarca() + "\nModelo: " + vehiculo.getModelo() +
                        "\nAño: " + vehiculo.getAnio() + "\nKilometraje: " + vehiculo.getKilometraje() +
                        "\nTipo: " + vehiculo.getTipoVehiculo() + "\nAtributo 1: " + vehiculo.getAtributo1() +
                        "\nAtributo 2: " + vehiculo.getAtributo2());
                System.out.println("--------------------------------------");
            }

            System.out.print("\nOpciones: \n[1] Siguiente página \n[2] Página anterior \n[3] Salir al menú principal: ");
            String opcion = scanner.nextLine().trim().toLowerCase();

            if (opcion.equals("1") && paginaActual < totalPaginas - 1) {
                paginaActual++;
            } else if (opcion.equals("2") && paginaActual > 0) {
                paginaActual--;
            } else if (opcion.equals("3")) {
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Buscar Vehiculos por tipo metodo opción 2 submenu "Ver Vehiculos".
     */
    @Override
    public void buscarVehiculosPorTipo(String tipoVehiculo) { //METODO LISTO
        List<Vehiculos> vehiculosPorTipo = new ArrayList<>();
        for (Vehiculos vehiculo : vehiculos) {
            if (vehiculo.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo)) {
                vehiculosPorTipo.add(vehiculo);
            }
        }
        if (vehiculosPorTipo.isEmpty()) {
            System.out.println("No se encontraron vehículos del tipo " + tipoVehiculo + ".");
            return;
        }

        int paginas = 4;
        int pagActual = 0;
        int pagTotal = (int) Math.ceil((double) vehiculosPorTipo.size() / paginas);

        while (true) {
            int inicio = pagActual * paginas;
            int fin = Math.min(inicio + paginas, vehiculosPorTipo.size());

            System.out.println("\nLista de Vehículos del tipo " + tipoVehiculo + "\n- Página " + (pagActual + 1) + " de " + pagTotal);
            for (int i = inicio; i < fin; i++) {
                Vehiculos vehiculoPorTipo = vehiculosPorTipo.get(i);  // Usar vehiculosPorTipo en lugar de vehiculos
                System.out.println("ID: " + vehiculoPorTipo.getId() +
                        "\nMarca: " + vehiculoPorTipo.getMarca() +
                        "\nModelo: " + vehiculoPorTipo.getModelo() +
                        "\nAño: " + vehiculoPorTipo.getAnio() +
                        "\nKilometraje: " + vehiculoPorTipo.getKilometraje() +
                        "\nTipo: " + vehiculoPorTipo.getTipoVehiculo() +
                        "\nAtributo 1: " + vehiculoPorTipo.getAtributo1() +
                        "\nAtributo 2: " + vehiculoPorTipo.getAtributo2());
                System.out.println("--------------------------------------");
            }

            System.out.print("\nOpciones: \n[1] Siguiente página \n[2] Página anterior \n[3] Salir al menú principal: ");
            String opcion = scanner.nextLine().trim().toLowerCase();

            if (opcion.equals("1") && pagActual < pagTotal - 1) {
                pagActual++;
            } else if (opcion.equals("2") && pagActual > 0) {
                pagActual--;
            } else if (opcion.equals("3")) {
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Buscar Vehiculos por Id metodo opción 3 submenu "Ver Vehiculos".
     */
    @Override //METODO LISTO
    public Vehiculos buscarVehiculoporId(int id) {
        for (Vehiculos vehiculo : vehiculos) {
            if (vehiculo.getId() == id) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * Método de opción 2 Menu principal (Ver historial de mantenimiento): Mostrar historial por vehículo
     */
    @Override
    public void mostrarHistorialPorVehiculo(int idVehiculo) { //METODO LISTOCO
        List<Mantenimiento> historialPorVehiculo = new ArrayList<>();

        // Recorrer historialMantenimiento y agregar solo los mantenimientos del vehículo específico
        for (Mantenimiento vehiculo : historialMantenimiento) {
            if (vehiculo.getIdVehiculo() == idVehiculo) {
                historialPorVehiculo.add(vehiculo);
            }
        }

        // Verificar si se encontraron mantenimientos para el vehículo específico
        if (historialPorVehiculo.isEmpty()) {
            System.out.println("No se encontraron mantenimientos para el vehículo con ID: " + idVehiculo);
            return;
        }

        int paginas = 4;
        int pagActual = 0;
        int pagTotal = (int) Math.ceil((double) historialPorVehiculo.size() / paginas);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int inicio = pagActual * paginas;
            int fin = Math.min(inicio + paginas, historialPorVehiculo.size());

            System.out.println("\nLista de Mantenimientos para el Vehículo ID: " + idVehiculo);
            System.out.println("- Página " + (pagActual + 1) + " de " + pagTotal);

            for (int i = inicio; i < fin; i++) {
                Mantenimiento mantenimiento = historialPorVehiculo.get(i);
                System.out.println("ID: " + mantenimiento.getIdVehiculo());
                System.out.println("Fecha: " + mantenimiento.getFecha());
                System.out.println("Tipo: " + mantenimiento.getTipoMantencion());
                System.out.println("Descripción: " + mantenimiento.getDescripcion());
                System.out.println("--------------------------------------");
            }

            System.out.print("\nOpciones:\n[1] Siguiente página\n[2] Página anterior\n[3] Salir al menú principal\nSeleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("1") && pagActual < pagTotal - 1) {
                pagActual++;
            } else if (opcion.equals("2") && pagActual > 0) {
                pagActual--;
            } else if (opcion.equals("3")) {
                break;
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
    /**
     * Método de opción 3, Agregar vehiculo
     */
    @Override
    public void agregarVehiculo() { //HACER VALIDACIONES EN CLASE
        System.out.println("\nAgregar Vehículo:");
        System.out.println("Ingrese la marca del vehículo:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el modelo del vehiculo:");
        String modelo= scanner.nextLine();
        int anio;
        while (true) {
            try {
                System.out.println("Ingrese el año del vehículo:");
                anio = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                if (anio < 1990 || anio > 2024) {
                    System.out.println("Año inválido. Ingrese un año entre 1990 y 2024.");
                    continue;
                }
                break; // Salir del bucle si el año es válido
            } catch (InputMismatchException e) {
                System.out.println("Formato incorrecto. Ingrese un año válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
        // Ingresar el kilometraje del vehículo (validación de kilometraje)
        int kilometraje;
        while (true) {
            try {
                System.out.println("Ingrese el kilometraje del vehículo:");
                kilometraje = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                if (kilometraje < 0) {
                    System.out.println("El kilometraje no puede ser negativo. Ingrese un valor válido.");
                    continue;
                }
                break; // Salir del bucle si el kilometraje es válido
            } catch (InputMismatchException e) {
                System.out.println("Formato incorrecto. Ingrese un valor numérico válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }

        String tipoVehiculo;
        while (true) {
            System.out.println("Ingrese el tipo de vehículo (Automóvil, Motocicleta, Camión o Autobús):");
            tipoVehiculo = scanner.nextLine().toUpperCase();
            if (tipoVehiculo.equals("AUTOMÓVIL") || tipoVehiculo.equals("MOTOCICLETA") ||
                    tipoVehiculo.equals("CAMIÓN") || tipoVehiculo.equals("AUTOBÚS")) {
                break; // Salir del bucle si el tipo de vehículo es válido
            } else {
                System.out.println("Tipo de vehículo inválido. Ingrese uno de los tipos mencionados (con tildes incluidos).");
            }
        }

        // Validar si ya existe un vehículo con la misma ID
        System.out.print("Ingrese la ID del vehículo: ");
        while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar entrada inválida
            System.out.println("Ingrese la ID del vehiculo:");
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Vehiculos existe = buscarVehiculoporId(id);
        if (existe != null) {
            System.out.println("Ya existe un vehículo con esa ID. No se puede agregar.");
        }

        String atributo1 = "";
        String atributo2 = "";

        if (tipoVehiculo.equalsIgnoreCase("AUTOMÓVIL")){
            System.out.println("Ingrese Cantidad de puertas:");
            atributo1 = scanner.nextLine();
            System.out.println("Ingrese tipo de combustible:");
            atributo2 = scanner.nextLine();
        } else if(tipoVehiculo.equalsIgnoreCase("MOTOCICLETA")) {
            System.out.println("Ingrese cilindraje:");
            atributo1 = scanner.nextLine();
            System.out.println("Ingrese tipo de motor:");
            atributo2 = scanner.nextLine();
        } else if (tipoVehiculo.equalsIgnoreCase("CAMIÓN")) {
            System.out.println("Ingrese cantidad de ruedas:");
            atributo1 = scanner.nextLine();
            System.out.println("Ingrese capacidad de carga:");
            atributo2 = scanner.nextLine();
        } else if (tipoVehiculo.equalsIgnoreCase("AUTOBÚS")) {
            System.out.println("Ingrese capacidad de pasajeros:");
            atributo1 = scanner.nextLine();
            System.out.println("Ingrese tipo de servicio:");
            atributo2 = scanner.nextLine();
        } else {
            System.out.println("Tipo de vehículo inválido.");
        }


        Vehiculos vehiculoNuevo = new Vehiculos(id, marca, modelo, anio, kilometraje, tipoVehiculo, atributo1, atributo2);
        vehiculos.add(vehiculoNuevo);
    }
    /**
     * Método de opción 4, Registrar mantenimiento
     */
    @Override
    public void registrarMantenimiento() {
        System.out.println("Registrar mantenimiento:");
        System.out.println("Ingrese id del vehiculo:");
        while (!scanner.hasNextInt()) { //asegurarnos de que el usuario ingrese un número válido
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next(); // Limpiar entrada inválida
            System.out.print("Ingrese el ID del vehículo: ");
        }
        int idVehiculo = scanner.nextInt();
        scanner.nextLine();
        Vehiculos vehiculo = buscarVehiculoporId(idVehiculo);
        if (vehiculo == null) {
            System.out.println("El vehículo con ID " + idVehiculo + " no existe.");
            return;
        }
        System.out.println("Ingrese fecha del mantenimiento:");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese tipo de mantenimiento:");
        String tipoMantenimiento = scanner.nextLine();
        System.out.println("Ingrese descripcion:");
        String descripcion = scanner.nextLine();

        if(validarDatos(fecha, tipoMantenimiento)){
            System.out.println("Ya existe un mantenimiento con el mismo tipo o fecha para este vehiculo");
        }
        else {
            int NuevoId = historialMantenimiento.size() + 1;
            Mantenimiento mantenimientoNuevo = new Mantenimiento(idVehiculo, NuevoId, fecha, tipoMantenimiento, descripcion);
            historialMantenimiento.add(mantenimientoNuevo);
            System.out.println("Mantenimiento agregado exitosamente (!)");
        }
    }
    private boolean validarDatos(String fecha, String tipoMantenimiento){
        for (Mantenimiento mantenimiento : historialMantenimiento){
            if (mantenimiento.getFecha().equalsIgnoreCase(fecha) && mantenimiento.getTipoMantencion().equalsIgnoreCase(tipoMantenimiento)){

                return true;
            }
        }
        return false;
    }

    /**
     * Método de opción 5, Generar boleta mantenimiento
     */
    @Override
    public void generarBoleta() {
        // Solicitar datos para la boleta
        System.out.print("Ingrese el ID del mantenimiento: ");
        int idMantenimiento = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer de entrada

        Mantenimiento mantenimiento = null;
        for (Mantenimiento m : historialMantenimiento) {
            if (m.getIdMantenimiento() == idMantenimiento) {
                mantenimiento = m;
                break;
            }
        }

        if (mantenimiento == null) {
            System.out.println("Mantenimiento no encontrado.");
            return;
        }

        Vehiculos vehiculo = buscarVehiculoporId(mantenimiento.getIdVehiculo());
        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        // Calcular el costo ficticio y el total del mantenimiento considerando el IVA
        double costoFicticio = calcularCostoFicticio(mantenimiento);
        double totalMantenimiento = costoFicticio * 1.19;

        // Crear y almacenar la boleta
        Boleta nuevaBoleta = new Boleta(idMantenimiento, vehiculo.getModelo(), mantenimiento.getTipoMantencion(), mantenimiento.getFecha(), totalMantenimiento);
        listaBoletas.add(nuevaBoleta);

        // Crear archivo de boleta
        String boletaFilename = "boleta_" + idMantenimiento + ".csv";
        Out archivoBoleta = new Out(boletaFilename);
        archivoBoleta.print("Id;Vehiculo;Mantencion;Fecha;Total\n");
        archivoBoleta.print(idMantenimiento + ";" +
                vehiculo.getMarca() + ";" +
                mantenimiento.getTipoMantencion() + ";" +
                mantenimiento.getFecha() + ";" +
                totalMantenimiento + "\n");

        // Eliminar mantenimiento y vehículo asociados a la boleta
        historialMantenimiento.remove(mantenimiento);
        eliminarVehiculo(vehiculo.getId());

        // Mostrar mensaje de confirmación
        System.out.println("Boleta generada exitosamente.");
    }
    private void eliminarVehiculo(int idVehiculo) {
        Vehiculos vehiculoAEliminar = null;
        for (Vehiculos v : vehiculos) {
            if (v.getId() == idVehiculo) {
                vehiculoAEliminar = v;
                break;
            }
        }
        if (vehiculoAEliminar != null) {
            vehiculos.remove(vehiculoAEliminar);
        }
    }

    /**
     * Se decidió calcular un costo ficticio ya que no se especifica que costo se deberia cobrar por cada mantenimiento en el enunciado.
     * @param mantenimiento
     * @return
     */
    private double calcularCostoFicticio(Mantenimiento mantenimiento) {
        // Generar un costo ficticio basado en el tipo de mantención
        switch (mantenimiento.getTipoMantencion().toLowerCase()) {
            case "cambio de aceite":
                return 50.0; // Ejemplo de costo
            case "revisión general":
                return 100.0; // Ejemplo de costo
            case "cambio de neumáticos":
                return 200.0; // Ejemplo de costo
            case "mantenimiento del motor":
                return 300.0; // Ejemplo de costo
            case "actualización de software":
                return 150.0; // Ejemplo de costo
            case "cambio de filtros":
                return 80.0; // Ejemplo de costo
            default:
                return 75.0; // Costo por defecto para otros tipos de mantención
        }
    }


    @Override
    public void listadoDeBoletas() {
        int paginas = 4;
        int pagActual = 0;
        int pagTotal = (int) Math.ceil((double) listaBoletas.size() / paginas);

        while (true) {
            int inicio = pagActual * paginas;
            int fin = Math.min(inicio + paginas, listaBoletas.size());

            System.out.println("\nLista de Boletas\n- Página " + (pagActual + 1) + " de " + pagTotal);
            for (int i = inicio; i < fin; i++) {
                Boleta boleta = listaBoletas.get(i);
                System.out.println("ID: " + boleta.getIdMantenimiento());
                System.out.println("Vehículo: " + boleta.getVehiculo());
                System.out.println("Mantención: " + boleta.getTipoMantencion());
                System.out.println("Fecha: " + boleta.getFecha());
                System.out.println("Total: " + boleta.getTotal());
                System.out.println("--------------------------------------");
            }

            System.out.print("\nOpciones: \n[1] Siguiente página \n[2] Página anterior \n[3] Volver al menú principal: ");
            String opcion = scanner.nextLine().trim().toLowerCase();

            if (opcion.equals("1") && pagActual < pagTotal - 1) {
                pagActual++;
            } else if (opcion.equals("2") && pagActual > 0) {
                pagActual--;
            } else if (opcion.equals("3")) {
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    @Override
    public void SaliryGuardar() {
        //Guardar Vehiculos
        Out outVehiculos = new Out("vehiculos.csv");
        outVehiculos.print("ID,Marca,Modelo,Año,Kilometraje,Tipo,Atributo1,Atributo2\n");
        for (Vehiculos vehiculos1: vehiculos){
            outVehiculos.print(vehiculos1.getId() + "," + vehiculos1.getMarca() + "," + vehiculos1.getModelo() +
                    "," + vehiculos1.getAnio() +
                    "," + vehiculos1.getKilometraje() +
                    "," + vehiculos1.getTipoVehiculo() +
                    "," + vehiculos1.getAtributo1() + "," + vehiculos1.getAtributo2() +"\n");
        }

        //Guardar Mantenimiento
        Out outMantenimientos = new Out("mantenimientos.csv");
        outMantenimientos.print("IDMantenimiento,IDVehiculo,Fecha,TipoMantenimiento,Descripcion\n");
        for (Mantenimiento mantenimiento : historialMantenimiento){
            outMantenimientos.print(mantenimiento.getIdMantenimiento() + ","
                    + mantenimiento.getIdVehiculo() + ","
                    + mantenimiento.getFecha() + ","
                    + mantenimiento.getTipoMantencion() + ","
                    + mantenimiento.getDescripcion() +"\n");
        }


    }
}
