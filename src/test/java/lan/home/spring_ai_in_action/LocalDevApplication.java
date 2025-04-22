package lan.home.spring_ai_in_action;

import org.springframework.boot.SpringApplication;

public class LocalDevApplication {
    public static void main(String[] args) {
        SpringApplication.from(SpringAiInActionApplication::main)
                .with(LocalDevTestcontainersConfig.class)
                .run(args);
    }
}
