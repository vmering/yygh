import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<userData> {

    //一行一行读取excel内容，从第二行开始读取
    @Override
    public void invoke(userData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }
    //读取表头信息也就是字段名
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
