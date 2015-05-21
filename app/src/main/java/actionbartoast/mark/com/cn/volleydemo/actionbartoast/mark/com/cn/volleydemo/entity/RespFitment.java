package actionbartoast.mark.com.cn.volleydemo.actionbartoast.mark.com.cn.volleydemo.entity;

import java.util.List;

public class RespFitment extends RespBase {
    private List<Fitment> dic;
    private String Times;

    public List<Fitment> getDic() {
        return dic;
    }

    public void setDic(List<Fitment> dic) {
        this.dic = dic;
    }

    public String getTimes() {
        return Times;
    }

    public void setTimes(String times) {
        Times = times;
    }


}
