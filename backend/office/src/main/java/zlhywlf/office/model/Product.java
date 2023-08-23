package zlhywlf.office.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    long id; //id
    String name; // 名称
    String nameZh; // 中文名称
    double value; // 值
    String unit; // 单位
    double coefficient = 1; // 折标系数
    String procedure; //所属工序
    String type; //类型
    Date date; // 日期
}
