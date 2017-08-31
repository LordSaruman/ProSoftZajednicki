/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip_000
 */
public class Region implements OpstiDomenskiObjekat, Serializable {

    private int idRegiona;
    private String nazivRegiona;

    public Region() {
    }

    public Region(int idRegiona, String nazivRegiona) {
        this.idRegiona = idRegiona;
        this.nazivRegiona = nazivRegiona;
    }

    public String getNazivRegiona() {
        return nazivRegiona;
    }

    public void setNazivRegiona(String nazivRegiona) {
        this.nazivRegiona = nazivRegiona;
    }

    public int getIdRegiona() {
        return idRegiona;
    }

    public void setIdRegiona(int idRegiona) {
        this.idRegiona = idRegiona;
    }

    @Override
    public String toString() {
        return nazivRegiona;
    }

    @Override
    public String vratiNazivTabele() {
        return "region";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> listaRegiona = new ArrayList<>();

        while (rs.next()) {
            int idRegiona = rs.getInt("idRegiona");
            String nazivRegiona = rs.getString("nazivRegiona");

            Region r = new Region(idRegiona, nazivRegiona);
            listaRegiona.add(r);
        }
        return listaRegiona;
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
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Region other = (Region) obj;
        if (this.idRegiona != other.idRegiona) {
            return false;
        }
        return true;
    }

    
}
