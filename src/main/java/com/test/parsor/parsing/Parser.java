package com.test.parsor.parsing;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Parsing에 필요한 기능을 정의한 인터페이스
 * 추후 Parsing로직이 바뀌거나 word이외의 파일을 추가할 경우를 생각
 */
public interface Parser {


    /**
     * 업로드한 파일이 있는지 없는지 확인하는 메서드
     * 구현체가될 Parser와 상관없이 필요한 기능
     * @param fileName : 파일 이름
     * @return true: 파일 존재, fales: 파일 없음
     */
    default Boolean isExists(String fileName) {

        try {
            // 파일이 존재시 true 반환
            ClassPathResource resource = new ClassPathResource("static/files/" + fileName);
            File file = resource.getFile();
            return file.exists();
        } catch (IOException e) {
            // 해당 파일이 없어서 예외가 발생시 false반환
            return false;
        }

    }

}
