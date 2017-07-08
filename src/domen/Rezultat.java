/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip_000
 */
public class Rezultat implements OpstiDomenskiObjekat{
    
    private int idRezultat;
    private Tim tim;
    private Turnir turnir;
    private String rezultat;
    private Korisnik korisnik;

    public Rezultat() {
    }

    public Rezultat(int idRezultat, Tim tim, Turnir turnir, String rezultat, Korisnik korisnik) {
        this.idRezultat = idRezultat;
        this.tim = tim;
        this.turnir = turnir;
        this.rezultat = rezultat;
        this.korisnik = korisnik;
    }


    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }
    
    public int getIdRezultat() {
        return idRezultat;
    }

    public void setIdRezultat(int idRezultat) {
        this.idRezultat = idRezultat;
    }

    @Override
    public String vratiNazivTabele() {
        return "rezultat";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,%d,%d,'%s',%d",
               tim.getIdTima(), turnir.getIdTurnira(), rezultat, korisnik.getIdKorisnika());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaRezultata = new ArrayList<>();
        try {
            while (rs.next()) {
                int idRezultat = rs.getInt("idRezultata");
                String rezultatS = rs.getString("Rezultat");
                int idTurnira = rs.getInt("tu.idTurnira");
                int idTima = rs.getInt("t.idTima");
                
                //korisnik
                int idKorisnika = rs.getInt("k.idKorisnika");
                String imeKorisnika = rs.getString("k.imeKorisnika");
                String prezimeKorisnika = rs.getString("k.prezimeKorisnika");
                String username = rs.getString("k.username");
                String password = rs.getString("k.password");
                Korisnik korisnik = new Korisnik(idKorisnika, imeKorisnika, prezimeKorisnika, username, password);
                
                //turnir
                List<OpstiDomenskiObjekat> listaTurnira = new ArrayList<>();
                Turnir turnir = null;
                Turnir t = new Turnir();
                listaTurnira = t.vratiListu(rs);
                
                for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaTurnira) {
                    if (((Turnir)opstiDomenskiObjekat).getIdTurnira() == idTurnira) {
                        turnir = (Turnir) opstiDomenskiObjekat;
                    }
                }
                
                //tim
                List<OpstiDomenskiObjekat> listaTimova = new ArrayList<>();
                Tim tim = null;
                Tim ti = new Tim();
                listaTimova = ti.vratiListu(rs);
                
                for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaTurnira) {
                    if (((Tim)opstiDomenskiObjekat).getIdTima() == idTima) {
                        tim = (Tim) opstiDomenskiObjekat;
                    }
                }
                
                Rezultat rezultat = new Rezultat(idRezultat, tim, turnir, rezultatS, korisnik);
               
                listaRezultata.add(rezultat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRezultata;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return vratiNazivTabele();
    }

    @Override
    public String vratiJoin() {
        return "tim t ON r.`idTima`=t.`idTima` JOIN turnir tu ON r.`idTurnira`=tu.`idTurnira` JOIN korisnik k ON r.`idKorisnika`=k.`idKorisnika`";
    }

    @Override
    public String vratiSelect() {
        return "r.`idRezultat`,t.`idTima` AS idTima, tu.`idTurnira` AS idTurnira, k.`idKorisnika` AS idKorisnika, r.`Rezultat`";
    }

    @Override
    public String vratiWhere() {
        return "idRezultata=" + this.getRezultat();
    }

    
    
    
}
