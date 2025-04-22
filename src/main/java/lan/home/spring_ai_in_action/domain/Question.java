package lan.home.spring_ai_in_action.domain;

import jakarta.validation.constraints.NotBlank;

public record Question(
        @NotBlank(message = "Game title is required") String gameTitle,
        @NotBlank(message = "Question is required") String question) {

}
