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
public class Korisnik implements OpstiDomenskiObjekat{
    
    private int idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String username;
    private String password;

    public Korisnik() {
    }

    public Korisnik(int idKorisnika, String imeKorisnika, String prezimeKorisnika, String username, String password) {
        this.idKorisnika = idKorisnika;
        this.imeKorisnika = imeKorisnika;
        this.prezimeKorisnika = prezimeKorisnika;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public String getImeKorisnika() {
        return imeKorisnika;
    }

    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    public String getPrezimeKorisnika() {
        return prezimeKorisnika;
    }

    public void setPrezimeKorisnika(String prezimeKorisnika) {
        this.prezimeKorisnika = prezimeKorisnika;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL, '%s', '%s', '%s', '%s'", imeKorisnika, prezimeKorisnika, username, password);
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> listaK = new ArrayList<>();
        try {
            while (rs.next()) {
                int idKorisnika = rs.getInt("idKorisnika");
                String imeKorisnika = rs.getString("imeKorisnika");
                String prezimeKorisnika = rs.getString("prezimeKorisnika");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Korisnik korisnik = new Korisnik(idKorisnika, imeKorisnika, prezimeKorisnika, username, password);
                listaK.add(korisnik);
            }
        } catch (SQLException e) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaK;
    
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

    @Override
    public String toString() {
        return username;
    }
    
    
}
