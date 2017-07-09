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
public class Rezultat implements OpstiDomenskiObjekat {

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
                int idRezultat = rs.getInt("idRezultat");
                String rezultatS = rs.getString("Rezultat");
                int idTurnira = rs.getInt("tu.idTurnira");
                int idTima = rs.getInt("t.idTima");

                //korisnik
                int idKorisnika = rs.getInt("k.idKorisnika");
                String imeKorisnika = rs.getString("k.imeKorisnika");
                String prezimeKorisnika = rs.getString("k.prezimeKorisnika");
                String username = rs.getString("k.username");
                String password = rs.getString("k.password");
                Korisnik korisnikDB = new Korisnik(idKorisnika, imeKorisnika, prezimeKorisnika, username, password);

                //turnir
                Turnir turnirDB = new Turnir();
                turnirDB.setIdTurnira(idTurnira);
                turnirDB.setNaziv(rs.getString("tu.naziv"));

                //tim
                Tim timDB = new Tim();
                timDB.setIdTima(idTima);
                timDB.setNaziv(rs.getString("t.naziv"));

                Rezultat rezultatDB = new Rezultat(idRezultat, timDB, turnirDB, rezultatS, korisnikDB);

                listaRezultata.add(rezultatDB);
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
        return "rezultat r JOIN tim t ON r.`idTima`=t.`idTima` JOIN turnir tu ON r.`idTurnira`=tu.`idTurnira` JOIN korisnik k ON r.`idKorisnika`=k.`idKorisnika`";
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
