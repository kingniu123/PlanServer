package db;

import plan.option_code;

public class SubPlan {
	private int sub_id;
    private int p_id;               //绑定的主计划
    private String title;           //子计划标题
    private int weight = 1;         //子计划比重，默认为1
    private String start;           //子计划实际开始时间
    private String end;             //子计划实际完成时间
    private String describe;        //子计划详情/备注
    private int state = option_code.FIN_STATE_UNDO;     //计划完成情况
    
    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
