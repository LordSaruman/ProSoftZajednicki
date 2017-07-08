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
public class Serija implements OpstiDomenskiObjekat{
    
    private int idSerije;
    private String nazivSerije;

    public Serija() {
    }

    public Serija(int idSerije, String nazivSerije) {
        this.idSerije = idSerije;
        this.nazivSerije = nazivSerije;
    }

    public String getNazivSerije() {
        return nazivSerije;
    }

    public void setNazivSerije(String nazivSerije) {
        this.nazivSerije = nazivSerije;
    }

    public int getIdSerije() {
        return idSerije;
    }

    public void setIdSerije(int idSerije) {
        this.idSerije = idSerije;
    }

    @Override
    public String toString() {
        return nazivSerije;
    }

    @Override
    public String vratiNazivTabele() {
        return "serija";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaSerija = new ArrayList<>();
        try {
            while (rs.next()) {
                int idSerije = rs.getInt("idSerije");
                String nazivSerije = rs.getString("nazivSerije");
                Serija serija = new Serija(idSerije, nazivSerije);
                listaSerija.add(serija);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Serija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSerija;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoin() {
        return vratiNazivTabele();
    }

    @Override
    public String vratiSelect() {
        return "*";
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
