package com.test.parsor.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class RegexTest {

    @Test
    @DisplayName("랜덤인지 확인하는 정규표현식 테스트")
    void isRandom() {
    
        //Given
        String regex = "^(?:\\d+\\)|\\(\\d+\\)|[ㄱ-ㅎ-|가-힣]\\)|[\u24B6-\u24E9\u3260-\u327B])\\s.*";
        //When
        String text1 = "1) [Level-1] Random 번호 첫번째 입니다.";
        String text2 = "ㄱ) [Level-2] Random 번호 첫번째 입니다.";
        String text3 = "(1) Random 번호 첫번째 입니다.";
        String text4 = "가) Random 번호 첫번째 입니다.";
        String text5 = "Ⓐ Random 번호 첫번째 입니다.";
        String text6 = "ⓐ Random 번호 첫번째 입니다.";
        String text7 = "㉮ Random 번호 첫번째 입니다.";
        String text8 = "㉠ Random 번호 첫번째 입니다.";

        String text9 = "㉠Random 번호 첫번째 입니다.";
        String text10 = "( 1 ) Random 번호 첫번째 입니다.";
        String text11 = "(1 ) Random 번호 첫번째 입니다.";
        String text12 = "( 1) Random 번호 첫번째 입니다.";



        //Then
        assertThat(text1.matches(regex)).isTrue();
        assertThat(text2.matches(regex)).isTrue();
        assertThat(text3.matches(regex)).isTrue();
        assertThat(text4.matches(regex)).isTrue();
        assertThat(text5.matches(regex)).isTrue();
        assertThat(text6.matches(regex)).isTrue();
        assertThat(text7.matches(regex)).isTrue();
        assertThat(text8.matches(regex)).isTrue();

        assertThat(text9.matches(regex)).isFalse();
        assertThat(text10.matches(regex)).isFalse();
        assertThat(text11.matches(regex)).isFalse();
        assertThat(text12.matches(regex)).isFalse();
    
    }

    @Test
    @DisplayName("조 문자열 자르기 테스트")
    void articleSubString() {
    
        //Given
        String text1 = "제2조(용어의 정의)";
        String text2 = "제4조(용어의 정의)";
        String text3 = "제15조(용어의 정의)";

        //When
        String sub1 = text1.substring(text1.indexOf("제"), text1.indexOf("조") + 1);
        String sub2 = text2.substring(text2.indexOf("제"), text2.indexOf("조") + 1);
        String sub3 = text3.substring(text3.indexOf("제"), text3.indexOf("조") + 1);
        
        int no1 = Integer.parseInt(text1.substring(text1.indexOf("제") + 1, text1.indexOf("조")));
        int no2 = Integer.parseInt(text2.substring(text2.indexOf("제") + 1, text2.indexOf("조")));
        int no3 = Integer.parseInt(text3.substring(text3.indexOf("제") + 1, text3.indexOf("조")));

        //Then
        assertThat(sub1).isEqualTo("제2조");
        assertThat(sub2).isEqualTo("제4조");
        assertThat(sub3).isEqualTo("제15조");

        assertThat(no1).isEqualTo(2);
        assertThat(no2).isEqualTo(4);
        assertThat(no3).isEqualTo(15);

    }
    
    @Test
    @DisplayName("목인지 확인하는 정규표현식 테스트")
    void isItem() {

        //Given : 가~하.으로 시작하는 정규표현식
        String regex = "^[가나다라마바사아자차카타파하]\\..*$";

        //When
        String text1 = "가. ‘중대한 암 보장개시일’ 이후에 ‘중대한 암’으로 진단 확정 되었을 경우";
        String text2 = "나. ‘중대한 암 이외의 중대한 질병’ 또는 ‘중대한 화상 및 부식(화학약품 등에 의한 피부 손상)’으로 진단 확정 되거나 ‘중대한 수술’을 받았을 경우";
        String text3 = ". ‘중대한 암 보장개시일’ 이후에 ‘중대한 암’으로 진단 확정 되었을 경우";
        String text4 = "가나. ‘중대한 암 보장개시일’ 이후에 ‘중대한 암’으로 진단 확정 되었을 경우";

        //Then
        assertThat(text1).matches(regex);
        assertThat(text2).matches(regex);
        assertThat(text3).doesNotMatch(regex);
        assertThat(text4).doesNotMatch(regex);

    }
    
    @Test
    @DisplayName("호인지 확인하는 정규표현식 테스트")
    void isSubParagraph() {
    
        //Given : 숫자.으로 시작하는 정규표현식
        String regex = "^\\d+\\.\\s*.*$";

        //When
        String text1 = "1. 피보험자가 보험기간 중 사망한 경우 : 사망보험금 지급";
        String text2 = "2. 피보험자에게 보험기간 중 다음 각 목의 어느 하나의 사유가 발생하였을 경우(최초 사유발생에 한하여 1회한) : CI보험금 지급";
        String text3 = ". 피보험자에게 보험기간 중 다음 각 목의 어느 하나의 사유가 발생하였을 경우(최초 사유발생에 한하여 1회한) : CI보험금 지급";
        String text4 = "223. 피보험자에게 보험기간 중 다음 각 목의 어느 하나의 사유가 발생하였을 경우(최초 사유발생에 한하여 1회한) : CI보험금 지급";

        //Then
        assertThat(text1).matches(regex);
        assertThat(text2).matches(regex);
        assertThat(text3).doesNotMatch(regex);
        assertThat(text4).matches(regex);

    }

    @Test
    @DisplayName("항인지 확인하는 정규표현식 테스트")
    void isParagraph() {
    
        //Given : ① ~ ⑳의 유니코드 범위
        char testChar1 = '\u2460';
        char testChar2 = '\u2473';
        String regex = "^[\u2460-\u2473].*$";

        //When : 항의 텍스트인지 확인
        String text1 = "① 이 계약에 있어서 ‘중대한 질병’이라 함은 ‘중대한 암’ 및 ‘중대한 암 이외의 중대한 질병’으로 ‘중대한 질병’의 정의(별표 4 참조)에서 정한 질병을 말합니다.";
        String text2 = "② 이 계약에 있어서 ‘유방암’이라 함은 ‘중대한 암’ 중에서 제7차 개정 한국표준질병·사인분류 중 분류번호 C50(유방의 악성 신생물)에 해당하는 질병을 말합니다.";
        String text3 = "이 계약에 있어서 ‘유방암’이라 함은 ‘중대한 암’ 중에서 제7차 개정 한국표준질병·사인분류 중 분류번호 C50(유방의 악성 신생물)에 해당하는 질병을 말합니다.";

        //Then
        assertThat(text1).matches(regex);
        assertThat(text2).matches(regex);
        assertThat(text3).doesNotMatch(regex);

    }

    @Test
    @DisplayName("조인지 확인하는 정규표현식 테스트")
    void isArticle() {
    
        // Given
        String regex = "^제\\d+조\\([가-힣A-Za-z0-9\\s'‘’]+\\)$";

        // When
        String text1 = "제1조(목적)";
        String text2 = "제2조(용어의 정의)";

        Boolean matches1 = Pattern.matches(regex, text1);
        Boolean matches2 = Pattern.matches(regex, text2);

        String text3 = "자2조(용어의 정의)";
        String text4 = "제4저(용어의 정의)";

        String text5 = "제4조(‘중대한 수술’의 정의)";

        Boolean matches3 = Pattern.matches(regex, text3);
        Boolean matches4 = Pattern.matches(regex, text4);

        Boolean matches5 = Pattern.matches(regex, text5);

        // Then
        assertThat(matches1).isTrue();
        assertThat(matches2).isTrue();

        assertThat(matches3).isFalse();
        assertThat(matches4).isFalse();

        assertThat(matches5).isTrue();

    }
    
    @Test
    @DisplayName("조를 객체로 바꿀때 문자열 자르는 테스트")
    void articleSubstr() {
    
        // Given
        String text1 = "제 1 관 목적 및 용어의 정의";
        String text2 = "제 2 관 보험금의 지급";
        String text3 = "제 124 관 보험금의 지급";

        // When
        String title1 = text1.substring(0, text1.indexOf("관") + 1);
        String title2 = text2.substring(0, text2.indexOf("관") + 1);

        Integer no1 = Integer.parseInt(text1.substring(text1.indexOf("제") + 2, text1.indexOf("관") - 1));
        Integer no2 = Integer.parseInt(text2.substring(text2.indexOf("제") + 2, text2.indexOf("관") - 1));
        Integer no3 = Integer.parseInt(text3.substring(text3.indexOf("제") + 2, text3.indexOf("관") - 1));

        String content = text1.substring(text1.indexOf("관") + 2);

        // Then
        assertThat(title1).isEqualTo("제 1 관");
        assertThat(title2).isEqualTo("제 2 관");

        assertThat(no1).isEqualTo(1);
        assertThat(no2).isEqualTo(2);
        assertThat(no3).isEqualTo(124);

        assertThat(content).isEqualTo("목적 및 용어의 정의");

    }
    
    @Test
    @DisplayName("관인지 확인하는 테스트")
    void isSubSectionTest() {
    
        // Given
        String regex = "^제\\s\\d+\\s관\\s[가-힣A-Za-z0-9\\s]+$";

        // When
        String text1 = "제 1 관 목적 및 용어의 정의";
        String text2 = "제 2 관 보험금의 지급";

        Boolean matches1 = Pattern.matches(regex, text1);
        Boolean matches2 = Pattern.matches(regex, text2);

        String text3 = "제1관 목적 및 용어의 정의";
        String text4 = "제 1 관목적 및 용어의 정의";
        String text5 = "저 2 관 보험금의 지급";

        Boolean matches3 = Pattern.matches(regex, text3);
        Boolean matches4 = Pattern.matches(regex, text4);
        Boolean matches5 = Pattern.matches(regex, text5);

        // Then
        assertThat(matches1).isTrue();
        assertThat(matches2).isTrue();

        assertThat(matches3).isFalse();
        assertThat(matches4).isFalse();
        assertThat(matches5).isFalse();
    }

}//End of class
