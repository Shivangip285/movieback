package com.booking.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "passwordlogger")
public class PasswordLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonProperty
    @Column
    @ApiModelProperty(name = "older_password", value = "Older password", example = "password", position = 3)
    private String olderPassword;

    @JsonProperty
    @Column
    @ApiModelProperty(name = "oldest_password", value = "Oldest password", example = "password", position = 4)
    private String oldestPassword;

    public PasswordLogger(){

    }

    public PasswordLogger(User user, String olderPassword, String oldestPassword) {
        this.user = user;
        this.olderPassword = olderPassword;
        this.oldestPassword = oldestPassword;
    }

    public String getOlderPassword() {
        return olderPassword;
    }

    public void setOlderPassword(String olderPassword) {
        this.olderPassword = olderPassword;
    }

    public String getOldestPassword() {
        return oldestPassword;
    }

    public void setOldestPassword(String oldestPassword) {
        this.oldestPassword = oldestPassword;
    }
}
