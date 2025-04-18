package lan.home.spring_ai_in_action.domain;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpringAiChatService implements ChatService {

    private ChatClient chatClient;

    public SpringAiChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Answer askQuestion(Question question) {
        String answerText = chatClient.prompt()
                .user(question.question())
                .call()
                .content();
        return new Answer(answerText);
    }

}
