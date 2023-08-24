package zlhywlf.office.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class Product {
    long id; //id
    String name; // 名称
    double value; // 值
    double coefficient = 1; // 折标系数
    String procedure; //所属工序
    String type; //类型
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date; // 日期
    String dateStr;

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String s = date == null ? "null" : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(f);
        return "[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", coefficient=" + coefficient +
                ", procedure='" + procedure + '\'' +
                ", type='" + type + '\'' +
                ", date=" + s +
                ", dateStr="+dateStr+
                ']';
    }
}
