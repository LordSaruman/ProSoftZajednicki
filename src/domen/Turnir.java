/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip_000
 */
public class Turnir implements OpstiDomenskiObjekat{
    
    private int idTurnira;
    private Date pocetak;
    private Date kraj;
    private String naziv;
    private Double nagradniFond;
    private Serija serija;
    private Lokacija lokacija;
    private Region region;

    public Turnir() {
    }

    public Turnir(int idTurnira, Date pocetak, Date kraj, String naziv, Double nagradniFond, Serija serija, Lokacija lokacija, Region region) {
        this.idTurnira = idTurnira;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.naziv = naziv;
        this.nagradniFond = nagradniFond;
        this.serija = serija;
        this.lokacija = lokacija;
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getIdTurnira() {
        return idTurnira;
    }

    public void setIdTurnira(int idTurnira) {
        this.idTurnira = idTurnira;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getNagradniFond() {
        return nagradniFond;
    }

    public void setNagradniFond(Double nagradniFond) {
        this.nagradniFond = nagradniFond;
    }

    public Serija getSerija() {
        return serija;
    }

    public void setSerija(Serija serija) {
        this.serija = serija;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "turnir";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("NULL,'%s','%s','%s',%d,%d,%d,%d",
               sdf.format(pocetak), sdf.format(kraj), naziv, nagradniFond, serija.getIdSerije(), region.getIdRegiona(), lokacija.getIdLokacije());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaTurnira = new ArrayList<>();
        try {
            while (rs.next()) {
                int idTurnira = rs.getInt("idTurnira");
                Date pocetak = rs.getDate("pocetak");
                Date kraj = rs.getDate("kraj");
                String naziv = rs.getString("naziv");
                double nagradniFond = rs.getDouble("nagradniFond");
                
                //serija
                int idSerije = rs.getInt("s.idSerije");
                String nazivSerije = rs.getString("nazivSerije");
                Serija serija = new Serija(idSerije, nazivSerije);
                
                //region
                int idRegiona = rs.getInt("r.idRegiona");
                String nazivRegiona = rs.getString("r.nazivRegiona");
                Region region = new Region(idRegiona, nazivRegiona);
                
                //lokacija
                int idLokacije = rs.getInt("l.idLokacije");
                String nazivLokacije = rs.getString("l.nazivLokacije");
                Lokacija lokacija = new Lokacija(idLokacije, region, nazivLokacije);

                Turnir turnir = new Turnir(idTurnira, pocetak, kraj, naziv, nagradniFond, serija, lokacija, region);
                listaTurnira.add(turnir);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTurnira;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "pocetak=" + "'" + this.getPocetak() + "'" + "," + "kraj=" + "'" + this.getKraj() + "'" + ","
                + "naziv=" + "'" + this.getNaziv() + "'" + "," + "nagradniFodn=" + "'" + this.getNagradniFond() + "'" + ","
                + "idSerije=" + "'" + this.getSerija().getIdSerije() + "'" + "," + "idLokacije=" + "'" + this.getLokacija().getIdLokacije() + "'" + ","
                + "idRegiona=" + "'" + this.getRegion().getIdRegiona();
    }

    @Override
    public String vratiJoin() {
        return "turnir t JOIN serija s ON t.`idSerije` = s.`idSerije` JOIN lokacija l ON t.`idLokacije` = l.`idLokacije` JOIN region r ON t.`idRegiona` = r.`idRegiona`";
    }

    @Override
    public String vratiSelect() {
        return "`idTurnira`, `pocetak`, `kraj`, `naziv`, `nagradniFond`, s.`idSerije` AS idSerije, l.`idLokacije` AS `idLokacije`, r.`idRegiona` AS idRegiona";
    }

    @Override
    public String vratiWhere() {
        return "idTurnira=" + this.getIdTurnira();
    }
    
    
}
