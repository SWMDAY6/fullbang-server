package day6.fullbang.ai.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ai_input_data")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AiInputData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monday_list", nullable = false)
    private String mondayList;
    @Column(name = "tuesday_list", nullable = false)
    private String tuesdayList;
    @Column(name = "wednesday_list", nullable = false)
    private String wednesdayList;
    @Column(name = "thursday_list", nullable = false)
    private String thursdayList;
    @Column(name = "friday_list", nullable = false)
    private String fridayList;
    @Column(name = "saturday_list", nullable = false)
    private String saturdayList;
    @Column(name = "sunday_list", nullable = false)
    private String sundayList;

    @CreatedDate
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(name = "is_send")
    private Boolean isSend = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "send_date")
    private LocalDate sendDate;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    @Builder
    public AiInputData(String mondayList, String tuesdayList, String wednesdayList, String thursdayList,
        String fridayList,
        String saturdayList, String sundayList, String ownerEmail) {
        this.mondayList = mondayList;
        this.tuesdayList = tuesdayList;
        this.wednesdayList = wednesdayList;
        this.thursdayList = thursdayList;
        this.fridayList = fridayList;
        this.saturdayList = saturdayList;
        this.sundayList = sundayList;
        this.ownerEmail = ownerEmail;
    }
}
