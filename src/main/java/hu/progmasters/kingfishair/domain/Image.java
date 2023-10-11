package hu.progmasters.kingfishair.domain;

import com.itextpdf.io.image.ImageData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = true)
    private Airport airport;


    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = true)
    private Plane plane;


    public Image(Long id, String imageUrl, Airport airport, Plane plane) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.airport = airport;
        this.plane = plane;
    }

    public Image() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
