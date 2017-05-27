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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrés López
 */
@Entity
@Table(name = "FACT_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactDetalle.findAll", query = "SELECT f FROM FactDetalle f")
    , @NamedQuery(name = "FactDetalle.findByFacNumero", query = "SELECT f FROM FactDetalle f WHERE f.factDetallePK.facNumero = :facNumero")
    , @NamedQuery(name = "FactDetalle.findByArtCodigo", query = "SELECT f FROM FactDetalle f WHERE f.factDetallePK.artCodigo = :artCodigo")
    , @NamedQuery(name = "FactDetalle.findByFacdetCantidad", query = "SELECT f FROM FactDetalle f WHERE f.facdetCantidad = :facdetCantidad")
    , @NamedQuery(name = "FactDetalle.findByFacdetPrecio", query = "SELECT f FROM FactDetalle f WHERE f.facdetPrecio = :facdetPrecio")})
public class FactDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FactDetallePK factDetallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACDET_CANTIDAD")
    private BigInteger facdetCantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FACDET_PRECIO")
    private Double facdetPrecio;
    @JoinColumn(name = "ART_CODIGO", referencedColumnName = "ART_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articulos articulos;
    @JoinColumn(name = "FAC_NUMERO", referencedColumnName = "FAC_NUMERO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public FactDetalle() {
    }

    public FactDetalle(FactDetallePK factDetallePK) {
        this.factDetallePK = factDetallePK;
    }

    public FactDetalle(FactDetallePK factDetallePK, BigInteger facdetCantidad) {
        this.factDetallePK = factDetallePK;
        this.facdetCantidad = facdetCantidad;
    }

    public FactDetalle(BigInteger facNumero, BigInteger artCodigo) {
        this.factDetallePK = new FactDetallePK(facNumero, artCodigo);
    }

    public FactDetallePK getFactDetallePK() {
        return factDetallePK;
    }

    public void setFactDetallePK(FactDetallePK factDetallePK) {
        this.factDetallePK = factDetallePK;
    }

    public BigInteger getFacdetCantidad() {
        return facdetCantidad;
    }

    public void setFacdetCantidad(BigInteger facdetCantidad) {
        this.facdetCantidad = facdetCantidad;
    }

    public Double getFacdetPrecio() {
        return facdetPrecio;
    }

    public void setFacdetPrecio(Double facdetPrecio) {
        this.facdetPrecio = facdetPrecio;
    }

    public Articulos getArticulos() {
        return articulos;
    }

    public void setArticulos(Articulos articulos) {
        this.articulos = articulos;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factDetallePK != null ? factDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FactDetalle)) {
            return false;
        }
        FactDetalle other = (FactDetalle) object;
        if ((this.factDetallePK == null && other.factDetallePK != null) || (this.factDetallePK != null && !this.factDetallePK.equals(other.factDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.entidades.FactDetalle[ factDetallePK=" + factDetallePK + " ]";
    }
    
}
