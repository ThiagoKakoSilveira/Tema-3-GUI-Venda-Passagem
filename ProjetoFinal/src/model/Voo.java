package model;

import java.util.Date;
import util.DateUtil;

public class Voo {

    private int codigo;
    private Ponte_Aerea ponte;
    private Date horarioDoVoo;
    private Aviao Aviao;

    public Ponte_Aerea getPonte() {
        return ponte;
    }

    public void setPonte(Ponte_Aerea ponte) {
        this.ponte = ponte;
    }

    public Date getHorarioDoVoo() {
        return horarioDoVoo;
    }

    public void setHorarioDoVoo(Date horarioDoVoo) {
        this.horarioDoVoo = horarioDoVoo;
    }

    public Aviao getAviao() {
        return Aviao;
    }

    public void setAviao(Aviao Aviao) {
        this.Aviao = Aviao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Voo(Ponte_Aerea ponte, Date horarioDoVoo, Aviao Aviao) {
        this.ponte = ponte;
        this.horarioDoVoo = horarioDoVoo;
        this.Aviao = Aviao;
    }

    public Voo(int codigo, Ponte_Aerea ponte, Date horarioDoVoo, Aviao Aviao) {
        this.codigo = codigo;
        this.ponte = ponte;
        this.horarioDoVoo = horarioDoVoo;
        this.Aviao = Aviao;
    }

    public Cidade getOrigem() {
     return this.ponte.getOrigem();
     }

     public void setOrigem(Cidade origem) {
     this.ponte.setOrigem(origem);
     }

     public Cidade getDestino() {
     return this.ponte.getDestino();
     }

     public void setDestino(Cidade destino) {
     this.ponte.setDestino(destino);
     }
     
    @Override
    public String toString() {
        String data = DateUtil.dateToString(this.horarioDoVoo);
        String hora = DateUtil.getHourFromDateToString(this.horarioDoVoo);
        
        return "Código: " + this.codigo;
    }
}
