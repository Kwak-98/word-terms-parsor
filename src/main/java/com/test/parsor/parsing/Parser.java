package com.test.parsor.parsing;

import com.test.parsor.word.AbstractElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    /**
     * 트리구조의 문서를 List로 반환하는 메서드
     * @param element : 트리의 루트
     * @return List<String> : 파일의 각 요소가 AbstactElement로 저장된 List
     */
    public List<AbstractElement> toList(AbstractElement element);

    /**
     * 정리된 객체List를 html출력을 위한 문자열 List로 반환하는 메서드
     * @param word : 공백이 없고 문서의 순서대로 정리된 AbstractElement 객체 List
     * @return List<String> : html태그로 변환된 문서List
     */
    List<String> toHtml(List<AbstractElement> word);

    /**
     * document를 받아서 트리구조의 AbstractElement생성
     * @param document : 파일의 내용물
     * @return Optional<AbstractElement> : null체크를 하기위한 Optional로 포장된 트리구조의 AbstractElement의 root반환
     */
    Optional<AbstractElement> parse(XWPFDocument document);

}
