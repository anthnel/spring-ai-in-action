package lan.home.spring_ai_in_action.domain;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.AdvisorSpec;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class SpringAiChatService implements ChatService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource promptTemplate;

    public SpringAiChatService(VectorStore vectorStore, ChatClient chatClient) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    // @Override
    // public Flux<String> askQuestion(Question question) {

    // SearchRequest searchRequest = SearchRequest
    // .builder()
    // .query(question.question())
    // .topK(3)
    // .similarityThreshold(0.2)
    // .build();

    // List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
    // if (similarDocs == null || similarDocs.isEmpty()) {
    // return Flux.just("Aucune réponse trouvée");
    // }

    // return Flux.fromStream(similarDocs.stream()).map(Document::getText);
    // }

    @Override
    public Flux<String> askQuestion(Question question) {

        return chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(promptTemplate))
                .user(question.question())
                .stream().content();
    }

}
