package com.example.demo.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseTimeEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ColumnDefault("0")
    private int pageView;

    @ColumnDefault("false")
    private boolean completeStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String contact;

    @Builder
    private Board(
            Long id,
            String title,
            String content,
            Project project,
            int pageView,
            boolean completeStatus,
            User user,
            String contact) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.project = project;
        this.pageView = pageView;
        this.completeStatus = completeStatus;
        this.user = user;
        this.contact = contact;
    }
}
