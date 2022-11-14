package day6.fullbang.ai.dto;

import day6.fullbang.ai.domain.AiInputData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAiDataDto {

    private String mondayList;
    private String tuesdayList;
    private String wednesdayList;
    private String thursdayList;
    private String fridayList;
    private String saturdayList;
    private String sundayList;

    private String ownerEmail;

    public AiInputData toEntity() {
        return AiInputData.builder()
            .mondayList(mondayList)
            .tuesdayList(tuesdayList)
            .wednesdayList(wednesdayList)
            .thursdayList(thursdayList)
            .fridayList(fridayList)
            .saturdayList(saturdayList)
            .sundayList(sundayList)
            .ownerEmail(ownerEmail)
            .build();
    }

}
