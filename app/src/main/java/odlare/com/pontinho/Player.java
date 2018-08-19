package odlare.com.pontinho;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "players")
public class Player {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private String phone;

    @NonNull
    private String email;

    @Ignore
    public Player(@NonNull Integer id,
                  @NonNull String name,
                  @NonNull String lastname,
                  @NonNull String phone,
                  @NonNull String email) {

        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public Player(@NonNull String name,
                  @NonNull String lastname,
                  @NonNull String phone,
                  @NonNull String email) {

        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@NonNull String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
