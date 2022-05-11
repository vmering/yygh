import com.alibaba.excel.EasyExcel;

public class testRead {
    public static void main(String[] args) {
        //设置excel文件路径名称
        String fileName = "G:\\桌面\\test.xlsx";
        //调用方法实现读取操作
        EasyExcel.read(fileName,userData.class,new ExcelListener()).sheet().doRead();
    }
}
