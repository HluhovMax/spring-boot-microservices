package io.javabraims.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by 38066 on 19.03.2019.
 */
@Data
@AllArgsConstructor
public class CatalogItem {

    private String name;
    private String desc;
    private int rating;
}
