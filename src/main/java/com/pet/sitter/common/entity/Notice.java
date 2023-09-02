package com.pet.sitter.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Setter
@DynamicUpdate
@NoArgsConstructor (access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noNo;

    @Column(length = 45)
    private String noTitle;

    @Column(columnDefinition = "text")
    private String noContent;

    @Column
    private LocalDateTime noDate =LocalDateTime.now();

    @Column
    @ColumnDefault("0")
    private Integer noViewCnt;


    //조회수증가
    public void increaseViewCount() {
        this.noViewCnt++;
    }

    // Notice 엔티티에서 NoticeFile 리스트를 관리
    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
    private List<NoticeFile> noticeFiles;

    @Builder
    public Notice(Long noNo, String noTitle, String noContent, LocalDateTime noDate, Integer noViewCnt ) {
        this.noNo = noNo;
        this.noTitle = noTitle;
        this.noContent = noContent;
        this.noDate = noDate;
        this.noViewCnt = noViewCnt;

    }




}
