package Model;

public class Vehiculos {
    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometraje;
    private String tipoVehiculo;
    private String atributo1;
    private String atributo2;

    public Vehiculos(int id, String marca, String modelo,int anio,int kilometraje, String tipoVehiculo,String atributo1,String atributo2){
        this.marca=marca;
        this.modelo=modelo;
        this.anio=anio;
        this.kilometraje=kilometraje;
        this.tipoVehiculo=tipoVehiculo;
        this.atributo1=atributo1;
        this.atributo2=atributo2;

        // validacion
        if (id == 0) {
            throw new IllegalArgumentException("No se permite que el primer valor de ID sea cero.");
        }
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getAtributo1() {
        return atributo1;
    }

    public String getAtributo2() {
        return atributo2;
    }

}
