package com.exp.model;

import java.util.Date;

import com.util.PropUtil;

/**
 * 专家
 */
public class Expert extends BaseModel {
	private int wcmivTableId;// WCMIVTABLE10ID NUMBER(10) not null,
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
	private Date shenQingRiQi;// SHEN_QING_RI_QI DATE,申请日期
	private String bianHao;// BIAN_HAO VARCHAR2(750 CHAR),编号
	private String xingMing;// XING_MING VARCHAR2(750 CHAR),姓名
	private String xingBie;// XING_BIE VARCHAR2(750 CHAR),性别
	private Date chuShengRiQi;// CHU_SHENG_RI_QI DATE,出生日期
	private String minZu;// MIN_ZU VARCHAR2(750 CHAR),民族
	private String zhaoPian;// ZHAO_PIAN VARCHAR2(750 CHAR),照片
	private String gongZuoDanWei;// GONG_ZUO_DAN_WEI VARCHAR2(750 CHAR),工作单位
	private String suoZaiChengShi;// SUO_ZAI_CHENG_SHI VARCHAR2(750 CHAR),所在城市
	private String zhiWu;// ZHI_WU VARCHAR2(750 CHAR),职务
	private String zhiCheng;// ZHI_CHENG VARCHAR2(750 CHAR),职称
	private Date pingDingShiJian;// PING_DING_SHI_JIAN DATE,评定时间
	private String xueLi;// XUE_LI VARCHAR2(750 CHAR),学历
	private String suoZaiXueXiao;// SUO_ZAI_XUE_XIAO VARCHAR2(750 CHAR),所在学校
	private Date biYeShiJian;// BI_YE_SHI_JIAN DATE,毕业时间
	private String congShiZhuanYe;// CONG_SHI_ZHUAN_YE VARCHAR2(750 CHAR),从事职业
	private String congShiShiJian;// CONG_SHI_SHI_JIAN VARCHAR2(750 CHAR),从事时间
	private String shenTiZhuangKuang;// SHEN_TI_ZHUANG_KUANG VARCHAR2(750
	// CHAR),身体状况
	private String lianXiDiZhi;// LIAN_XI_DI_ZHI VARCHAR2(750 CHAR),联系地址
	private String youBian;// YOU_BIAN VARCHAR2(750 CHAR),邮编
	private String zhuZhaiDianHua;// ZHU_ZHAI_DIAN_HUA VARCHAR2(750 CHAR),住宅电话
	private String yiDongDianHua;// YI_DONG_DIAN_HUA VARCHAR2(750 CHAR),移动电话
	private String shenFenZhengHao;// SHEN_FEN_ZHENG_HAO VARCHAR2(750 CHAR),身份证号
	private String youXiangDiZhi;// YOU_XIANG_DI_ZHI VARCHAR2(750 CHAR),邮箱地址
	private String zhuanYe1;// ZHUAN_YE1 VARCHAR2(750 CHAR),专业
	private String zhuanYe2;// ZHUAN_YE2 VARCHAR2(750 CHAR),专业
	private String zhuanYe3;// ZHUAN_YE3 VARCHAR2(750 CHAR),专业
	private String zhiChengZhengShu1;// ZHI_CHENG_ZHENG_SHU1 VARCHAR2(750
	// CHAR),职称证书
	private String zhiChengZhengShu2;// ZHI_CHENG_ZHENG_SHU2 VARCHAR2(750
	// CHAR),职称证书
	private String zhiChengZhengShu3;// ZHI_CHENG_ZHENG_SHU3 VARCHAR2(750
	private String banGongDianHua;// BAN_GONG_DIAN_HUA VARCHAR2(750 CHAR),办公电话
	
