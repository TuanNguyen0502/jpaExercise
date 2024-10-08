package vn.loh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "Active")
    private int active;

    @Column(name = "description", columnDefinition = "NVARCHAR(500) NULL")
    private String description;

    @Column(name = "poster", columnDefinition = "NVARCHAR(500) NULL")
    private String poster;

    @Column(name = "title", columnDefinition = "NVARCHAR(500) NULL")
    private String title;

    @Column(name = "views")
    private int views;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;
}
