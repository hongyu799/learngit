package com.exp.model;

import java.util.Date;

import com.util.PropUtil;

/**
 * 项目
 */
public class Project extends BaseModel {
	private int wcmivTableId;// WCMIVTABLE11ID NUMBER(10) not null,
	private int documentId;// DOCUMENTID NUMBER(10),
	private int channelId;// CHANNELID NUMBER(10),
	private int infoViewId;// INFOVIEWID NUMBER(10),
	private String crUser;// CRUSER VARCHAR2(50 CHAR),
	private Date crTime;// CRTIME DATE,
	private String postUser;// POSTUSER VARCHAR2(50 CHAR),
	private String docNo;// DOCNO VARCHAR2(1000 CHAR),
	private String randomSerial;// RANDOMSERIAL VARCHAR2(100 CHAR),
	private String rootElement;// ROOTELEMENT VARCHAR2(50 CHAR),
	private String docTitle;// DOCTITLE VARCHAR2(1000 CHAR),
	private String xiangMuMingCheng;// XIANG_MU_MING_CHENG VARCHAR2(750
	// CHAR),项目名称
	private String ruanJianKaiFa;// RUAN_JIAN_KAI_FA VARCHAR2(75 CHAR),软件开发
	private String xiTongJiCheng;// XI_TONG_JI_CHENG VARCHAR2(75 CHAR),系统集成
	private String wangLuoGongCheng;// WANG_LUO_GONG_CHENG VARCHAR2(75
	// CHAR),网络工程
	private String xinXiAnQuan;// XIN_XI_AN_QUAN VARCHAR2(75 CHAR),信息安全
	private String jiFangJianShe;// JI_FANG_JIAN_SHE VARCHAR2(75 CHAR),机房建设
	private String yunWeiGuanLi;// YUN_WEI_GUAN_LI VARCHAR2(75 CHAR),运维管理
	private String quanBu;//全部
	private String xiangMuBeiJing;// XIANG_MU_BEI_JING VARCHAR2(3000 CHAR)项目背景
	private String tiJiaoRen;// TI_JIAO_REN
	private int docStatus;
	private String tableName;
	private String idName;

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private String zhuanJiaRenShu;// ZHUAN_JIA_REN_SHU;

	public String getZhuanJiaRenShu() {
		return zhuanJiaRenShu;
	}

	public void setZhuanJiaRenShu(String zhuanJiaRenShu) {
		this.zhuanJiaRenShu = zhuanJiaRenShu;
	}

	private PageBean page;

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public int getDocStatus() {
		return docStatus;
	}

	public boolean isNewDoc() {
		return docStatus == 16;
	}

	public boolean isFinishDoc() {
		return docStatus == 23;
	}

	public String getStatus() {
		if (isNewDoc())
			return "抽取专家";
		if (isFinishDoc())
			return "已完成";
		return "审批中";
	}

	public void setDocStatus(int docStatus) {
		this.docStatus = docStatus;
	}

	public String getTiJiaoRen() {
		return tiJiaoRen;
	}

	public void setTiJiaoRen(String tiJiaoRen) {
		this.tiJiaoRen = tiJiaoRen;
	}

	public String getTableName() {
		return PropUtil.getPropValue("protable");
	}

	public String getIdName() {
		return PropUtil.getPropValue("proid");
	}

	public int getWcmivTableId() {
		return wcmivTableId;
	}

	public void setWcmivTableId(int wcmivTableId) {
		this.wcmivTableId = wcmivTableId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getInfoViewId() {
		return infoViewId;
	}

	public void setInfoViewId(int infoViewId) {
		this.infoViewId = infoViewId;
	}

	public String getCrUser() {
		return crUser;
	}

	public void setCrUser(String crUser) {
		this.crUser = crUser;
	}

	public Date getCrTime() {
		return crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

	public String getPostUser() {
		return postUser;
	}

	public void setPostUser(String postUser) {
		this.postUser = postUser;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getRandomSerial() {
		return randomSerial;
	}

	public void setRandomSerial(String randomSerial) {
		this.randomSerial = randomSerial;
	}

	public String getRootElement() {
		return rootElement;
	}

	public void setRootElement(String rootElement) {
		this.rootElement = rootElement;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getXiangMuMingCheng() {
		return xiangMuMingCheng;
	}

	public void setXiangMuMingCheng(String xiangMuMingCheng) {
		this.xiangMuMingCheng = xiangMuMingCheng;
	}

	public String getRuanJianKaiFa() {
		return null != ruanJianKaiFa && ruanJianKaiFa.trim().equals("true") ? //
		"软件开发"
				: "";
	}

	public void setRuanJianKaiFa(String ruanJianKaiFa) {
		this.ruanJianKaiFa = ruanJianKaiFa;
	}

	public String getXiTongJiCheng() {
		return null != xiTongJiCheng && xiTongJiCheng.trim().equals("true") ? //
		"系统集成"
				: "";
	}

	public void setXiTongJiCheng(String xiTongJiCheng) {
		this.xiTongJiCheng = xiTongJiCheng;
	}

	public String getWangLuoGongCheng() {
		return null != wangLuoGongCheng
				&& wangLuoGongCheng.trim().equals("true") ? //
		"网络工程"
				: "";
	}

	public void setWangLuoGongCheng(String wangLuoGongCheng) {
		this.wangLuoGongCheng = wangLuoGongCheng;
	}

	public String getXinXiAnQuan() {
		return null != xinXiAnQuan && xinXiAnQuan.trim().equals("true") ? //
		"信息安全 "
				: "";
	}

	public void setXinXiAnQuan(String xinXiAnQuan) {
		this.xinXiAnQuan = xinXiAnQuan;
	}

	public String getJiFangJianShe() {
		return null != jiFangJianShe && jiFangJianShe.trim().equals("true") ? //
		"机房建设 "
				: "";
	}

	public void setJiFangJianShe(String jiFangJianShe) {
		this.jiFangJianShe = jiFangJianShe;
	}

	public String getYunWeiGuanLi() {
		return null != yunWeiGuanLi && yunWeiGuanLi.trim().equals("true") ? //
		"运维管理"
				: "";
	}

	public void setYunWeiGuanLi(String yunWeiGuanLi) {
		this.yunWeiGuanLi = yunWeiGuanLi;
	}
    //新加的"全部"
	public String getQuanBu() {
		return null!=quanBu && quanBu.trim().equals("true") ?
				"全部"
				    :"";
	}

	public void setQuanBu(String quanBu) {
		this.quanBu = quanBu;
	}

	public String getXiangMuBeiJing() {
		return xiangMuBeiJing;
	}

	public void setXiangMuBeiJing(String xiangMuBeiJing) {
		this.xiangMuBeiJing = xiangMuBeiJing;
	}

}
