package com.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION_Time=10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",
            nullable=false,
            foreignKey =@ForeignKey(name="FK_USER_VERIFY_TOKEN")
    )
    private User user;

    //    private Long id;
//    private String token;
//    private Date expirationTime;
//    @OneToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="user_id",
//            foreignKey = @ForeignKey(name="FK_USER_VERIFY_TOKEN")
//    )
//    private User user;

    public VerificationToken(User user, String token) {
        super();
        this.user=user;
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_Time);
    }

    public VerificationToken(String token, Date expirationTime) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate(EXPIRATION_Time);
    }


    private Date calculateExpirationDate(int expirationTime) {
        Calendar calender= Calendar.getInstance();
        calender.setTimeInMillis(new Date().getTime());
        calender.add(Calendar.MINUTE,expirationTime);
       return new Date(calender.getTime().getTime());
    }
}
