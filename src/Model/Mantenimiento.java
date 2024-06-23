package Model;



public class Mantenimiento {
    private int idMantenimiento;
    private int idVehiculo;
    private String fecha;
    private String tipoMantencion; //No se si hacer el tipo de mantencion STRING o ENUM
    private String descripcion;


    public Mantenimiento(int idMantenimiento, int idVehiculo, String fecha, String tipoMantencion, String descripcion) {

        this.idVehiculo = idVehiculo;
        this.fecha = fecha;
        this.tipoMantencion = tipoMantencion;
        this.descripcion = descripcion;


        // validacion
        if ( idMantenimiento == 0) {
            throw new IllegalArgumentException("No se permite que el primer valor de ID sea cero.");
        }
        this.idMantenimiento = idMantenimiento;

    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoMantencion() {
        return tipoMantencion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
