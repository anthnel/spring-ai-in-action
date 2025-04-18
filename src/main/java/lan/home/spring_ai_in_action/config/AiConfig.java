package lan.home.spring_ai_in_action.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        ChatOptions chatOptions = ChatOptions.builder()
                // .model("meta-llama-3.1-8b-instruct")
                .build();
        return builder.defaultSystem(
                "Tu es quelqu'un qui a l'esprit très vif. Tes réponses sont toujours intelligentes et concises.")
                .defaultOptions(chatOptions)
                .build();
    }

}
