package db;

import plan.option_code;

public class Plan {
	private int id;             //plan的id，唯一标识符
    private String u_id;        //用户id
    private String title;       //标题
    private int tag = option_code.TAG_STUDY;            //标签  0:学习，1：工作，2：生活
    private int color = option_code.COLOR_RED;          //颜色，表示计划重要程度 0：红，1：橙，2：黄，3：绿，4：蓝
    private String p_start;       //计划开始时间
    private String during;        //计划持续时间
    private String start;         //实际开始时间
    private String end;           //实际结束时间
    private boolean ring = true;       //是否提醒，默认提醒
    private int interval = 1;     //提醒间隔
    private String ringtime = "10:00";   //默认十点提醒
    private String describe;    //计划描述
    private double progress = 0.00;    //进度
    private int state = option_code.STATE_EXECUTING;       //计划状态,0：执行中，1：历史，2：回收，3：草稿
    private int fin_state = option_code.FIN_STATE_UNDO;      //计划完成状态，0：未开始，1：未完成，2：已完成
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getP_start() {
        return p_start;
    }

    public void setP_start(String p_start) {
        this.p_start = p_start;
    }

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = during;
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

    public boolean isRing() {
        return ring;
    }

    public void setRing(boolean ring) {
        this.ring = ring;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getRingtime() {
        return ringtime;
    }

    public void setRingtime(String ringtime) {
        this.ringtime = ringtime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFin_state() {
        return fin_state;
    }

    public void setFin_state(int fin_state) {
        this.fin_state = fin_state;
    }

}
