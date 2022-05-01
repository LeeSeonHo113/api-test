package site.metacoding.apitest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DownloadDto {
    private Response response;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class Response {
        private Header header;
        private Body body;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        class Header {
            private String resultCode;
            private String resultMsg;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        class Body {
            private Items items;
            private Integer pageNo;
            private Integer numOfRows;
            private Integer totalCount;

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            class Items {
                private List<Item> item;

                @AllArgsConstructor
                @NoArgsConstructor
                @Data
                class Item {
                    private String courseName;
                    private String courseAreaName;
                    private String spotAreaName;
                    private String spotName;
                    private String tm;
                    private Integer sky;
                    private Integer rn;
                }
            }
        }
    }
}