package com.skill.India.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Alkesh srivastav on 7/13/2017.
 */
@Component
@ConfigurationProperties(prefix = "pdfData", locations = "classpath:sql/pdfData.yml")
public class PdfDataConfigSql {
    private String selectSql;
    private String selectSqlInstitutionGrant;
    private String selectSqlCenterLevel;
    private String selectSqlInstitutionRecognition;
    private String selectSqlPriorExperience;
    private String selectSqlDirector;

    private String selectSqlTrainingPartnerRegistrationId;

    public String getSelectSqlTrainingPartnerRegistrationId() {
        return selectSqlTrainingPartnerRegistrationId;
    }

    public void setSelectSqlTrainingPartnerRegistrationId(String selectSqlTrainingPartnerRegistrationId) {
        this.selectSqlTrainingPartnerRegistrationId = selectSqlTrainingPartnerRegistrationId;
    }

    public String getSelectSqlDirector() {
        return selectSqlDirector;
    }

    public void setSelectSqlDirector(String selectSqlDirector) {
        this.selectSqlDirector = selectSqlDirector;
    }

    public String getSelectSqlPriorExperience() {
        return selectSqlPriorExperience;
    }

    public void setSelectSqlPriorExperience(String selectSqlPriorExperience) {
        this.selectSqlPriorExperience = selectSqlPriorExperience;
    }

    public String getSelectSqlInstitutionRecognition() {
        return selectSqlInstitutionRecognition;
    }

    public void setSelectSqlInstitutionRecognition(String selectSqlInstitutionRecognition) {
        this.selectSqlInstitutionRecognition = selectSqlInstitutionRecognition;
    }

    public String getSelectSqlCenterLevel() {
        return selectSqlCenterLevel;
    }

    public void setSelectSqlCenterLevel(String selectSqlCenterLevel) {
        this.selectSqlCenterLevel = selectSqlCenterLevel;
    }

    public String getSelectSqlInstitutionGrant() {
        return selectSqlInstitutionGrant;
    }

    public void setSelectSqlInstitutionGrant(String selectSqlInstitutionGrant) {
        this.selectSqlInstitutionGrant = selectSqlInstitutionGrant;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }
}
