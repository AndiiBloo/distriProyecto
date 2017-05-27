/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andrés López
 */
@Embeddable
public class DetallecomprobantecontabilidadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COM_NUMERO")
    private BigInteger comNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUE_CODIGO")
    private BigInteger cueCodigo;

    public DetallecomprobantecontabilidadPK() {
    }

    public DetallecomprobantecontabilidadPK(BigInteger comNumero, BigInteger cueCodigo) {
        this.comNumero = comNumero;
        this.cueCodigo = cueCodigo;
    }

    public BigInteger getComNumero() {
        return comNumero;
    }

    public void setComNumero(BigInteger comNumero) {
        this.comNumero = comNumero;
    }

    public BigInteger getCueCodigo() {
        return cueCodigo;
    }

    public void setCueCodigo(BigInteger cueCodigo) {
        this.cueCodigo = cueCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comNumero != null ? comNumero.hashCode() : 0);
        hash += (cueCodigo != null ? cueCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallecomprobantecontabilidadPK)) {
            return false;
        }
        DetallecomprobantecontabilidadPK other = (DetallecomprobantecontabilidadPK) object;
        if ((this.comNumero == null && other.comNumero != null) || (this.comNumero != null && !this.comNumero.equals(other.comNumero))) {
            return false;
        }
        if ((this.cueCodigo == null && other.cueCodigo != null) || (this.cueCodigo != null && !this.cueCodigo.equals(other.cueCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.entidades.DetallecomprobantecontabilidadPK[ comNumero=" + comNumero + ", cueCodigo=" + cueCodigo + " ]";
    }
    
}
