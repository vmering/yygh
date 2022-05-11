import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class testWrite {
    public static void main(String[] args) {
        //数据集合
        List<userData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userData userData =new userData();
            userData.setUid(i);
            userData.setUsername("hello"+i);
            userData.setAge(20+i);
            data.add(userData);
        }
        //设置excel文件路径名称
        String fileName = "G:\\桌面\\test.xlsx";
        //调用方法实现写操作
        EasyExcel.write(fileName,userData.class).sheet("用户信息").doWrite(data);

    }
}
