package tareasApp.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Dispositivos")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDispositivo tipo;

    @Column(nullable = false)
    private String oficina;

    private String destino;

    @Column(length = 500)
    private String descripcion;

    private String nne;
    private String red;
    private String nombreEquipo;
    private String cuentaUsuario;

    public String getRed() {
        return red;
    }
    public void setRed(String red) {
        this.red = red;
    }
    public String getNne(){
        return nne;
    }
    public void setNne(String nne){
        this.nne=nne;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoDispositivo tipo) {
        this.tipo = tipo;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDispositivo getTipo() {
        return tipo;
    }

    public Long getId() {
        return id;
    }

    public String getOficina() {
        return oficina;
    }

    public String getDestino() {
        return destino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }
}
