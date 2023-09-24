package com.itsweb.backend.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/** AuditingEntityListener를 적용하면
 *엔티티가 데이터베이스에 추가되거나 변경될때 자동으로
 * 시간값을 지정할 수 있음.
*/
@Getter
public class TimeStamp {
    // 등록일
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;
    // 수정일
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="moddate")
    private LocalDateTime modDate;
}
