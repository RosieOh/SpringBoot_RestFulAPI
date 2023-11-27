package com.pro06.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;

    private String keyword;

    private String[] getTypes() {
        if(type == null || type.isEmpty()) {
            return null;
        }

        return type.split("");
    }

    public Pageable getPageAble(String...props) {
        return PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
    }

    private String link;

    public String getLink() {
        if(link == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("page= " + this.page);
            stringBuilder.append("&size= " + this.size);
            if(type != null) {
                stringBuilder.append("&type=" + type);
            }

            if (keyword != null) {
                try {
                    stringBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
        }

        return link;
    }
}
