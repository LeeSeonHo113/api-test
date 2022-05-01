package site.metacoding.apitest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class DownloadController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/json; charset=UTF8")
    public String download() {
        StringBuffer sb = new StringBuffer();
        sb.append("https://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?");
        sb.append( // 인증키
                "serviceKey=%2BPdFyDtb1rWEniBuuWkok4KLv1Pl9edceG8MDtcRYDXAi%2B4GlbiyF1zfVayCTi1tQ1TB%2B6fJT8ToUAhUocOWgg%3D%3D&");
        sb.append("pageNo=1&"); // 페이지 번호
        sb.append("numOfRows=2&"); // 한 페이지의 결과수
        sb.append("dataType=json&"); // 데이터 타입
        sb.append("CURRENT_DATE=2022042710&"); // 현재시각
        sb.append("HOUR=24&"); // 예보기간
        sb.append("COURSE_ID=1"); // 코스 아이디

        RestTemplate rt = new RestTemplate();
        // sb.toString을 해줘야한다.
        String result = rt.getForObject(
                "https://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?serviceKey=%2BPdFyDtb1rWEniBuuWkok4KLv1Pl9edceG8MDtcRYDXAi%2B4GlbiyF1zfVayCTi1tQ1TB%2B6fJT8ToUAhUocOWgg%3D%3D&pageNo=1&numOfRows=2&dataType=json&CURRENT_DATE=2022042810&HOUR=24&COURSE_ID=1",
                String.class);

        System.out.println(result);

        return "";
    }

    @GetMapping("/weather/{code}")
    public @ResponseBody DownloadDto test(@PathVariable String code) {

        DownloadDto dto = null;

        try {

            StringBuffer sb = new StringBuffer();

            sb.append("http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?");
            sb.append(
                    "serviceKey=%2BPdFyDtb1rWEniBuuWkok4KLv1Pl9edceG8MDtcRYDXAi%2B4GlbiyF1zfVayCTi1tQ1TB%2B6fJT8ToUAhUocOWgg%3D%3D&");
            sb.append("pageNo=1&");
            sb.append("numOfRows=1&");
            sb.append("dataType=json&");
            sb.append("CURRENT_DATE=2022042810&");
            sb.append("HOUR=24&");
            sb.append("COURSE_ID=" + code);

            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String result = br.readLine();
            Gson gson = new Gson();

            dto = gson.fromJson(result.toString(), DownloadDto.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

}