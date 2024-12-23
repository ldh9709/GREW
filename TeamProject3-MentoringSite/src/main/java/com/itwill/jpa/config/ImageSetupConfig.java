package com.itwill.jpa.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Configuration
public class ImageSetupConfig {

    private static final String EXTERNAL_IMAGE_PATH = "C:/mentor-profile-images/"; // 외부 저장 경로
    private static final String INTERNAL_IMAGE_PATH = "static/images/mentor-profile/"; // 기존 이미지 경로

    @Bean
    public CommandLineRunner setupDefaultImages() {
        return args -> {
            // 외부 저장 경로 생성
            File directory = new File(EXTERNAL_IMAGE_PATH);
            if (!directory.exists()) {
                boolean isCreated = directory.mkdirs();
                if (isCreated) {
                    System.out.println("외부 디렉토리가 생성되었습니다: " + EXTERNAL_IMAGE_PATH);
                }
            }

            // 기본 이미지 복사
            try {
                copyDefaultImage("fuck.jpeg", "fuck.jpeg");
                copyDefaultImage("fuck1.jpeg", "fuck1.jpeg");
                copyDefaultImage("fuck2.jpeg", "fuck2.jpeg");
                copyDefaultImage("fuck3.jpeg", "fuck3.jpeg");
                copyDefaultImage("fuck4.jpeg", "fuck4.jpeg");
                copyDefaultImage("fuck5.jpeg", "fuck5.jpeg");
                copyDefaultImage("fuck6.jpeg", "fuck6.jpeg");
                copyDefaultImage("fuck7.jpeg", "fuck7.jpeg");
                copyDefaultImage("fuck8.jpeg", "fuck8.jpeg");
                copyDefaultImage("fuck9.jpeg", "fuck9.jpeg");
                copyDefaultImage("fuck10.jpeg", "fuck10.jpeg");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("기본 이미지를 복사하는 중 오류 발생.");
            }
        };
    }

    private void copyDefaultImage(String resourceName, String destinationName) throws IOException {
        Path destinationPath = Path.of(EXTERNAL_IMAGE_PATH, destinationName);
        if (!Files.exists(destinationPath)) {
            // 내부 경로에서 이미지 읽기
            var resource = new ClassPathResource(INTERNAL_IMAGE_PATH + resourceName);
            Files.copy(resource.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("기본 이미지가 복사되었습니다: " + destinationPath);
        } else {
            System.out.println("기본 이미지가 이미 존재합니다: " + destinationPath);
        }
    }
}
