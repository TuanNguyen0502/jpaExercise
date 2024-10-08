package vn.loh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", columnDefinition = "NVARCHAR(500) NOT NULL")
    @NotEmpty(message = "Name is required")
    private String username;

    @Column(name = "password", columnDefinition = "NVARCHAR(500) NOT NULL")
    @NotEmpty(message = "Name is required")
    private String password;

    @Column(name = "email", columnDefinition = "NVARCHAR(500) NULL")
    private String email;

    @Column(name = "fullname", columnDefinition = "NVARCHAR(500) NULL")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "createdate")
    private Date createdate;

    @Column(name = "image", columnDefinition = "NVARCHAR(500) NULL")
    private String image;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Name is required") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Name is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Name is required") String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
