package lan.home.spring_ai_in_action;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.ai.evaluation.FactCheckingEvaluator;
import org.springframework.ai.evaluation.RelevancyEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lan.home.spring_ai_in_action.domain.Answer;
import lan.home.spring_ai_in_action.domain.Question;
import lan.home.spring_ai_in_action.domain.SpringAiChatService;

@SpringBootTest
class SpringAiInActionApplicationTests {

    @Autowired
    private SpringAiChatService chatService;

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    private RelevancyEvaluator relevancyEvaluator;
    private FactCheckingEvaluator factCheckingEvaluator;

    @BeforeEach
    void setup() {
        this.relevancyEvaluator = new RelevancyEvaluator(chatClientBuilder);
        this.factCheckingEvaluator = new FactCheckingEvaluator(
                chatClientBuilder);
    }

    // @Test
    // void evaluateRelevancy() {

    // String userText = "Pourquoi le ciel est bleu?";
    // Question question = new Question(userText);
    // Answer answer = chatService.askQuestion(question);

    // EvaluationRequest evaluationRequest = new EvaluationRequest(
    // userText, answer.answer());

    // EvaluationResponse response = relevancyEvaluator
    // .evaluate(evaluationRequest);

    // Assertions.assertThat(response.isPass())
    // .withFailMessage("""
    // ========================================
    // The answer "%s"
    // is not considered relevant to the question
    // "%s".
    // ========================================
    // """, answer.answer(), userText)
    // .isTrue();
    // }

    // @Test
    // void evaluateFactualAccuracy() {

    // String userText = "Pourquoi le ciel est bleu?";
    // Question question = new Question(userText);
    // Answer answer = chatService.askQuestion(question);

    // // String referenceAnswer = "Le ciel est bleu, mais ce n'est pas à cause de
    // la
    // // couleur de peinture en promotion...";

    // EvaluationRequest evaluationRequest = new EvaluationRequest(userText,
    // answer.answer());
    // EvaluationResponse response =
    // factCheckingEvaluator.evaluate(evaluationRequest);
    // Assertions.assertThat(response.isPass())
    // .withFailMessage("""
    // ========================================
    // The answer "%s"
    // is not considered correct for the question
    // "%s".
    // ========================================
    // """, answer.answer(), userText)
    // .isTrue();
    // }
}