	// CHAR),职称证书
	//private String suoZaiDanWeiYiJian;// SUO_ZAI_DAN_WEI_YI_JIAN VARCHAR2(3000--
	// CHAR),所在单位意见
	//private Date suoZaiDanWeiYiJianRiQiDate;// SUO_ZAI_DAN_WEI_YI_JIAN_RI_QI--
	// DATE,所在单位一件日期时间
	//private String dzzwbgsyj;// DZZWBGSYJ VARCHAR2(3000 CHAR),--
	//private Date dzzwbgsyjrq;// DZZWBGSYJRQ DATE,--
	//private String xzzgbmyj;// XZZGBMYJ VARCHAR2(3000 CHAR),--
	//private Date xzzgbmyjrq;// XZZGBMYJRQ DATE,--
	
	//private String lianXiDianHua;// LIAN_XI_DIAN_HUA VARCHAR2(750 CHAR),联系电话--
	//private String zhengShuShangChuan1;// ZHENG_SHU_SHANG_CHUAN1 VARCHAR2(750--
	// CHAR),证书上传
	//private String zhengShuShangChuan2;// ZHENG_SHU_SHANG_CHUAN2 VARCHAR2(750--
	// CHAR),证书上传
	//private String zhengShuShangChuan3;// ZHENG_SHU_SHANG_CHUAN3 VARCHAR2(750--
	// CHAR),证书上传
	//private Date danWeiYiJianRiQi;// DAN_WEI_YI_JIAN_RI_QI DATE,单位意见日期--
	//private String banGongShiYiJian;// BAN_GONG_SHI_YI_JIAN VARCHAR2(3000--
	// CHAR),办公室意见
	//private Date banGongShiYiJianRiQi;// BAN_GONG_SHI_YI_JIAN_RI_QI DATE,办公室意见日期--
	//private String zhuGuanYiJian;// ZHU_GUAN_YI_JIAN VARCHAR2(3000 CHAR),主管意见---
	//private Date zhuGuanYiJianRiQi;// ZHU_GUAN_YI_JIAN_RI_QI DATE主管意见日期---

	private int docStatus;

	private Project project = new Project();

	private String tableName;
	private String idName;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(int docStatus) {
		this.docStatus = docStatus;
	}

	public String getTableName() {
		return PropUtil.getPropValue("exptable");
	}

