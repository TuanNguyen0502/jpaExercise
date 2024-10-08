package vn.loh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", columnDefinition = "NVARCHAR(500) NOT NULL")
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(name = "image", columnDefinition = "NVARCHAR(500) NULL")
    private String image;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "category")
    private List<Video> videos;

    public Video addVideo(Video video) {
        getVideos().add(video);
        video.setCategory(this);

        return video;
    }

    public Video removeVideo(Video video) {
        getVideos().remove(video);
        video.setCategory(null);

        return video;
    }
}
