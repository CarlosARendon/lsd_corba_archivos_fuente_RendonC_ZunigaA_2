/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorDeAlertas.utilidades;

/**
 *
 * @author Andrés Zúñiga Garzón -- hazgarzon@gmail.com
 */
public class IndicadoresMedicos {
    
    //True indica que existe un resultado anormal - false que esta bien
    private boolean estado;
    
    //Los atributos máximos, indican el rango desde el valor mínimo a capturar
    private int freCardicaMin, freCardiacaMax, pArtSistolicaMin, pArtSistolicaMax, 
            pArtDiastolicaMin, pArtDiastolicaMax, freRespitariaMin, freRespiratoriaMax,
            temperaturaMin, temperaturaMax, satOxigenoMin, satOxigenoMax;
    
    public IndicadoresMedicos() {
        this.estado = false;
        this.freCardicaMin = 50;
        this.freCardiacaMax = 100;
        this.pArtSistolicaMin = 60;
        this.pArtSistolicaMax = 90;
        this.pArtDiastolicaMin = 40;
        this.pArtDiastolicaMax = 60;
        this.freRespitariaMin = 10;
        this.freRespiratoriaMax = 40;
        this.temperaturaMin = 35;
        this.temperaturaMax = 5;
        this.satOxigenoMin = 80;
        this.satOxigenoMax = 40;
    }

    public int getFreCardicaMin() {
        return freCardicaMin;
    }

    public void setFreCardicaMin(int freCardicaMin) {
        this.freCardicaMin = freCardicaMin;
    }

    public int getFreCardiacaMax() {
        return freCardiacaMax;
    }

    public void setFreCardiacaMax(int freCardiacaMax) {
        this.freCardiacaMax = freCardiacaMax;
    }

    public int getpArtSistolicaMin() {
        return pArtSistolicaMin;
    }

    public void setpArtSistolicaMin(int pArtSistolicaMin) {
        this.pArtSistolicaMin = pArtSistolicaMin;
    }

    public int getpArtSistolicaMax() {
        return pArtSistolicaMax;
    }

    public void setpArtSistolicaMax(int pArtSistolicaMax) {
        this.pArtSistolicaMax = pArtSistolicaMax;
    }

    public int getpArtDiastolicaMin() {
        return pArtDiastolicaMin;
    }

    public void setpArtDiastolicaMin(int pArtDiastolicaMin) {
        this.pArtDiastolicaMin = pArtDiastolicaMin;
    }

    public int getpArtDiastolicaMax() {
        return pArtDiastolicaMax;
    }

    public void setpArtDiastolicaMax(int pArtDiastolicaMax) {
        this.pArtDiastolicaMax = pArtDiastolicaMax;
    }

    public int getFreRespitariaMin() {
        return freRespitariaMin;
    }

    public void setFreRespitariaMin(int freRespitariaMin) {
        this.freRespitariaMin = freRespitariaMin;
    }

    public int getFreRespiratoriaMax() {
        return freRespiratoriaMax;
    }

    public void setFreRespiratoriaMax(int freRespiratoriaMax) {
        this.freRespiratoriaMax = freRespiratoriaMax;
    }

    public int getTemperaturaMin() {
        return temperaturaMin;
    }

    public void setTemperaturaMin(int temperaturaMin) {
        this.temperaturaMin = temperaturaMin;
    }

    public int getTemperaturaMax() {
        return temperaturaMax;
    }

    public void setTemperaturaMax(int temperaturaMax) {
        this.temperaturaMax = temperaturaMax;
    }

    public int getSatOxigenoMin() {
        return satOxigenoMin;
    }

    public void setSatOxigenoMin(int satOxigenoMin) {
        this.satOxigenoMin = satOxigenoMin;
    }

    public int getSatOxigenoMax() {
        return satOxigenoMax;
    }

    public void setSatOxigenoMax(int satOxigenoMax) {
        this.satOxigenoMax = satOxigenoMax;
    }
    
    
    
    /**
     * 
     * @param edad
     * @param freCardiaca
     * @return 
     */
    public boolean medirFreCardiaca(double edad, double freCardiaca){
        this.estado = false;
        
        if(edad <= 0.6){
            if(freCardiaca < 120 || freCardiaca > 140)
                this.estado = true;
        }
        else if (edad < 1){
            if(freCardiaca < 100 || freCardiaca > 130)
                this.estado = true;
        }
        else if (edad < 2){
            if(freCardiaca < 100 || freCardiaca > 120)
                this.estado = true;
        }
        else if (edad < 6){
            if(freCardiaca < 80 || freCardiaca > 120)
                this.estado = true;
        }
        else if (edad < 13){
            if(freCardiaca < 80 || freCardiaca > 100)
                this.estado = true;
        }
        else if (edad < 16){
            if(freCardiaca < 70 || freCardiaca > 80)
                this.estado = true;
        }
        else {
            if(freCardiaca < 60 || freCardiaca > 80)
                this.estado = true;
        }
        
        return estado;
    }
    
