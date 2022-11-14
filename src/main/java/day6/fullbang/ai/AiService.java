package day6.fullbang.ai;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.fullbang.ai.domain.AiInputData;
import day6.fullbang.ai.dto.RequestAiDataDto;
import day6.fullbang.ai.dto.ResponseAiDataDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

    private final AiRepository aiRepository;

    @Transactional
    public Long save(RequestAiDataDto requestAiDataDto) {
        return aiRepository.save(requestAiDataDto.toEntity());
    }

    public List<ResponseAiDataDto> findAll() {
        List<AiInputData> aiInputData = aiRepository.findAll();
        List<ResponseAiDataDto> responseAiDataDtos = dataEntityToDto(aiInputData);
        return responseAiDataDtos;
    }

    public List<ResponseAiDataDto> findNoSend() {
        List<AiInputData> aiInputData = aiRepository.findNoSend();
        List<ResponseAiDataDto> responseAiDataDtos = dataEntityToDto(aiInputData);
        return responseAiDataDtos;
    }

    @Transactional
    public Long checkEmail(Long id) {
        aiRepository.checkEmail(id);
        return id;
    }

    public List<ResponseAiDataDto> dataEntityToDto(List<AiInputData> dataEntity) {
        List<ResponseAiDataDto> responseAiDataDtos = new ArrayList<>();

        for (AiInputData aiInputData : dataEntity) {
            ResponseAiDataDto item = new ResponseAiDataDto(aiInputData);
            responseAiDataDtos.add(item);
        }
        return responseAiDataDtos;
    }

}
