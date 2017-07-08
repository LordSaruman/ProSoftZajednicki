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

/**
 *
 * @author filip_000
 */
public class Dostignuca implements OpstiDomenskiObjekat{
    
    private int idDostignuca;
    private Tim tim;
    private String mesto;
    private Double nagrada;
    private String pozicija;
    private Turnir turnir;

    public Dostignuca() {
    }

    public Dostignuca(int idDostignuca, Tim tim, String mesto, Double nagrada, String pozicija, Turnir turnir) {
        this.idDostignuca = idDostignuca;
        this.tim = tim;
        this.mesto = mesto;
        this.nagrada = nagrada;
        this.pozicija = pozicija;
        this.turnir = turnir;
    }

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public int getIdDostignuca() {
        return idDostignuca;
    }

    public void setIdDostignuca(int idDostignuca) {
        this.idDostignuca = idDostignuca;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Double getNagrada() {
        return nagrada;
    }

    public void setNagrada(Double nagrada) {
        this.nagrada = nagrada;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public String vratiNazivTabele() {
        return "dostignuca";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> listaDostignuca = new ArrayList<>();
        
        while(rs.next()){
            int idDostinuga = rs.getInt("idDostignuca");
            //int idTima
            String mesto = rs.getString("mesto");
            double nagrada = rs.getDouble("nagrada");
            String pozicija = rs.getString("pozicija");
            //int idTurnira
        }
        return listaDostignuca;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoin() {
        return "dostignuca d JOIN tim t ON d.`idTima`=t.`idTima` JOIN turnir tu ON d.`idTurnira` = tu.`idTurnira`\n";
    }

    @Override
    public String vratiSelect() {
        return "d.idDostignuca,t.idTima AS idTima,d.mesto,d.nagrada,d.pozicija,tu.idTurnira AS idTurnira\n";
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
