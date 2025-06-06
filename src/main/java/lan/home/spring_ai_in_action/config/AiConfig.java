package lan.home.spring_ai_in_action.config;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.model.NoopApiKey;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AiConfig {

    @Bean
    OpenAiChatOptions openAiChatOptions() {
        return OpenAiChatOptions.builder()
                .model("meta-llama-3.1-8b-instruct")
                .temperature(0.4)
                .maxTokens(1000)
                .build();
    }

    @Bean
    ChatMemory chatMemory() { // #1
        return new InMemoryChatMemory();
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, OpenAiChatOptions openAiChatOptions, VectorStore vectorStore,
            ChatMemory chatMemory) {
        return builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder()
                                .topK(3)
                                .similarityThreshold(0.5)
                                .build()))
                .defaultOptions(openAiChatOptions)
                .build();
    }

    @Bean
    OpenAiChatModel chatModel(OpenAiApi openAiApi, OpenAiChatOptions openApiChatOptions) {

        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(openApiChatOptions)
                .build();

    }

    @Bean
    OpenAiEmbeddingOptions openAiEmbeddingOptions() {
        return OpenAiEmbeddingOptions.builder()
                .model("text-embedding-granite-embedding-278m-multilingual")
                .build();
    }

    @Bean
    OpenAiEmbeddingModel embeddingModel(OpenAiApi openAiApi, OpenAiEmbeddingOptions openAiEmbeddingOptions) {
        return new OpenAiEmbeddingModel(openAiApi, MetadataMode.EMBED, openAiEmbeddingOptions);
    }

    @Bean
    OpenAiApi openAiApi() {
        return OpenAiApi.builder()
                .baseUrl("http://localhost:1234")
                .apiKey(new NoopApiKey())
                .webClientBuilder(WebClient.builder()
                        // Force HTTP/1.1 for streaming
                        .clientConnector(new JdkClientHttpConnector(HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_1_1)
                                .connectTimeout(Duration.ofSeconds(30))
                                .build())))
                .restClientBuilder(RestClient.builder()
                        // Force HTTP/1.1 for non-streaming
                        .requestFactory(new JdkClientHttpRequestFactory(HttpClient.newBuilder()
                                .version(HttpClient.Version.HTTP_1_1)
                                .connectTimeout(Duration.ofSeconds(30))
                                .build())))
                .build();

    }

}
