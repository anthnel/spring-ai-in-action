package lan.home.spring_ai_in_action.domain;

import reactor.core.publisher.Flux;

public interface ChatService {
    Flux<String> askQuestion(Question question);
}
