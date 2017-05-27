/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrés López
 */
@Entity
@Table(name = "FACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByFacNumero", query = "SELECT f FROM Factura f WHERE f.facNumero = :facNumero")
    , @NamedQuery(name = "Factura.findByCiuCodigo", query = "SELECT f FROM Factura f WHERE f.ciuCodigo = :ciuCodigo")
    , @NamedQuery(name = "Factura.findByFacFecha", query = "SELECT f FROM Factura f WHERE f.facFecha = :facFecha")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_NUMERO")
    private BigDecimal facNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIU_CODIGO")
    private BigInteger ciuCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date facFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<FactDetalle> factDetalleList;
    @JoinColumn(name = "CIU_CIU_CODIGO", referencedColumnName = "CIU_CODIGO")
    @ManyToOne
    private CiudadEntrega ciuCiuCodigo;
    @JoinColumn(name = "CLI_RUC", referencedColumnName = "CLI_RUC")
    @ManyToOne(optional = false)
    private Cliente cliRuc;

    public Factura() {
    }

    public Factura(BigDecimal facNumero) {
        this.facNumero = facNumero;
    }

    public Factura(BigDecimal facNumero, BigInteger ciuCodigo, Date facFecha) {
        this.facNumero = facNumero;
        this.ciuCodigo = ciuCodigo;
        this.facFecha = facFecha;
    }

    public BigDecimal getFacNumero() {
        return facNumero;
    }

    public void setFacNumero(BigDecimal facNumero) {
        this.facNumero = facNumero;
    }

    public BigInteger getCiuCodigo() {
        return ciuCodigo;
    }

    public void setCiuCodigo(BigInteger ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }

    @XmlTransient
    public List<FactDetalle> getFactDetalleList() {
        return factDetalleList;
    }

    public void setFactDetalleList(List<FactDetalle> factDetalleList) {
        this.factDetalleList = factDetalleList;
    }

    public CiudadEntrega getCiuCiuCodigo() {
        return ciuCiuCodigo;
    }

    public void setCiuCiuCodigo(CiudadEntrega ciuCiuCodigo) {
        this.ciuCiuCodigo = ciuCiuCodigo;
    }

    public Cliente getCliRuc() {
        return cliRuc;
    }

    public void setCliRuc(Cliente cliRuc) {
        this.cliRuc = cliRuc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facNumero != null ? facNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facNumero == null && other.facNumero != null) || (this.facNumero != null && !this.facNumero.equals(other.facNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.entidades.Factura[ facNumero=" + facNumero + " ]";
    }
    
}
