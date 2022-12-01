package lesson3;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;


class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getBean(SpringConfig.class).run();
    }
}