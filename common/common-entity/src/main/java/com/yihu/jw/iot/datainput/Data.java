package com.yihu.jw.iot.datainput;
/**
 * 具体各项体征指标数据值
 * @author lith on 2018/01/17.
 */
public class Data {
    private String rid; // 存到hbase中的id
    private int del = 1; //删除标记,1代表正常，0代表删除
    private String measure_time;     //测量时间  yyyy-MM-dd HH:mm:ss
    private String systolic;        //收缩压
    private String systolic_unit;   //收缩压测量单位
    private String diastolic;       //舒张压
    private String pulse;            //脉搏
    private String pulse_unit;
    private String blood_sugar;     //血糖
    private String blood_sugar_unit;
    private String blood_sugar_result;
    private String blood_oxygen; //血氧
    private String blood_oxygen_unit;
    private String bbt; //体温
    private String bbt_unit;
    private String ket; //酮体
    private String ket_unit;
    private String uro; //尿胆原
    private String uro_unit;
    private String uric_acid;   //尿酸
    private String uric_acid_unit;
    private String tchol;   //总胆固醇
    private String tchol_unit;
    private String hdl; //高密度脂蛋白
    private String hdl_unit;
    private String tg;  //甘油三酯
    private String tg_unit;
    private String ldl; //低密度脂蛋白
    private String ldl_unit;
    private String height;  //身高
    private String height_unit;
    private String weight;  //体重
    private String weight_unit;
    private String waist;   //腰围
    private String waist_unit;
    private String bmi; //BMI
    private String bmi_unit;
    private String hgb; //血红蛋白
    private String hgb_unit;
    private String hbalc;   //糖化血红蛋白
    private String hbalc_unit;
    private String left_eye;//  左眼
    private String left_eye_unit;
    private String right_eye;// 左眼
    private String right_eye_unit;
    private String ecg; //心电
    private String ecg_attach;
    private String ph;  //PH值
    private String nit; //亚硝酸盐
    private String nit_unit;
    private String pro; //蛋白质
    private String pro_unit;
    private String glu; //葡萄糖
    private String glu_unit;
    private String bil; //胆红素
    private String bil_unit;
    private String sg;  //比重
    private String sg_unit;
    private String wbc; //白细胞
    private String wbc_unit;
    private String vc;      //维生素C
    private String vc_unit;
    private String bld; //潜血
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

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getSystolic_unit() {
        return systolic_unit;
    }

