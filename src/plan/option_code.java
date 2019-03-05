package plan;

public class option_code {
	public static final String LOGIN_OK = "101";
    public static final String LOGIN_WRONG_PSD = "102";
    public static final String LOGIN_WRONG_ID = "103";
	
	public static final String REG_DUPID = "201";
	public static final String REG_OK = "202";
	public static final String REG_CREATE_ERROR = "203";
	
	public static final String UPDATE_OK = "301";
	public static final String UPDATE_ERROR = "302";
	
	public static final String INSERT_PLAN_OK = "401";
	public static final String INSERT_PLAN_ERROR = "402";
	
	public static final String CHANGE_PSD_OK = "501";
	public static final String CHANGE_PSD_ERROR = "502";
	
	//计划标签
    public final static int TAG_STUDY = 0;
    public final static int TAG_WORK = 1;
    public final static int TAG_LIFE = 2;

    //计划颜色
    public final static int COLOR_RED = 0;
    public final static int COLOR_ORANGE = 1;
    public final static int COLOR_YELLOW = 2;
    public final static int COLOR_GREEN = 3;
    public final static int COLOR_BLUE = 4;
    
  //计划状态
    public final static int STATE_EXECUTING = 0;      //执行中
    public final static int STATE_HISTORY = 1;        //历史计划
    public final static int STATE_GIVE_UP = 2;        //回收站中已放弃计划
    
  //计划执行状态
    public final static int FIN_STATE_UNDO = 0;
    public final static int FIN_STATE_START = 1;
    public final static int FIN_STATE_FINISHEN = 2;
}
