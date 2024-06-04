package com.test.parsor.controller;

import com.test.parsor.parsing.Parser;
import com.test.parsor.service.FileService;
import com.test.parsor.word.AbstractElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/view.kr")
    public String view(MultipartFile file, Model model) {

        // docx파일을 List로 변환
        String fileName = file.getOriginalFilename();
        List<String> word = FILE_SERVICE.readFile(file);

        // 리스트에서 AbstractElement로 변환
        AbstractElement root = PARSER.parse(fileName, word);

        // model에 담아서 페이지로 포워딩
        model.addAttribute("root", root);

        return "view";
    }

}//End of class
