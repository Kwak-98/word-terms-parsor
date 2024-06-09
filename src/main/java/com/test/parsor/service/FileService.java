package com.test.parsor.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일에 관련된 서비스를 제공하는 인터페이스
 */
public interface FileService {

    /**
     * 업로드한 파일의 내용물만 읽어서 XWPFDocument객체로 
     * @param file : html에서 post로 전송한 파일
     * @return  XWPFDocument: apache poi라이브러리로 변환한 문서 객체
     */
    XWPFDocument readFile(MultipartFile file);

}
