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
public class FactDetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FACDET_CODIGO")
    private BigInteger facdetCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_NUMERO")
    private BigInteger facNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ART_CODIGO")
    private BigInteger artCodigo;

    public FactDetallePK() {
    }

    public FactDetallePK(BigInteger facdetCodigo, BigInteger facNumero, BigInteger artCodigo) {
        this.facdetCodigo = facdetCodigo;
        this.facNumero = facNumero;
        this.artCodigo = artCodigo;
    }

    public BigInteger getFacdetCodigo() {
        return facdetCodigo;
    }

    public void setFacdetCodigo(BigInteger facdetCodigo) {
        this.facdetCodigo = facdetCodigo;
    }

    public BigInteger getFacNumero() {
        return facNumero;
    }

    public void setFacNumero(BigInteger facNumero) {
        this.facNumero = facNumero;
    }

    public BigInteger getArtCodigo() {
        return artCodigo;
    }

    public void setArtCodigo(BigInteger artCodigo) {
        this.artCodigo = artCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facdetCodigo != null ? facdetCodigo.hashCode() : 0);
        hash += (facNumero != null ? facNumero.hashCode() : 0);
        hash += (artCodigo != null ? artCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactDetallePK)) {
            return false;
        }
        FactDetallePK other = (FactDetallePK) object;
        if ((this.facdetCodigo == null && other.facdetCodigo != null) || (this.facdetCodigo != null && !this.facdetCodigo.equals(other.facdetCodigo))) {
            return false;
        }
        if ((this.facNumero == null && other.facNumero != null) || (this.facNumero != null && !this.facNumero.equals(other.facNumero))) {
            return false;
        }
        if ((this.artCodigo == null && other.artCodigo != null) || (this.artCodigo != null && !this.artCodigo.equals(other.artCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.entidades.FactDetallePK[ facdetCodigo=" + facdetCodigo + ", facNumero=" + facNumero + ", artCodigo=" + artCodigo + " ]";
    }
    
}
