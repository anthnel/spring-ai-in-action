package lan.home.spring_ai_in_action.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lan.home.spring_ai_in_action.domain.Answer;
import lan.home.spring_ai_in_action.domain.Question;
import lan.home.spring_ai_in_action.domain.SpringAiChatService;

@RestController
public class AskController {

    private final SpringAiChatService springAiChatService;

    public AskController(SpringAiChatService springAiChatService, ChatClient chatClient) {
        this.springAiChatService = springAiChatService;

    }

    @PostMapping(path = "/ask", produces = "application/json")
    public Answer ask(@RequestBody Question question) {
        return springAiChatService.askQuestion(question);
    }

    // public String chat(String chatId, String userMessage) {
    // long startTime = System.currentTimeMillis();
    // ChatResponse chatResponse = this.chatClient
    // .prompt()
    // .user(userMessage)
    // .call()
    // .chatResponse();

    // long endTime = System.currentTimeMillis();

    // String response = chatResponse.getResult().getOutput().getText();
    // double elapsedSeconds = (endTime - startTime) / 1000;
    // long totalTokens =
    // chatResponse.getMetadata().getUsage().getGenerationTokens();

    // double tokPerSec = totalTokens / elapsedSeconds;

    // return String.format(
    // "%s\n\n(%.2f tok/sec - %d tokens - Response time: %.2f seconds)", response,
    // tokPerSec, totalTokens,
    // elapsedSeconds);
    // }
}