    public void setSystolic_unit(String systolic_unit) {
        this.systolic_unit = systolic_unit;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getPulse_unit() {
        return pulse_unit;
    }

    public void setPulse_unit(String pulse_unit) {
        this.pulse_unit = pulse_unit;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getBlood_sugar_unit() {
        return blood_sugar_unit;
    }

    public void setBlood_sugar_unit(String blood_sugar_unit) {
        this.blood_sugar_unit = blood_sugar_unit;
    }

    public String getBlood_sugar_result() {
        return blood_sugar_result;
    }

    public void setBlood_sugar_result(String blood_sugar_result) {
        this.blood_sugar_result = blood_sugar_result;
    }

    public String getBlood_oxygen() {
        return blood_oxygen;
    }

    public void setBlood_oxygen(String blood_oxygen) {
        this.blood_oxygen = blood_oxygen;
    }

    public String getBlood_oxygen_unit() {
        return blood_oxygen_unit;
    }

    public void setBlood_oxygen_unit(String blood_oxygen_unit) {
        this.blood_oxygen_unit = blood_oxygen_unit;
    }

    public String getBbt() {
        return bbt;
    }

    public void setBbt(String bbt) {
        this.bbt = bbt;
    }

    public String getBbt_unit() {
        return bbt_unit;
    }

    public void setBbt_unit(String bbt_unit) {
        this.bbt_unit = bbt_unit;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getKet_unit() {
        return ket_unit;
    }

    public void setKet_unit(String ket_unit) {
        this.ket_unit = ket_unit;
    }

    public String getUro() {
        return uro;
    }

    public void setUro(String uro) {
        this.uro = uro;
    }

    public String getUro_unit() {
        return uro_unit;
    }

    public void setUro_unit(String uro_unit) {
        this.uro_unit = uro_unit;
    }

    public String getUric_acid() {
        return uric_acid;
    }

    public void setUric_acid(String uric_acid) {
        this.uric_acid = uric_acid;
    }

    public String getUric_acid_unit() {
        return uric_acid_unit;
    }

    public void setUric_acid_unit(String uric_acid_unit) {
        this.uric_acid_unit = uric_acid_unit;
    }

    public String getTchol() {
        return tchol;
    }

    public void setTchol(String tchol) {
        this.tchol = tchol;
    }

    public String getTchol_unit() {
        return tchol_unit;
    }

    public void setTchol_unit(String tchol_unit) {
        this.tchol_unit = tchol_unit;
    }

    public String getHdl() {
        return hdl;
    }

    public void setHdl(String hdl) {
        this.hdl = hdl;
    }

    public String getHdl_unit() {
        return hdl_unit;
    }

    public void setHdl_unit(String hdl_unit) {
        this.hdl_unit = hdl_unit;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getTg_unit() {
        return tg_unit;
    }

    public void setTg_unit(String tg_unit) {
        this.tg_unit = tg_unit;
    }

    public String getLdl() {
        return ldl;
    }

    public void setLdl(String ldl) {
        this.ldl = ldl;
    }

    public String getLdl_unit() {
        return ldl_unit;
    }

    public void setLdl_unit(String ldl_unit) {
        this.ldl_unit = ldl_unit;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(String height_unit) {
        this.height_unit = height_unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getWaist_unit() {
        return waist_unit;
    }

    public void setWaist_unit(String waist_unit) {
        this.waist_unit = waist_unit;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBmi_unit() {
        return bmi_unit;
    }

    public void setBmi_unit(String bmi_unit) {
        this.bmi_unit = bmi_unit;
    }

    public String getHgb() {
        return hgb;
    }

    public void setHgb(String hgb) {
        this.hgb = hgb;
    }

    public String getHgb_unit() {
        return hgb_unit;
    }

    public void setHgb_unit(String hgb_unit) {
        this.hgb_unit = hgb_unit;
    }

    public String getHbalc() {
        return hbalc;
    }

    public void setHbalc(String hbalc) {
        this.hbalc = hbalc;
    }

    public String getHbalc_unit() {
        return hbalc_unit;
    }

    public void setHbalc_unit(String hbalc_unit) {
        this.hbalc_unit = hbalc_unit;
    }

    public String getLeft_eye() {
        return left_eye;
    }

    public void setLeft_eye(String left_eye) {
        this.left_eye = left_eye;
    }

    public String getLeft_eye_unit() {
        return left_eye_unit;
    }

    public void setLeft_eye_unit(String left_eye_unit) {
        this.left_eye_unit = left_eye_unit;
    }

    public String getRight_eye() {
        return right_eye;
    }

    public void setRight_eye(String right_eye) {
        this.right_eye = right_eye;
    }

    public String getRight_eye_unit() {
        return right_eye_unit;
    }

    public void setRight_eye_unit(String right_eye_unit) {
        this.right_eye_unit = right_eye_unit;
    }

    public String getEcg() {
        return ecg;
    }

    public void setEcg(String ecg) {
        this.ecg = ecg;
    }

    public String getEcg_attach() {
        return ecg_attach;
    }

    public void setEcg_attach(String ecg_attach) {
        this.ecg_attach = ecg_attach;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNit_unit() {
        return nit_unit;
    }

    public void setNit_unit(String nit_unit) {
        this.nit_unit = nit_unit;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getPro_unit() {
        return pro_unit;
    }

    public void setPro_unit(String pro_unit) {
        this.pro_unit = pro_unit;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getGlu_unit() {
        return glu_unit;
    }

    public void setGlu_unit(String glu_unit) {
        this.glu_unit = glu_unit;
    }

    public String getBil() {
        return bil;
    }

    public void setBil(String bil) {
        this.bil = bil;
    }

    public String getBil_unit() {
        return bil_unit;
    }

    public void setBil_unit(String bil_unit) {
        this.bil_unit = bil_unit;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getSg_unit() {
        return sg_unit;
    }

    public void setSg_unit(String sg_unit) {
        this.sg_unit = sg_unit;
    }

    public String getWbc() {
        return wbc;
    }

    public void setWbc(String wbc) {
        this.wbc = wbc;
    }

    public String getWbc_unit() {
        return wbc_unit;
    }

    public void setWbc_unit(String wbc_unit) {
        this.wbc_unit = wbc_unit;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getVc_unit() {
        return vc_unit;
    }

    public void setVc_unit(String vc_unit) {
        this.vc_unit = vc_unit;
    }

    public String getBld() {
        return bld;
    }

    public void setBld(String bld) {
        this.bld = bld;
    }

    public String getBld_unit() {
        return bld_unit;
    }

    public void setBld_unit(String bld_unit) {
        this.bld_unit = bld_unit;
    }
}
