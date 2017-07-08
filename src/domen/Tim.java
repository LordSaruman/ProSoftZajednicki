/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip_000
 */
public class Tim implements OpstiDomenskiObjekat {

    private int idTima;
    private String naziv;
    private String trener;
    private String menadzer;
    private String sponzor;
    private String igre;
    private Double zaradjenNovac;
    private Region region;
    private Lokacija lokacije;

    public Tim() {
    }

    public Tim(int idTima, String naziv, String trener, String menadzer, String sponzor, String igre, Double zaradjenNovac, Region region, Lokacija lokacije) {
        this.idTima = idTima;
        this.naziv = naziv;
        this.trener = trener;
        this.menadzer = menadzer;
        this.sponzor = sponzor;
        this.igre = igre;
        this.zaradjenNovac = zaradjenNovac;
        this.region = region;
        this.lokacije = lokacije;
    }

    public Lokacija getLokacije() {
        return lokacije;
    }

    public void setLokacije(Lokacija lokacije) {
        this.lokacije = lokacije;
    }

    public int getIdTima() {
        return idTima;
    }

    public void setIdTima(int idTima) {
        this.idTima = idTima;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }

    public String getSponzor() {
        return sponzor;
    }

    public void setSponzor(String sponzor) {
        this.sponzor = sponzor;
    }

    public String getIgre() {
        return igre;
    }

    public void setIgre(String igre) {
        this.igre = igre;
    }

    public Double getZaradjenNovac() {
        return zaradjenNovac;
    }

    public void setZaradjenNovac(Double zaradjenNovac) {
        this.zaradjenNovac = zaradjenNovac;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "tim";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%s','%s','%s','%s',%f,%d,%d",
               naziv, trener, menadzer, sponzor, igre, zaradjenNovac, region.getIdRegiona(), lokacije.getIdLokacije());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaTrenera = new ArrayList<>();
        try {
            while (rs.next()) {
                int idTima = rs.getInt("idTima");
                String naziv = rs.getString("naziv");
                String trener = rs.getString("trener");
                String menadzer = rs.getString("menadzer");
                String sponzor = rs.getString("sponzor");
                String igre = rs.getString("igre");
                double zaradjenNovac = rs.getDouble("zaradjenNovac");
                
                int idRegiona = rs.getInt("r.idRegiona");
                String nazivRegiona = rs.getString("r.nazivRegiona");
                Region region = new Region(idRegiona, nazivRegiona);
                
                int idLokacije = rs.getInt("l.idLokacije");
                String nazivLokacije = rs.getString("l.nazivLokacije");
                Lokacija lokacija = new Lokacija(idLokacije, region, nazivLokacije);
                
                Tim tim = new Tim(idTima, naziv, trener, menadzer, sponzor, igre, zaradjenNovac, region, lokacija);
                
                listaTrenera.add(tim);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTrenera;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv=" + "'" + this.getNaziv() + "'" + "," + "trener=" + "'" + this.getTrener() + "'" + ","
                + "meneadzer=" + "'" + this.getMenadzer() + "'" + "," + "sponzor=" + "'" + this.getSponzor() + "'" + ","
                + "igre=" + "'" + this.getIgre() + "'" + "," + "zaradjenNovac=" + "'" + this.getZaradjenNovac() + "'" + ","
                + "idRegiona=" + "'" + this.getRegion().getIdRegiona() + "'" + "," + "idLokacije=" + "'" + this.getLokacije().getIdLokacije();
    }

    @Override
    public String vratiJoin() {
        return "tim t JOIN `region` r ON t.`idRegiona` = r.`idRegiona` JOIN `lokacija` l ON t.`idLokacije` = l.`idLokacije`\n";
    }

    @Override
    public String vratiSelect() {
        return "t.`idTima`, t.`igre`, t.`menadzer`, t.`naziv`, t.`sponzor`, t.`trener`, t.`zaradjenNovac`, r.`idRegiona` AS `idRegiona`, l.`idLokacije` AS `idLokacije`\n";
    }

    @Override
    public String vratiWhere() {
        return "idTima=" + this.getIdTima();
    }

}
