package iot.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.searchbox.annotations.JestId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "居民标签信息", description = "居民标签信息")
public class FigureLabelDataModelVO {

    //es ID
    @JestId
    private String id;

    @ApiModelProperty("身份证")
    private String idcard;

    @ApiModelProperty("标签类型")

    private String labelType;
    @ApiModelProperty("标签code")
    private String labelCode;

    @ApiModelProperty("标签名称")
    private String labeName;

    @ApiModelProperty("标签值，仅当标签只有一个分类的时候才有此值，比如生日，体重等")
    private String labelValue;

    @ApiModelProperty("创建时间")
    private String createTime;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLabelType() {
        return labelType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabeName() {
        return labeName;
    }

    public void setLabeName(String labeName) {
        this.labeName = labeName;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
