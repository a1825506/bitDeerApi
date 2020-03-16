package com.mengfei.admApijava.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 矿机型号实体类
 */
@Entity
public class Miner {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMinerUuid() {
        return minerUuid;
    }

    public void setMinerUuid(String minerUuid) {
        this.minerUuid = minerUuid;
    }

    public String getCoinUuid() {
        return coinUuid;
    }

    public void setCoinUuid(String coinUuid) {
        this.coinUuid = coinUuid;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public String getMinerModel() {
        return minerModel;
    }

    public void setMinerModel(String minerModel) {
        this.minerModel = minerModel;
    }

    public String getComputingPower() {
        return computingPower;
    }

    public void setComputingPower(String computingPower) {
        this.computingPower = computingPower;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getEnergyEfficiencyRatio() {
        return energyEfficiencyRatio;
    }

    public void setEnergyEfficiencyRatio(String energyEfficiencyRatio) {
        this.energyEfficiencyRatio = energyEfficiencyRatio;
    }

    public String getTheoreticalReturn() {
        return theoreticalReturn;
    }

    public void setTheoreticalReturn(String theoreticalReturn) {
        this.theoreticalReturn = theoreticalReturn;
    }




    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(value = "", name = "minerUuid", example = "矿机唯一标识,系统自动生成", required = false)
    private String minerUuid;

    @ApiModelProperty(value = "", name = "coinUuid", example = "所属数字货币一标识,系统自动生成", required = false)
    private String coinUuid;//所属数字货币

    @ApiModelProperty(value = "", name = "generation", example = "矿机代数", required = false)
    private Integer generation;//矿机代数

    @ApiModelProperty(value = "", name = "minerModel", example = "矿机型号", required = false)
    private String minerModel;//矿机型号

    @ApiModelProperty(value = "", name = "computingPower", example = "矿机算力", required = false)
    private String computingPower;//矿机算力

    @ApiModelProperty(value = "", name = "power", example = "功率", required = false)
    private String power;//功率

    @ApiModelProperty(value = "", name = "energyEfficiencyRatio", example = "能效比", required = false)
    private String energyEfficiencyRatio;//能效比


    @ApiModelProperty(value = "", name = "theoreticalReturn", example = "理论收益", required = false)
    private String theoreticalReturn;

    @ApiModelProperty(value = "", name = "minimumHashrate", example = "最低算力费", required = false)
    private String minimumHashrate;


    @Override
    public String toString() {
        return "Miner{" +
                "id=" + id +
                ", minerUuid='" + minerUuid + '\'' +
                ", coinUuid='" + coinUuid + '\'' +
                ", generation=" + generation +
                ", minerModel='" + minerModel + '\'' +
                ", computingPower='" + computingPower + '\'' +
                ", power='" + power + '\'' +
                ", energyEfficiencyRatio='" + energyEfficiencyRatio + '\'' +
                ", theoreticalReturn='" + theoreticalReturn + '\'' +
                '}';
    }
}
