package com.test.parsor.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class RegexTest {

    @Test
    @DisplayName("조인지 확인하는 정규표현식 테스트")
    void isArticle() {
    
        // Given
        String regex = "^제\\d+조\\([가-힣A-Za-z0-9\\s]+\\)$";

        // When
        String text1 = "제1조(목적)";
        String text2 = "제2조(용어의 정의)";

        Boolean matches1 = Pattern.matches(regex, text1);
        Boolean matches2 = Pattern.matches(regex, text2);

        String text3 = "자2조(용어의 정의)";
        String text4 = "제4저(용어의 정의)";

        Boolean matches3 = Pattern.matches(regex, text3);
        Boolean matches4 = Pattern.matches(regex, text4);

        // Then
        assertThat(matches1).isTrue();
        assertThat(matches2).isTrue();

        assertThat(matches3).isFalse();
        assertThat(matches4).isFalse();

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
