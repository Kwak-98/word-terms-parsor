//파일을 업로드하지 않고 제출하는 경우 방지
$("form").on("submit", function (e){
    let fileCheck = $("input[name=file]");

    if(fileCheck.get(0).files.length == 0) {
        alert("파일을 선택해주세요.");
        e.preventDefault();
        return;
    }

});


$("input[name=file").on("change", function(){
    let maxSize = 300 * 1024 * 1024;                             // 300MB 최대 사이즈 제한
    let minSize = 1 * 1024;                                      // 1KB 최소 사이즈 제한
    let fileSize = this.files[0].size;                           // 업로드한 파일용량
    let fileName = this.files[0].name;                           // 업로드한 파일의 이름
    let fileExtension = fileName.split('.').pop().toLowerCase(); // 파일 확장자 추출

    // 파일 확장자 체크
    if (fileExtension !== 'docx') {
        alert("docx 파일만 업로드 가능합니다.");
        $(this).val(''); // 업로드한 파일 제거
        return;
    }

    // 파일 용량 체크
    if (fileSize > maxSize) {
        alert("파일 용량은 300MB 이하로 가능합니다.");
        $(this).val(''); // 업로드한 파일 제거
        return;
    }

    if (fileSize < minSize) {
        alert("파일 용량이 너무 작습니다.");
        $(this).val(''); // 업로드한 파일 제거
        return;
    }

});