package com.lpp.kiven.util;

/**
 * @author ：zhangliang
 * @date ：Created in 2020/3/31 14:09
 * @description：
 * @modified By：
 * @version: $
 */
public class ParamUtils {
    public static final String TOBSALE="01";//b端销售-cardStyle
    public static final String TOBMARKET="02";//B端营销
    public static final String TOCSALE="03";//C端销售

    public static final String INTERFACE_SEND="00";//接口发送 -cardType
    public static final String KEY_SEND="01";//卡密发送
    public static final String CLIENT_SALE="02";//自有客户端销售

    public static final String STOCKTYPE_SALE="02";//erp入库类型-销售卡
    public static final String STOCKTYPE_MARKET="03";//erp入库类型-营销卡

    public static final String CUSTOMERTYPE_2B="2B";//erp客户类型-企业客户
    public static final String CUSTOMERTYPE_2C="2C";//erp客户类型-个人客户
    public static final String ProductType_MC="01";//erp入库-音乐卡
    public static final String ProductType_PM="02";//erp入库-白金卡

    public static final int EXECUTE_AFTER = 0;
    public static final int EXECUTE_BEFORE = 1;
    public static final int EXECUTE_ING = 2;

    public static final String EXECUTE_AFTER_CNAME = "已执行";
    public static final String EXECUTE_BEFORE_CNAME = "未执行";
    public static final String EXECUTE_ING_CNAME = "执行中";

    public static final int APPLY = 1;
    public static final int ADUIT_ING = 2;
    public static final int RETRACT = 3;
    public static final int AUDIT_PASS = 4;
    public static final int AUDIT_REFUSE = 5;
    public static final int WAIT_FOR_EXCUTE = 6;
    public static final int STORAGE = 7;
    public static final int TOACTIVATE = 8;
    public static final int FINISH = 9;

    public static final String APPLY_CNAME = "申请";
    public static final String ADUIT_ING_CNAME = "审批中";
    public static final String RETRACT_CNAME = "撤销";
    public static final String AUDIT_PASS_CNAME = "审批通过";
    public static final String AUDIT_REFUSE_CNAME = "审批拒绝";
    public static final String WAIT_FOR_EXCUTE_CNAME = "制卡中";
    public static final String STORAGE_CNAME = "已入库";
    public static final String TOACTIVATE_CNAME = "待激活";
    public static final String FINISH_CNAME = "完成";

    public static final String MC_HI="39";//票务卡
    public static final String PM_HI="38";//白金会员卡
    public static final String DC_HI="40";//ios音乐卡
    public static final String VO_HI="41";//投票卡
    public static final String DI_HI="42";//打折卡
    public static final String DE_HI="43";//道具券
    public static final String DA_HI="44";//数字专辑卡
    public static final String CL_HI="45";//彩铃包月卡
    public static final String DT_HI="46";//数字专辑券
    public static final String ST_HI="47";//歌曲下载券
    public static final String EC_HI="99";//其他（兑换）卡
    public static final String PA_HI="98";//其他（支付）卡

    public static final String MC="MC";
    public static final String PM="PM";
    public static final String DC="DC";
    public static final String VO="VO";
    public static final String DI="DI";
    public static final String DE="DE";
    public static final String DA="DA";
    public static final String CL="CL";
    public static final String DT="DT";
    public static final String ST="ST";
    public static final String EC="EC";
    public static final String PA="PA";


    private static String[] busiTypeArry={"38","39","40","41","42","43","44","45","46","47","98","99"};
    private static String[] busiTypeKeys={"PM","MC","DC","VO","DI","DE","DA","CL","DT","ST","PA","EC"};


    //会议管控
    public static final String AUDIT_TYPE_N="1"; //新增审批
    public static final String AUDIT_TYPE_U="2"; //修改审批

    public static final String ACTION_TYPE_ZC="1"; //直充
    public static final String ACTION_TYPE_YL="2"; //用户领取
    public static final String ACTION_TYPE_JY="3"; //简便用户领取
    public static final String ACTION_TYPE_DH="4"; //兑换码-卡平台
    public static final String ACTION_TYPE_RE="5"; //兑换码

