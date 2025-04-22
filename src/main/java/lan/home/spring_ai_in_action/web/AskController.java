package lan.home.spring_ai_in_action.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lan.home.spring_ai_in_action.domain.Question;
import lan.home.spring_ai_in_action.domain.SpringAiChatService;
import reactor.core.publisher.Flux;

@RestController
public class AskController {

    private final SpringAiChatService springAiChatService;

    public AskController(SpringAiChatService springAiChatService) {
        this.springAiChatService = springAiChatService;

    }

    @PostMapping(path = "/ask", produces = "application/ndjson")
    public Flux<String> ask(@RequestBody @Valid Question question) {
        return springAiChatService.askQuestion(question);
    }

}