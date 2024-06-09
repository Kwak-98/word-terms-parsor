package com.test.parsor.controller;

import com.test.parsor.parsing.Parser;
import com.test.parsor.service.FileService;
import com.test.parsor.word.AbstractElement;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ParsorController {

    private final FileService FILE_SERVICE;
    private final Parser PARSER;

    /**
     * 메인 페이지 포워딩
     * @return "index" : 메인 페이지
     */
    @GetMapping("/index.kr")
    public String index() {
        return "index";
    }

    /**
     * 업로드한 문서를 저장하지않고 내용물만 객체 트리구조로 변환
     * 콘솔 출력 + view페이지로 포워딩
     * @param file   : 업로드한 문서
     * @param model  : 페이지로 전달
     * @return       : "view" : 상세 보기 페이지
     */
    @PostMapping("/view.kr")
    public String view(MultipartFile file, Model model) {

        // docx파일을 AbstractElement로 변환
        // null이 아니면 페이지로 포워딩
        XWPFDocument document = FILE_SERVICE.readFile(file);
        Optional<AbstractElement> rootElement = PARSER.parse(document);

        //파일 이름 포워딩
        model.addAttribute("filename", file.getOriginalFilename());
        
        //root가 존재하면 root반환, null이면 에러문자 반환
        if(rootElement.isPresent()) {
            AbstractElement root = rootElement.get();
            root.print();
            List<AbstractElement> word = PARSER.toList(root);
            List<String> html = PARSER.toHtml(word);

            model.addAttribute("word", html);
        }else {
            model.addAttribute("exception", "파일을 읽어오지 못했습니다.");
        }

        return "view";
    }

}//End of class
