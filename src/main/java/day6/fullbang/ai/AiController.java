package day6.fullbang.ai;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.ai.dto.RequestAiDataDto;
import day6.fullbang.ai.dto.ResponseAiDataDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "AI API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    @PostMapping("")
    public Long save(@RequestBody RequestAiDataDto requestAiDataDto) {
        return aiService.save(requestAiDataDto);
    }

    @GetMapping("")
    public List<ResponseAiDataDto> findAll() {
        return aiService.findAll();
    }

    @GetMapping("/check")
    public List<ResponseAiDataDto> findNoSend() {
        return aiService.findNoSend();
    }

    @PatchMapping("/email")
    public Long checkEmail(@RequestParam Long id) {
        return aiService.checkEmail(id);
    }
}
