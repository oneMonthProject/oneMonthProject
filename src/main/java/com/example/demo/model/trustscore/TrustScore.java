package com.example.demo.model.trustscore;

import com.example.demo.model.BaseTimeEntity;
import com.example.demo.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "trust_score")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrustScore implements Serializable {
    @Serial
    private static final long serialVersionUID = 5571826191135479993L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trust_score_id")
    private Long id;
    /**
     * 유저자동생성식별자
     */
    @Column
    private Long userId;

    /**
     * 유저신뢰점수값
     */
    @Column
    private int score;

    /**
     * 변경날짜
     */
    @Column
    private Date updateDate;
}