    /**
     * 
     * @param edad
     * @param pArtSistolica
     * @param pArtDiastolica
     * @return 
     */
    public boolean medirPresionArterial(double edad, double pArtSistolica, double pArtDiastolica){
        this.estado = false;
        
        if(edad <= 0.6){
            if(pArtSistolica < 70 || pArtSistolica > 100 || pArtDiastolica < 50 || pArtDiastolica > 68)
                this.estado = true;
        }
        else if (edad < 1){
            if(pArtSistolica < 84 || pArtSistolica > 106 || pArtDiastolica < 56 || pArtDiastolica > 70)
                this.estado = true;
        }
        else if (edad < 2){
            if(pArtSistolica < 98 || pArtSistolica > 106 || pArtDiastolica < 58 || pArtDiastolica > 70)
                this.estado = true;
        }
        else if (edad < 6){
            if(pArtSistolica < 99 || pArtSistolica > 112 || pArtDiastolica < 64 || pArtDiastolica > 70)
                this.estado = true;
        }
        else if (edad < 13){
            if(pArtSistolica < 104 || pArtSistolica > 124 || pArtDiastolica < 64 || pArtDiastolica > 86)
                this.estado = true;
        }
        else if (edad < 16){
            if(pArtSistolica < 118 || pArtSistolica > 132 || pArtDiastolica < 70 || pArtDiastolica > 82)
                this.estado = true;
        }
        else {
            if(pArtSistolica < 110 || pArtSistolica > 140 || pArtDiastolica < 70 || pArtDiastolica > 90)
                this.estado = true;
        }
        
        return estado;
    }
    
    /**
     * 
     * @param edad
     * @param freRespiratoria
     * @return 
     */
    public boolean medirFreRespiratoria(double edad, double freRespiratoria){
        this.estado = false;
        
        if(edad <= 0.6){
            if(freRespiratoria < 40 || freRespiratoria > 45)
                this.estado = true;
        }
        else if (edad < 6){
            if(freRespiratoria < 20 || freRespiratoria > 30)
                this.estado = true;
        }
        else {
            if(freRespiratoria < 12 || freRespiratoria > 20)
                this.estado = true;
        }
        
        return estado;
    }
    
    /**
     * 
     * @param edad
     * @param temperatura
     * @return 
     */
    public boolean medirTemperatura(double edad, double temperatura){
        this.estado = false;
        
        if(edad <= 0.6){
            if(temperatura != 38)
                this.estado = true;
        }
        else if (edad < 6){
            if(temperatura < 37.5 || temperatura > 37.8)
                this.estado = true;
        }
        else if (edad < 13){
            if(temperatura < 37 || temperatura > 37.5)
                this.estado = true;
        }
        else if (edad < 16){
            if(temperatura != 37)
                this.estado = true;
        }
        else {
            if(temperatura < 36.2 || temperatura > 37.2)
                this.estado = true;
        }
        
        return estado;
    }
    
    /**
     * 
     * @param satOxigeno
     * @return 
     */
    public boolean medirSatOxigeno(double satOxigeno){
        this.estado = false;
        
        if(satOxigeno < 90 || satOxigeno > 100)
            this.estado = true;
        
        return estado;
    }
    
    /**
     * 
     * @param edad
     * @param freCardiaca
     * @param preArtSis
     * @param preArtDia
     * @param freRespiratoria
     * @param temperatura
     * @param satOxigeno
     * @return 
     */
    public boolean evaluarIndicadores(double edad, double freCardiaca, double preArtSis, double preArtDia, double freRespiratoria, double temperatura, double satOxigeno ){
        boolean resultado = false;
        
        if (medirFreCardiaca(edad,freCardiaca))
            resultado = true;
        
        if (medirPresionArterial(edad, preArtSis, preArtDia))
            resultado = true;
        
        if (medirFreRespiratoria(edad, freRespiratoria))
            resultado = true;
        
        if (medirTemperatura(edad, temperatura))
            resultado = true;
        
        if (medirSatOxigeno(satOxigeno))
            resultado = true;
        
        return resultado;
    }
    
}
