package com.smile.clz.api.core.exception;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * @author hasithagamage
 * @date 10/28/17
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Error",
        propOrder = {}
)
@XmlRootElement
public class Error implements Serializable {
    @XmlElement(
            required = true
    )
    private String code;
    @XmlElement(
            required = true
    )
    private String description;
    private String additionalInfo;

    public Error() {
    }

    public Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(String value) {
        this.additionalInfo = value;
    }

    public String toString() {
        return "Error{" + "code='" + this.code + '\'' + ", description='" + this.description + '\'' + ", additionalInfo='" + this.additionalInfo + '\'' + '}';
    }
}