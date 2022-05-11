import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class userData {

    //value表示字段名，index表示哪一列
    @ExcelProperty(value = "用户标号",index = 0)
    private int uid;

    @ExcelProperty(value = "用户名称",index = 1)
    private String username;

    @ExcelProperty(value = "用户年龄",index = 2)
    private  int age;
}
