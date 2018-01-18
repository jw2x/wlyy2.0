package com.yihu.jw.iot.datainput;
/**
 * 具体各项体征指标数据值
 * @author lith on 2018/01/17.
 */
public class Data {
    private String rid; // 存到hbase中的id
    private int del = 1; //删除标记,1代表正常，0代表删除
    private String measure_time;     //测量时间  yyyy-MM-dd HH:mm:ss
    private double systolic;        //收缩压
    private String systolic_unit;   //收缩压测量单位
    private double diastolic;       //舒张压
    private double pulse;            //脉搏
    private String pulse_unit;
    private double blood_sugar;     //血糖
    private String blood_sugar_unit;
    private double blood_sugar_result;
    private double blood_oxygen; //血氧
    private String blood_oxygen_unit;
    private double bbt; //体温
    private String bbt_unit;
    private double ket; //酮体
    private String ket_unit;
    private double uro; //尿胆原
    private String uro_unit;
    private double uric_acid;   //尿酸
    private String uric_acid_unit;
    private double tchol;   //总胆固醇
    private String tchol_unit;
    private double hdl; //高密度脂蛋白
    private String hdl_unit;
    private double tg;  //甘油三酯
    private String tg_unit;
    private double ldl; //低密度脂蛋白
    private String ldl_unit;
    private double height;  //身高
    private String height_unit;
    private double weight;  //体重
    private String weight_unit;
    private double waist;   //腰围
    private String waist_unit;
    private double bmi; //BMI
    private String bmi_unit;
    private double hgb; //血红蛋白
    private String hgb_unit;
    private double hbalc;   //糖化血红蛋白
    private String hbalc_unit;
    private double left_eye;//  左眼
    private String left_eye_unit;
    private double right_eye;// 左眼
    private String right_eye_unit;
    private double ecg; //心电
    private String ecg_attach;
    private double ph;  //PH值
    private double nit; //亚硝酸盐
    private String nit_unit;
    private double pro; //蛋白质
    private String pro_unit;
    private double glu; //葡萄糖
    private String glu_unit;
    private double bil; //胆红素
    private String bil_unit;
    private double sg;  //比重
    private String sg_unit;
    private double wbc; //白细胞
    private String wbc_unit;
    private double vc;      //维生素C
    private String vc_unit;
    private double bld; //潜血
    private String bld_unit;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getMeasure_time() {
        return measure_time;
    }

    public void setMeasure_time(String measure_time) {
        this.measure_time = measure_time;
    }

    public double getSystolic() {
        return systolic;
    }

    public void setSystolic(double systolic) {
        this.systolic = systolic;
    }

    public String getSystolic_unit() {
        return systolic_unit;
    }

    public void setSystolic_unit(String systolic_unit) {
        this.systolic_unit = systolic_unit;
    }

    public double getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(double diastolic) {
        this.diastolic = diastolic;
    }

    public double getPulse() {
        return pulse;
    }

    public void setPulse(double pulse) {
        this.pulse = pulse;
    }

    public String getPulse_unit() {
        return pulse_unit;
    }

    public void setPulse_unit(String pulse_unit) {
        this.pulse_unit = pulse_unit;
    }

