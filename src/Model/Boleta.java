package Model;
public class Boleta {
    private int idMantenimiento;
    private String vehiculo;
    private String tipoMantencion;
    private String fecha;
    private double total;

    public Boleta(int idMantenimiento, String vehiculo, String tipoMantencion, String fecha, double total) {
        this.idMantenimiento = idMantenimiento;
        this.vehiculo = vehiculo;
        this.tipoMantencion = tipoMantencion;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdMantenimiento() { return idMantenimiento; }
    public String getVehiculo() { return vehiculo; }
    public String getTipoMantencion() { return tipoMantencion; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }
}