	public String getIdName() {
		return PropUtil.getPropValue("expid");
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

	public Date getShenQingRiQi() {
		return shenQingRiQi;
	}

	public void setShenQingRiQi(Date shenQingRiQi) {
		this.shenQingRiQi = shenQingRiQi;
	}

	public String getBianHao() {
		return bianHao;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	public String getXingMing() {
		return xingMing;
	}

	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}

	public String getXingBie() {
		return xingBie;
	}

	public void setXingBie(String xingBie) {
		this.xingBie = xingBie;
	}

	public Date getChuShengRiQi() {
		return chuShengRiQi;
	}

	public void setChuShengRiQi(Date chuShengRiQi) {
		this.chuShengRiQi = chuShengRiQi;
	}

	public String getMinZu() {
		return minZu;
	}

	public void setMinZu(String minZu) {
		this.minZu = minZu;
	}

	public String getZhaoPian() {
		return zhaoPian;
	}

	public void setZhaoPian(String zhaoPian) {
		this.zhaoPian = zhaoPian;
	}

	public String getGongZuoDanWei() {
		return gongZuoDanWei;
	}

	public void setGongZuoDanWei(String gongZuoDanWei) {
		this.gongZuoDanWei = gongZuoDanWei;
	}

	public String getSuoZaiChengShi() {
		return suoZaiChengShi;
	}

	public void setSuoZaiChengShi(String suoZaiChengShi) {
		this.suoZaiChengShi = suoZaiChengShi;
	}

	public String getZhiWu() {
		return zhiWu;
	}

	public void setZhiWu(String zhiWu) {
		this.zhiWu = zhiWu;
	}

	public String getZhiCheng() {
		return zhiCheng;
	}

	public void setZhiCheng(String zhiCheng) {
		this.zhiCheng = zhiCheng;
	}

	public Date getPingDingShiJian() {
		return pingDingShiJian;
	}

	public void setPingDingShiJian(Date pingDingShiJian) {
		this.pingDingShiJian = pingDingShiJian;
	}

	public String getXueLi() {
		return xueLi;
	}

	public void setXueLi(String xueLi) {
		this.xueLi = xueLi;
	}

	public String getSuoZaiXueXiao() {
		return suoZaiXueXiao;
	}

	public void setSuoZaiXueXiao(String suoZaiXueXiao) {
		this.suoZaiXueXiao = suoZaiXueXiao;
	}

	public Date getBiYeShiJian() {
		return biYeShiJian;
	}

	public void setBiYeShiJian(Date biYeShiJian) {
		this.biYeShiJian = biYeShiJian;
	}

	public String getCongShiZhuanYe() {
		return congShiZhuanYe;
	}

	public void setCongShiZhuanYe(String congShiZhuanYe) {
		this.congShiZhuanYe = congShiZhuanYe;
	}

	public String getCongShiShiJian() {
		return congShiShiJian;
	}

	public void setCongShiShiJian(String congShiShiJian) {
		this.congShiShiJian = congShiShiJian;
	}

	public String getShenTiZhuangKuang() {
		return shenTiZhuangKuang;
	}

	public void setShenTiZhuangKuang(String shenTiZhuangKuang) {
		this.shenTiZhuangKuang = shenTiZhuangKuang;
	}

	public String getLianXiDiZhi() {
		return lianXiDiZhi;
	}

	public void setLianXiDiZhi(String lianXiDiZhi) {
		this.lianXiDiZhi = lianXiDiZhi;
	}

	public String getYouBian() {
		return youBian;
	}

	public void setYouBian(String youBian) {
		this.youBian = youBian;
	}

	public String getZhuZhaiDianHua() {
		return zhuZhaiDianHua;
	}

	public void setZhuZhaiDianHua(String zhuZhaiDianHua) {
		this.zhuZhaiDianHua = zhuZhaiDianHua;
	}

	public String getYiDongDianHua() {
		return yiDongDianHua;
	}

	public void setYiDongDianHua(String yiDongDianHua) {
		this.yiDongDianHua = yiDongDianHua;
	}

	public String getShenFenZhengHao() {
		return shenFenZhengHao;
	}

	public void setShenFenZhengHao(String shenFenZhengHao) {
		this.shenFenZhengHao = shenFenZhengHao;
	}

	public String getYouXiangDiZhi() {
		return youXiangDiZhi;
	}

	public void setYouXiangDiZhi(String youXiangDiZhi) {
		this.youXiangDiZhi = youXiangDiZhi;
	}

	public String getZhuanYe1() {
		return zhuanYe1;
	}

	public void setZhuanYe1(String zhuanYe1) {
		this.zhuanYe1 = zhuanYe1;
	}

	public String getZhuanYe2() {
		return zhuanYe2;
	}

	public void setZhuanYe2(String zhuanYe2) {
		this.zhuanYe2 = zhuanYe2;
	}

	public String getZhuanYe3() {
		return zhuanYe3;
	}

	public void setZhuanYe3(String zhuanYe3) {
		this.zhuanYe3 = zhuanYe3;
	}

	public String getZhiChengZhengShu1() {
		return zhiChengZhengShu1;
	}

	public void setZhiChengZhengShu1(String zhiChengZhengShu1) {
		this.zhiChengZhengShu1 = zhiChengZhengShu1;
	}

	public String getZhiChengZhengShu2() {
		return zhiChengZhengShu2;
	}

	public void setZhiChengZhengShu2(String zhiChengZhengShu2) {
		this.zhiChengZhengShu2 = zhiChengZhengShu2;
	}

	public String getZhiChengZhengShu3() {
		return zhiChengZhengShu3;
	}

	public void setZhiChengZhengShu3(String zhiChengZhengShu3) {
		this.zhiChengZhengShu3 = zhiChengZhengShu3;
	}

	

	public String getBanGongDianHua() {
		return banGongDianHua;
	}

	public void setBanGongDianHua(String banGongDianHua) {
		this.banGongDianHua = banGongDianHua;
	}

	
}
