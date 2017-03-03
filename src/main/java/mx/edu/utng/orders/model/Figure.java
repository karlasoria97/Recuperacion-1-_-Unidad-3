package mx.edu.utng.orders.model;

/**
 * Created by Karla on 02/03/2017.
 */

public class Figure {
    private String idFigure;
    private String teo;
    private String code;
    private String drawer;
    private String figure;
    private String user;
    private String bibref;
    private String dateSubmission;

    public Figure(String idFigure, String teo, String code, String drawer, String figure, String user, String bibref, String dateSubmission) {
        this.idFigure = idFigure;
        this.teo = teo;
        this.code = code;
        this.drawer = drawer;
        this.figure = figure;
        this.user = user;
        this.bibref = bibref;
        this.dateSubmission = dateSubmission;
    }

    public Figure() {
        this("","","","","","","","");
    }

    public String getIdFigure() {
        return idFigure;
    }

    public void setIdFigure(String idFigure) {
        this.idFigure = idFigure;
    }

    public String getTeo() {
        return teo;
    }

    public void setTeo(String teo) {
        this.teo = teo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBibref() {
        return bibref;
    }

    public void setBibref(String bibref) {
        this.bibref = bibref;
    }

    public String getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(String dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "idFigure='" + idFigure + '\'' +
                ", teo='" + teo + '\'' +
                ", code='" + code + '\'' +
                ", drawer='" + drawer + '\'' +
                ", figure='" + figure + '\'' +
                ", user='" + user + '\'' +
                ", bibref='" + bibref + '\'' +
                ", dateSubmission='" + dateSubmission + '\'' +
                '}';
    }
}
