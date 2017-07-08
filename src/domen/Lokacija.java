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
public class Lokacija implements OpstiDomenskiObjekat{
    
    private int idLokacije;
    private Region Region;
    private String nazivLokacije;

    public Lokacija() {
    }

    public Lokacija(int idLokacije, Region Region, String nazivLokacije) {
        this.idLokacije = idLokacije;
        this.Region = Region;
        this.nazivLokacije = nazivLokacije;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public int getIdLokacije() {
        return idLokacije;
    }

    public void setIdLokacije(int idLokacije) {
        this.idLokacije = idLokacije;
    }

    public Region getRegion() {
        return Region;
    }

    public void setRegion(Region Region) {
        this.Region = Region;
    }

    @Override
    public String toString() {
        return nazivLokacije;
    }

    @Override
    public String vratiNazivTabele() {
        return "lokacija";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> listaLokacija = new ArrayList<>();

        String sql = "SELECT * FROM ";
        try {
            while (rs.next()) {
                int idRegiona = rs.getInt("r.idRegiona");
                String nazivRegiona = rs.getString("r.nazivRegiona");
                Region r = new Region(idRegiona, nazivRegiona);
                
                int idLokacije = rs.getInt("l.idLokacije");
                String nazivLokacije = rs.getString("l.nazivLokacije");
                
                Lokacija lokacija = new Lokacija(idLokacije, r, nazivLokacije);
                listaLokacija.add(lokacija);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lokacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLokacija;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoin() {
        return "`lokacija` l JOIN `region` r ON l.`idRegiona` = r.`idRegiona`\n";
    }

    @Override
    public String vratiSelect() {
        return "l.idLokacije, r.idRegiona AS idRegiona, l.nazivLokacije\n";
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