    public double getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(double blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getBlood_sugar_unit() {
        return blood_sugar_unit;
    }

    public void setBlood_sugar_unit(String blood_sugar_unit) {
        this.blood_sugar_unit = blood_sugar_unit;
    }

    public double getBlood_sugar_result() {
        return blood_sugar_result;
    }

    public void setBlood_sugar_result(double blood_sugar_result) {
        this.blood_sugar_result = blood_sugar_result;
    }

    public double getBlood_oxygen() {
        return blood_oxygen;
    }

    public void setBlood_oxygen(double blood_oxygen) {
        this.blood_oxygen = blood_oxygen;
    }

    public String getBlood_oxygen_unit() {
        return blood_oxygen_unit;
    }

    public void setBlood_oxygen_unit(String blood_oxygen_unit) {
        this.blood_oxygen_unit = blood_oxygen_unit;
    }

    public double getBbt() {
        return bbt;
    }

    public void setBbt(double bbt) {
        this.bbt = bbt;
    }

    public String getBbt_unit() {
        return bbt_unit;
    }

    public void setBbt_unit(String bbt_unit) {
        this.bbt_unit = bbt_unit;
    }

    public double getKet() {
        return ket;
    }

    public void setKet(double ket) {
        this.ket = ket;
    }

    public String getKet_unit() {
        return ket_unit;
    }

    public void setKet_unit(String ket_unit) {
        this.ket_unit = ket_unit;
    }

    public double getUro() {
        return uro;
    }

    public void setUro(double uro) {
        this.uro = uro;
    }

    public String getUro_unit() {
        return uro_unit;
    }

    public void setUro_unit(String uro_unit) {
        this.uro_unit = uro_unit;
    }

    public double getUric_acid() {
        return uric_acid;
    }

    public void setUric_acid(double uric_acid) {
        this.uric_acid = uric_acid;
    }

    public String getUric_acid_unit() {
        return uric_acid_unit;
    }

    public void setUric_acid_unit(String uric_acid_unit) {
        this.uric_acid_unit = uric_acid_unit;
    }

    public double getTchol() {
        return tchol;
    }

    public void setTchol(double tchol) {
        this.tchol = tchol;
    }

    public String getTchol_unit() {
        return tchol_unit;
    }

    public void setTchol_unit(String tchol_unit) {
        this.tchol_unit = tchol_unit;
    }

    public double getHdl() {
        return hdl;
    }

    public void setHdl(double hdl) {
        this.hdl = hdl;
    }

    public String getHdl_unit() {
        return hdl_unit;
    }

    public void setHdl_unit(String hdl_unit) {
        this.hdl_unit = hdl_unit;
    }

    public double getTg() {
        return tg;
    }

    public void setTg(double tg) {
        this.tg = tg;
    }

    public String getTg_unit() {
        return tg_unit;
    }

    public void setTg_unit(String tg_unit) {
        this.tg_unit = tg_unit;
    }

    public double getLdl() {
        return ldl;
    }

    public void setLdl(double ldl) {
        this.ldl = ldl;
    }

    public String getLdl_unit() {
        return ldl_unit;
    }

    public void setLdl_unit(String ldl_unit) {
        this.ldl_unit = ldl_unit;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(String height_unit) {
        this.height_unit = height_unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public String getWaist_unit() {
        return waist_unit;
    }

    public void setWaist_unit(String waist_unit) {
        this.waist_unit = waist_unit;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getBmi_unit() {
        return bmi_unit;
    }

    public void setBmi_unit(String bmi_unit) {
        this.bmi_unit = bmi_unit;
    }

    public double getHgb() {
        return hgb;
    }

    public void setHgb(double hgb) {
        this.hgb = hgb;
    }

    public String getHgb_unit() {
        return hgb_unit;
    }

    public void setHgb_unit(String hgb_unit) {
        this.hgb_unit = hgb_unit;
    }

    public double getHbalc() {
        return hbalc;
    }

    public void setHbalc(double hbalc) {
        this.hbalc = hbalc;
    }

    public String getHbalc_unit() {
        return hbalc_unit;
    }

    public void setHbalc_unit(String hbalc_unit) {
        this.hbalc_unit = hbalc_unit;
    }

    public double getLeft_eye() {
        return left_eye;
    }

    public void setLeft_eye(double left_eye) {
        this.left_eye = left_eye;
    }

    public String getLeft_eye_unit() {
        return left_eye_unit;
    }

    public void setLeft_eye_unit(String left_eye_unit) {
        this.left_eye_unit = left_eye_unit;
    }

    public double getRight_eye() {
        return right_eye;
    }

    public void setRight_eye(double right_eye) {
        this.right_eye = right_eye;
    }

    public String getRight_eye_unit() {
        return right_eye_unit;
    }

    public void setRight_eye_unit(String right_eye_unit) {
        this.right_eye_unit = right_eye_unit;
    }

    public double getEcg() {
        return ecg;
    }

    public void setEcg(double ecg) {
        this.ecg = ecg;
    }

    public String getEcg_attach() {
        return ecg_attach;
    }

    public void setEcg_attach(String ecg_attach) {
        this.ecg_attach = ecg_attach;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getNit() {
        return nit;
    }

    public void setNit(double nit) {
        this.nit = nit;
    }

    public String getNit_unit() {
        return nit_unit;
    }

    public void setNit_unit(String nit_unit) {
        this.nit_unit = nit_unit;
    }

    public double getPro() {
        return pro;
    }

    public void setPro(double pro) {
        this.pro = pro;
    }

    public String getPro_unit() {
        return pro_unit;
    }

    public void setPro_unit(String pro_unit) {
        this.pro_unit = pro_unit;
    }

    public double getGlu() {
        return glu;
    }

    public void setGlu(double glu) {
        this.glu = glu;
    }

    public String getGlu_unit() {
        return glu_unit;
    }

    public void setGlu_unit(String glu_unit) {
        this.glu_unit = glu_unit;
    }

    public double getBil() {
        return bil;
    }

    public void setBil(double bil) {
        this.bil = bil;
    }

    public String getBil_unit() {
        return bil_unit;
    }

    public void setBil_unit(String bil_unit) {
        this.bil_unit = bil_unit;
    }

    public double getSg() {
        return sg;
    }

    public void setSg(double sg) {
        this.sg = sg;
    }

    public String getSg_unit() {
        return sg_unit;
    }

    public void setSg_unit(String sg_unit) {
        this.sg_unit = sg_unit;
    }

    public double getWbc() {
        return wbc;
    }

    public void setWbc(double wbc) {
        this.wbc = wbc;
    }

    public String getWbc_unit() {
        return wbc_unit;
    }

    public void setWbc_unit(String wbc_unit) {
        this.wbc_unit = wbc_unit;
    }

    public double getVc() {
        return vc;
    }

    public void setVc(double vc) {
        this.vc = vc;
    }

    public String getVc_unit() {
        return vc_unit;
    }

    public void setVc_unit(String vc_unit) {
        this.vc_unit = vc_unit;
    }

    public double getBld() {
        return bld;
    }

    public void setBld(double bld) {
        this.bld = bld;
    }

    public String getBld_unit() {
        return bld_unit;
    }

    public void setBld_unit(String bld_unit) {
        this.bld_unit = bld_unit;
    }
}
