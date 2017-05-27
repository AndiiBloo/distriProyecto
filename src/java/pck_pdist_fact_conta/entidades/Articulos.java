/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrés López
 */
@Entity
@Table(name = "ARTICULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a")
    , @NamedQuery(name = "Articulos.findByArtCodigo", query = "SELECT a FROM Articulos a WHERE a.artCodigo = :artCodigo")
    , @NamedQuery(name = "Articulos.findByArtNombre", query = "SELECT a FROM Articulos a WHERE a.artNombre = :artNombre")
    , @NamedQuery(name = "Articulos.findByArtCantidad", query = "SELECT a FROM Articulos a WHERE a.artCantidad = :artCantidad")
    , @NamedQuery(name = "Articulos.findByArtPrecio", query = "SELECT a FROM Articulos a WHERE a.artPrecio = :artPrecio")})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ART_CODIGO")
    private BigDecimal artCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ART_NOMBRE")
    private String artNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ART_CANTIDAD")
    private BigInteger artCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ART_PRECIO")
    private double artPrecio;
    @JoinTable(name = "FACT_ARTI", joinColumns = {
        @JoinColumn(name = "ART_CODIGO", referencedColumnName = "ART_CODIGO")}, inverseJoinColumns = {
        @JoinColumn(name = "FAC_NUMERO", referencedColumnName = "FAC_NUMERO")})
    @ManyToMany
    private List<Factura> facturaList;

    public Articulos() {
    }

    public Articulos(BigDecimal artCodigo) {
        this.artCodigo = artCodigo;
    }

    public Articulos(BigDecimal artCodigo, String artNombre, BigInteger artCantidad, double artPrecio) {
        this.artCodigo = artCodigo;
        this.artNombre = artNombre;
        this.artCantidad = artCantidad;
        this.artPrecio = artPrecio;
    }

    public BigDecimal getArtCodigo() {
        return artCodigo;
    }

    public void setArtCodigo(BigDecimal artCodigo) {
        this.artCodigo = artCodigo;
    }

    public String getArtNombre() {
        return artNombre;
    }

    public void setArtNombre(String artNombre) {
        this.artNombre = artNombre;
    }

    public BigInteger getArtCantidad() {
        return artCantidad;
    }

    public void setArtCantidad(BigInteger artCantidad) {
        this.artCantidad = artCantidad;
    }

    public double getArtPrecio() {
        return artPrecio;
    }

    public void setArtPrecio(double artPrecio) {
        this.artPrecio = artPrecio;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artCodigo != null ? artCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.artCodigo == null && other.artCodigo != null) || (this.artCodigo != null && !this.artCodigo.equals(other.artCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.Articulos[ artCodigo=" + artCodigo + " ]";
    }
    
}
