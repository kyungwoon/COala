package com.clone.finalProject.controller;


import com.clone.finalProject.domain.Fword;
import com.clone.finalProject.repository.FwordRepository;
import com.clone.finalProject.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TxtReadController {

    private final CacheService cacheService;
    private final FwordRepository fwordRepository;
    
    // txt파일을 읽고 한줄씩 내려가면서 출력
    @GetMapping("/test/read/txt")
    public String readTxt() throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources";

        String filename = "\\fword_list.txt";

        BufferedReader br = new BufferedReader(new FileReader(projectPath+filename));

        String str;

        while ((str = br.readLine()) != null) {
            log.info(str);
        }
        br.close();

        return "ok";
    }

    //내장 캐시로 욕설 디비담는 테스트 진행 중
    @GetMapping("/test/pls")
    public HashMap<Integer,String> testpls() {
        HashMap<Integer,String> fowrds = cacheService.getCacheData("key");
        return fowrds;
    }

    // 욕설 디비 담기 및 테스트
    @Transactional
    @GetMapping("/test/get/text")
    public void getText(){

    // 욕설 txt DB에 담는 로직
        List<Fword> fwordList = fwordRepository.findAll();

        for (Fword fword : fwordList) {
            String st= fword.getFWord().trim();
            fword.update(st);
        }
        log.info("공백 없애고 DB에 저장 완료");

        // containsValue 테스트
//        HashMap<Integer,String> fowrds= cacheService.getCacheData("key");
//        log.info(String.valueOf(fowrds.containsValue("잡놈")));
//        전체 출력문
//        for (Map.Entry<Integer, String> entrySet : fowrds.entrySet()) {
//            log.info(entrySet.getKey() + " : " + entrySet.getValue());
//        }

    }

}
