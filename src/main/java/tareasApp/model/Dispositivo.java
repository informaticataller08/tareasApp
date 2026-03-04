package tareasApp.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Dispositivos")
public class Dispositivo {
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
}