    public static final String ACTION_STATUS_NEW="0";  //新建
    public static final String ACTION_STATUS_ING="1"; //审批中-活动状态
    public static final String ACTION_STATUS_PASS="2"; //审批通过
    public static final String ACTION_STATUS_FUSE="3"; //审批拒绝

    public static final String AUDIT_STATUS_RE="0"; //未开始 -审批状态
    public static final String AUDIT_STATUS_ING="1"; //审批中
    public static final String AUDIT_STATUS_PASS="2"; //审批通过
    public static final String AUDIT_STATUS_FUSE="3"; //审批拒绝

    public static final String ACTION_USER_FLAG_RE="0"; //用户会员订购标识-未开始同步
    public static final String ACTION_USER_FLAG_FAIL="1"; //用户会员订购标识-同步失败
    public static final String ACTION_USER_FLAG_SUCCESS="2"; //用户会员订购标识-同步成功

    public static final String SYN_FLAG_SUCCESS="1"; //同步一级标识-同步成功
    public static final String SYN_FLAG_FAIL="0"; //同步一级标识-同步失败


    public static final String MEMBER_HTTP_SUCCESS="2000000000";

    public static final String AUDIT_1="1"; //一级审批
    public static final String AUDIT_2="2"; //二级审批
    public static final String AUDIT_3="3"; //三级审批
    public static final String AUDIT_4="4"; //四级审批
    public static final String AUDIT_5="5"; //五级审批

    public static final String AUDIT_AUTHORITY_Y="1"; //有审批权限
    public static final String AUDIT_AUTHORITY_N="0"; //无审批权限

    public static final String AUDIT_RESULT_PASS="1";//审批通过
    public static final String AUDIT_RESULT_REFUSE="0";//审批拒绝

    /**
     * 文件名后缀
     */
    public static final String XLSX = ".xlsx";
    public static final String XLS=".xls";
    public static final String CSV=".csv";
    public static final String TXT=".txt";

    public static final String JSON_NULL="";

    public static final String AUDIT_NO="1"; //待审批 --缺货登记审批
    public static final String AUDIT_SUCCESS="0"; //审批通过
    public static final String AUDIT_FAIL="2"; //审批拒绝

    public static final String MSG_NO="0"; //未开始  --短信发送状态
    public static final String MSG_ING="1";//发送中
    public static final String MSG_OK="2";//发送完成

    public static final String AUDIT_DEFINE="0"; //不通过  --短信发送状态
    public static final String AUDIT_YES="1";//通过


    public static final String EXCEL="0"; //导出文件类型0-Excel
    public static final String PDF="1";   //导出文件类型0-PDF

    public static final String OWN="00"; //项目来源-自有
    public static final String YL="01"; //项目来源-永乐
    public static final String MY="02";   //项目来源-猫眼


    public static final String MC_PAY="30"; //支付方式-音乐卡
    public static final String ZH_PAY="31";   //支付方式-招行
    public static final String WECHAT_PAY="32"; //支付方式-微信
    public static final String AL_PAY="33"; //支付方式-支付宝

    public static final String SALE_ONLINE="10"; //项目销售方式-在线销售
    public static final String SALE_HF="20";   //项目销售方式-后台销售分销票
    public static final String SALE_HZ="21"; //项目销售方式-后台销售赠票
    public static final String SALE_HG="22"; //项目销售方式-后台销售工作票

    public static String[] getBusiTypeKeys() {
        return busiTypeKeys;
    }

    public static void setBusiTypeKeys(String[] busiTypeKeys) {
        ParamUtils.busiTypeKeys = busiTypeKeys;
    }

    public static String[] getBusiTypeArry() {
        return busiTypeArry;
    }

    public static void setBusiTypeArry(String[] busiTypeArry) {
        ParamUtils.busiTypeArry = busiTypeArry;
    }
}
