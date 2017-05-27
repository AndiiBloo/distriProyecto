/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_pdist_fact_conta.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrés López
 */
@Entity
@Table(name = "DETALLECOMPROBANTECONTABILIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallecomprobantecontabilidad.findAll", query = "SELECT d FROM Detallecomprobantecontabilidad d")
    , @NamedQuery(name = "Detallecomprobantecontabilidad.findByComNumero", query = "SELECT d FROM Detallecomprobantecontabilidad d WHERE d.detallecomprobantecontabilidadPK.comNumero = :comNumero")
    , @NamedQuery(name = "Detallecomprobantecontabilidad.findByCueCodigo", query = "SELECT d FROM Detallecomprobantecontabilidad d WHERE d.detallecomprobantecontabilidadPK.cueCodigo = :cueCodigo")
    , @NamedQuery(name = "Detallecomprobantecontabilidad.findByDccDebe", query = "SELECT d FROM Detallecomprobantecontabilidad d WHERE d.dccDebe = :dccDebe")
    , @NamedQuery(name = "Detallecomprobantecontabilidad.findByDccHaber", query = "SELECT d FROM Detallecomprobantecontabilidad d WHERE d.dccHaber = :dccHaber")})
public class Detallecomprobantecontabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallecomprobantecontabilidadPK detallecomprobantecontabilidadPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DCC_DEBE")
    private Double dccDebe;
    @Column(name = "DCC_HABER")
    private Double dccHaber;
    @JoinColumn(name = "COM_NUMERO", referencedColumnName = "COM_NUMERO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comprobantecontabilidad comprobantecontabilidad;
    @JoinColumn(name = "CUE_CODIGO", referencedColumnName = "CUE_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Detallecomprobantecontabilidad() {
    }

    public Detallecomprobantecontabilidad(DetallecomprobantecontabilidadPK detallecomprobantecontabilidadPK) {
        this.detallecomprobantecontabilidadPK = detallecomprobantecontabilidadPK;
    }

    public Detallecomprobantecontabilidad(BigInteger comNumero, BigInteger cueCodigo) {
        this.detallecomprobantecontabilidadPK = new DetallecomprobantecontabilidadPK(comNumero, cueCodigo);
    }

    public DetallecomprobantecontabilidadPK getDetallecomprobantecontabilidadPK() {
        return detallecomprobantecontabilidadPK;
    }

    public void setDetallecomprobantecontabilidadPK(DetallecomprobantecontabilidadPK detallecomprobantecontabilidadPK) {
        this.detallecomprobantecontabilidadPK = detallecomprobantecontabilidadPK;
    }

    public Double getDccDebe() {
        return dccDebe;
    }

    public void setDccDebe(Double dccDebe) {
        this.dccDebe = dccDebe;
    }

    public Double getDccHaber() {
        return dccHaber;
    }

    public void setDccHaber(Double dccHaber) {
        this.dccHaber = dccHaber;
    }

    public Comprobantecontabilidad getComprobantecontabilidad() {
        return comprobantecontabilidad;
    }

    public void setComprobantecontabilidad(Comprobantecontabilidad comprobantecontabilidad) {
        this.comprobantecontabilidad = comprobantecontabilidad;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallecomprobantecontabilidadPK != null ? detallecomprobantecontabilidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallecomprobantecontabilidad)) {
            return false;
        }
        Detallecomprobantecontabilidad other = (Detallecomprobantecontabilidad) object;
        if ((this.detallecomprobantecontabilidadPK == null && other.detallecomprobantecontabilidadPK != null) || (this.detallecomprobantecontabilidadPK != null && !this.detallecomprobantecontabilidadPK.equals(other.detallecomprobantecontabilidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_pdist_fact_conta.entidades.Detallecomprobantecontabilidad[ detallecomprobantecontabilidadPK=" + detallecomprobantecontabilidadPK + " ]";
    }
    
}
