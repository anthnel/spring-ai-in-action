package lan.home.spring_ai_in_action.domain;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class SpringAiChatService implements ChatService {

    private final OpenAiChatModel chatModel;

    public SpringAiChatService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Flux<String> askQuestion(Question question) {
        long startTime = System.currentTimeMillis();

        var prompt = """
                Répond à cette question concernant le jeu %s : %s
                """.formatted(question.gameTitle(), question.question());

        return chatModel.stream(prompt);

        // long endTime = System.currentTimeMillis();

        // var response = chatResponse.getResult().getOutput().getText();
        // double elapsedSeconds = (endTime - startTime) / 1000.0;
        // long totalTokens =
        // chatResponse.getMetadata().getUsage().getCompletionTokens();
        // double tokPerSec = totalTokens / elapsedSeconds;

        // var answer = String.format(
        // "%s%n%n(%.2f tok/sec - %d tokens - Response time: %.2f seconds)", response,
        // tokPerSec, totalTokens,
        // elapsedSeconds);

        // return new Answer(question.gameTitle(), answer);
    }

}
