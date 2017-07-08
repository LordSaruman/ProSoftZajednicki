/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author filip_000
 */
public class ServerskiOdgovor implements Serializable{
    
    private Object odgovor;
    private StatusZahteva statusZahteva;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, StatusZahteva statusZahteva) {
        this.odgovor = odgovor;
        this.statusZahteva = statusZahteva;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public StatusZahteva getStatusZahteva() {
        return statusZahteva;
    }

    public void setStatusZahteva(StatusZahteva statusZahteva) {
        this.statusZahteva = statusZahteva;
    }
    
}
