package day6.fullbang.ai.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import day6.fullbang.ai.domain.AiInputData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseAiDataDto {
    private Long id;
    private String ownerEmail;
    private Boolean isSend;

    private String mondayList;
    private String tuesdayList;
    private String wednesdayList;
    private String thursdayList;
    private String fridayList;
    private String saturdayList;
    private String sundayList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate sendDate;

    public ResponseAiDataDto(AiInputData aiInputData) {
        this.id = aiInputData.getId();
        this.ownerEmail = aiInputData.getOwnerEmail();
        this.isSend = aiInputData.getIsSend();
        this.mondayList = aiInputData.getMondayList();
        this.tuesdayList = aiInputData.getTuesdayList();
        this.wednesdayList = aiInputData.getWednesdayList();
        this.thursdayList = aiInputData.getThursdayList();
        this.fridayList = aiInputData.getFridayList();
        this.saturdayList = aiInputData.getSaturdayList();
        this.sundayList = aiInputData.getSundayList();
        this.createDate = aiInputData.getCreatedDate();
        this.sendDate = aiInputData.getSendDate();
    }
}
