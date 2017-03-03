package mx.edu.utng.orders.model;

/**
 * Created by Karla on 01/03/2017.
 */

public class Vehiculo {
    private String idVehiculo;
    private String nroPlaca;
    private String descripcion;
    private String numAsientos;
    private String pesoVeh;
    private String estadoVeh;
    private String cargaMax;
    private int yearFab;
    private int yearAdq;


    public Vehiculo(String idVehiculo, String nroPlaca, String descripcion, String numAsientos, String pesoVeh, String estadoVeh, String cargaMax, int yearFab, int yearAdq) {
        this.idVehiculo = idVehiculo;
        this.nroPlaca = nroPlaca;
        this.descripcion = descripcion;
        this.numAsientos = numAsientos;
        this.pesoVeh = pesoVeh;
        this.estadoVeh = estadoVeh;
        this.cargaMax = cargaMax;
        this.yearFab = yearFab;
        this.yearAdq = yearAdq;
    }

    public Vehiculo() {
        this("","","","","","","",0,0);
    }

    public String getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(String idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNroPlaca() {
        return nroPlaca;
    }

    public void setNroPlaca(String nroPlaca) {
        this.nroPlaca = nroPlaca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumAsientos() {
        return numAsientos;
    }

    public void setNumAsientos(String numAsientos) {
        this.numAsientos = numAsientos;
    }

    public String getPesoVeh() {
        return pesoVeh;
    }

    public void setPesoVeh(String pesoVeh) {
        this.pesoVeh = pesoVeh;
    }

    public String getEstadoVeh() {
        return estadoVeh;
    }

    public void setEstadoVeh(String estadoVeh) {
        this.estadoVeh = estadoVeh;
    }

    public String getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(String cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getYearFab() {
        return yearFab;
    }

    public void setYearFab(int yearFab) {
        this.yearFab = yearFab;
    }

    public int getYearAdq() {
        return yearAdq;
    }

    public void setYearAdq(int yearAdq) {
        this.yearAdq = yearAdq;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "idVehiculo='" + idVehiculo + '\'' +
                ", nroPlaca='" + nroPlaca + '\'' +
                ", description='" + descripcion + '\'' +
                ", numAsientos='" + numAsientos + '\'' +
                ", pesoVeh='" + pesoVeh + '\'' +
                ", estadoVeh='" + estadoVeh + '\'' +
                ", cargaMax='" + cargaMax + '\'' +
                ", yearFab=" + yearFab +
                ", yearAdq=" + yearAdq +
                '}';
    }
}

