
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BAPI_MRP_STOCK_DETAIL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BAPI_MRP_STOCK_DETAIL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="STATISTIC_CREATED" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="UNRESTRICTED_STCK" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="QUAL_INSPECTION" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="BLKD_STKC" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="RESTR_USE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="BLKD_RETURNS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="BLKD_GR" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="RESERVATIONS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="UNRES_CONSI" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="CONSI_IN_QUAL_INSP" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="RESTR_CONSI" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="BLKD_CONSI" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="VAL_STOCK" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="QUAL_INSP_VENDOR" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="SALES_REQS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PUR_ORDERS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="CONSIG_ORD" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PUR_REQ" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PLND_ORDER" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PROD_ORDER" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="REC_RESER" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STK_TRNF_RES" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STK_TRNF_REL" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STK_TRNF_PLAN" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="FORECAST_REQ" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="REQUESTS_QUOT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="QUOTATIONS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="ORDERS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="SCHED_AGRMT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="CONTRACTS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="FOC_DELIVERY" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="DELIVERY" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="DEP_REQMTS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="DENOMINATR" type="{urn:sap-com:document:sap:rfc:functions}decimal5.0"/>
 *         &lt;element name="NUMERATOR" type="{urn:sap-com:document:sap:rfc:functions}decimal5.0"/>
 *         &lt;element name="REC_DEPREQ" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="IND_REQMTS" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="TOTAL_STCK" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="FIXED_RECPT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PL_RECEIPT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="FIXED_ISSUES" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="PL_ISSUES" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STCK_IN_TFR" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STCK_IN_TRANSIT" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="STOCKINTFR" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BAPI_MRP_STOCK_DETAIL", propOrder = {
    "statisticcreated",
    "unrestrictedstck",
    "qualinspection",
    "blkdstkc",
    "restruse",
    "blkdreturns",
    "blkdgr",
    "reservations",
    "unresconsi",
    "consiinqualinsp",
    "restrconsi",
    "blkdconsi",
    "valstock",
    "qualinspvendor",
    "salesreqs",
    "purorders",
    "consigord",
    "purreq",
    "plndorder",
    "prodorder",
    "recreser",
    "stktrnfres",
    "stktrnfrel",
    "stktrnfplan",
    "forecastreq",
    "requestsquot",
    "quotations",
    "orders",
    "schedagrmt",
    "contracts",
    "focdelivery",
    "delivery",
    "depreqmts",
    "denominatr",
    "numerator",
    "recdepreq",
    "indreqmts",
    "totalstck",
    "fixedrecpt",
    "plreceipt",
    "fixedissues",
    "plissues",
    "stckintfr",
    "stckintransit",
    "stockintfr"
})
public class BAPIMRPSTOCKDETAIL {

    @XmlElement(name = "STATISTIC_CREATED", required = true)
    protected String statisticcreated;
    @XmlElement(name = "UNRESTRICTED_STCK", required = true)
    protected BigDecimal unrestrictedstck;
    @XmlElement(name = "QUAL_INSPECTION", required = true)
    protected BigDecimal qualinspection;
    @XmlElement(name = "BLKD_STKC", required = true)
    protected BigDecimal blkdstkc;
    @XmlElement(name = "RESTR_USE", required = true)
    protected BigDecimal restruse;
    @XmlElement(name = "BLKD_RETURNS", required = true)
    protected BigDecimal blkdreturns;
    @XmlElement(name = "BLKD_GR", required = true)
    protected BigDecimal blkdgr;
    @XmlElement(name = "RESERVATIONS", required = true)
    protected BigDecimal reservations;
    @XmlElement(name = "UNRES_CONSI", required = true)
    protected BigDecimal unresconsi;
    @XmlElement(name = "CONSI_IN_QUAL_INSP", required = true)
    protected BigDecimal consiinqualinsp;
    @XmlElement(name = "RESTR_CONSI", required = true)
    protected BigDecimal restrconsi;
    @XmlElement(name = "BLKD_CONSI", required = true)
    protected BigDecimal blkdconsi;
    @XmlElement(name = "VAL_STOCK", required = true)
    protected BigDecimal valstock;
    @XmlElement(name = "QUAL_INSP_VENDOR", required = true)
    protected BigDecimal qualinspvendor;
    @XmlElement(name = "SALES_REQS", required = true)
    protected BigDecimal salesreqs;
    @XmlElement(name = "PUR_ORDERS", required = true)
    protected BigDecimal purorders;
    @XmlElement(name = "CONSIG_ORD", required = true)
    protected BigDecimal consigord;
    @XmlElement(name = "PUR_REQ", required = true)
    protected BigDecimal purreq;
    @XmlElement(name = "PLND_ORDER", required = true)
    protected BigDecimal plndorder;
    @XmlElement(name = "PROD_ORDER", required = true)
    protected BigDecimal prodorder;
    @XmlElement(name = "REC_RESER", required = true)
    protected BigDecimal recreser;
    @XmlElement(name = "STK_TRNF_RES", required = true)
    protected BigDecimal stktrnfres;
    @XmlElement(name = "STK_TRNF_REL", required = true)
    protected BigDecimal stktrnfrel;
    @XmlElement(name = "STK_TRNF_PLAN", required = true)
    protected BigDecimal stktrnfplan;
    @XmlElement(name = "FORECAST_REQ", required = true)
    protected BigDecimal forecastreq;
    @XmlElement(name = "REQUESTS_QUOT", required = true)
    protected BigDecimal requestsquot;
    @XmlElement(name = "QUOTATIONS", required = true)
    protected BigDecimal quotations;
    @XmlElement(name = "ORDERS", required = true)
    protected BigDecimal orders;
    @XmlElement(name = "SCHED_AGRMT", required = true)
    protected BigDecimal schedagrmt;
    @XmlElement(name = "CONTRACTS", required = true)
    protected BigDecimal contracts;
    @XmlElement(name = "FOC_DELIVERY", required = true)
    protected BigDecimal focdelivery;
    @XmlElement(name = "DELIVERY", required = true)
    protected BigDecimal delivery;
    @XmlElement(name = "DEP_REQMTS", required = true)
    protected BigDecimal depreqmts;
    @XmlElement(name = "DENOMINATR", required = true)
    protected BigDecimal denominatr;
    @XmlElement(name = "NUMERATOR", required = true)
    protected BigDecimal numerator;
    @XmlElement(name = "REC_DEPREQ", required = true)
    protected BigDecimal recdepreq;
    @XmlElement(name = "IND_REQMTS", required = true)
    protected BigDecimal indreqmts;
    @XmlElement(name = "TOTAL_STCK", required = true)
    protected BigDecimal totalstck;
    @XmlElement(name = "FIXED_RECPT", required = true)
    protected BigDecimal fixedrecpt;
    @XmlElement(name = "PL_RECEIPT", required = true)
    protected BigDecimal plreceipt;
    @XmlElement(name = "FIXED_ISSUES", required = true)
    protected BigDecimal fixedissues;
    @XmlElement(name = "PL_ISSUES", required = true)
    protected BigDecimal plissues;
    @XmlElement(name = "STCK_IN_TFR", required = true)
    protected BigDecimal stckintfr;
    @XmlElement(name = "STCK_IN_TRANSIT", required = true)
    protected BigDecimal stckintransit;
    @XmlElement(name = "STOCKINTFR", required = true)
    protected BigDecimal stockintfr;

    /**
     * Gets the value of the statisticcreated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATISTICCREATED() {
        return statisticcreated;
    }

    /**
     * Sets the value of the statisticcreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATISTICCREATED(String value) {
        this.statisticcreated = value;
    }

    /**
     * Gets the value of the unrestrictedstck property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUNRESTRICTEDSTCK() {
        return unrestrictedstck;
    }

    /**
     * Sets the value of the unrestrictedstck property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUNRESTRICTEDSTCK(BigDecimal value) {
        this.unrestrictedstck = value;
    }

    /**
     * Gets the value of the qualinspection property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQUALINSPECTION() {
        return qualinspection;
    }

    /**
     * Sets the value of the qualinspection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQUALINSPECTION(BigDecimal value) {
        this.qualinspection = value;
    }

    /**
     * Gets the value of the blkdstkc property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBLKDSTKC() {
        return blkdstkc;
    }

    /**
     * Sets the value of the blkdstkc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBLKDSTKC(BigDecimal value) {
        this.blkdstkc = value;
    }

    /**
     * Gets the value of the restruse property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRESTRUSE() {
        return restruse;
    }

    /**
     * Sets the value of the restruse property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRESTRUSE(BigDecimal value) {
        this.restruse = value;
    }

    /**
     * Gets the value of the blkdreturns property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBLKDRETURNS() {
        return blkdreturns;
    }

    /**
     * Sets the value of the blkdreturns property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBLKDRETURNS(BigDecimal value) {
        this.blkdreturns = value;
    }

    /**
     * Gets the value of the blkdgr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBLKDGR() {
        return blkdgr;
    }

    /**
     * Sets the value of the blkdgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBLKDGR(BigDecimal value) {
        this.blkdgr = value;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRESERVATIONS() {
        return reservations;
    }

    /**
     * Sets the value of the reservations property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRESERVATIONS(BigDecimal value) {
        this.reservations = value;
    }

    /**
     * Gets the value of the unresconsi property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUNRESCONSI() {
        return unresconsi;
    }

    /**
     * Sets the value of the unresconsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUNRESCONSI(BigDecimal value) {
        this.unresconsi = value;
    }

    /**
     * Gets the value of the consiinqualinsp property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCONSIINQUALINSP() {
        return consiinqualinsp;
    }

    /**
     * Sets the value of the consiinqualinsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCONSIINQUALINSP(BigDecimal value) {
        this.consiinqualinsp = value;
    }

    /**
     * Gets the value of the restrconsi property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRESTRCONSI() {
        return restrconsi;
    }

    /**
     * Sets the value of the restrconsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRESTRCONSI(BigDecimal value) {
        this.restrconsi = value;
    }

    /**
     * Gets the value of the blkdconsi property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBLKDCONSI() {
        return blkdconsi;
    }

    /**
     * Sets the value of the blkdconsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBLKDCONSI(BigDecimal value) {
        this.blkdconsi = value;
    }

    /**
     * Gets the value of the valstock property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVALSTOCK() {
        return valstock;
    }

    /**
     * Sets the value of the valstock property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVALSTOCK(BigDecimal value) {
        this.valstock = value;
    }

    /**
     * Gets the value of the qualinspvendor property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQUALINSPVENDOR() {
        return qualinspvendor;
    }

    /**
     * Sets the value of the qualinspvendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQUALINSPVENDOR(BigDecimal value) {
        this.qualinspvendor = value;
    }

    /**
     * Gets the value of the salesreqs property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSALESREQS() {
        return salesreqs;
    }

    /**
     * Sets the value of the salesreqs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSALESREQS(BigDecimal value) {
        this.salesreqs = value;
    }

    /**
     * Gets the value of the purorders property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPURORDERS() {
        return purorders;
    }

    /**
     * Sets the value of the purorders property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPURORDERS(BigDecimal value) {
        this.purorders = value;
    }

    /**
     * Gets the value of the consigord property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCONSIGORD() {
        return consigord;
    }

    /**
     * Sets the value of the consigord property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCONSIGORD(BigDecimal value) {
        this.consigord = value;
    }

    /**
     * Gets the value of the purreq property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPURREQ() {
        return purreq;
    }

    /**
     * Sets the value of the purreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPURREQ(BigDecimal value) {
        this.purreq = value;
    }

    /**
     * Gets the value of the plndorder property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPLNDORDER() {
        return plndorder;
    }

    /**
     * Sets the value of the plndorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPLNDORDER(BigDecimal value) {
        this.plndorder = value;
    }

    /**
     * Gets the value of the prodorder property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPRODORDER() {
        return prodorder;
    }

    /**
     * Sets the value of the prodorder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPRODORDER(BigDecimal value) {
        this.prodorder = value;
    }

    /**
     * Gets the value of the recreser property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRECRESER() {
        return recreser;
    }

    /**
     * Sets the value of the recreser property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRECRESER(BigDecimal value) {
        this.recreser = value;
    }

    /**
     * Gets the value of the stktrnfres property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTKTRNFRES() {
        return stktrnfres;
    }

    /**
     * Sets the value of the stktrnfres property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTKTRNFRES(BigDecimal value) {
        this.stktrnfres = value;
    }

    /**
     * Gets the value of the stktrnfrel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTKTRNFREL() {
        return stktrnfrel;
    }

    /**
     * Sets the value of the stktrnfrel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTKTRNFREL(BigDecimal value) {
        this.stktrnfrel = value;
    }

    /**
     * Gets the value of the stktrnfplan property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTKTRNFPLAN() {
        return stktrnfplan;
    }

    /**
     * Sets the value of the stktrnfplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTKTRNFPLAN(BigDecimal value) {
        this.stktrnfplan = value;
    }

    /**
     * Gets the value of the forecastreq property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFORECASTREQ() {
        return forecastreq;
    }

    /**
     * Sets the value of the forecastreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFORECASTREQ(BigDecimal value) {
        this.forecastreq = value;
    }

    /**
     * Gets the value of the requestsquot property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getREQUESTSQUOT() {
        return requestsquot;
    }

    /**
     * Sets the value of the requestsquot property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setREQUESTSQUOT(BigDecimal value) {
        this.requestsquot = value;
    }

    /**
     * Gets the value of the quotations property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQUOTATIONS() {
        return quotations;
    }

    /**
     * Sets the value of the quotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQUOTATIONS(BigDecimal value) {
        this.quotations = value;
    }

    /**
     * Gets the value of the orders property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getORDERS() {
        return orders;
    }

    /**
     * Sets the value of the orders property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setORDERS(BigDecimal value) {
        this.orders = value;
    }

    /**
     * Gets the value of the schedagrmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSCHEDAGRMT() {
        return schedagrmt;
    }

    /**
     * Sets the value of the schedagrmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSCHEDAGRMT(BigDecimal value) {
        this.schedagrmt = value;
    }

    /**
     * Gets the value of the contracts property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCONTRACTS() {
        return contracts;
    }

    /**
     * Sets the value of the contracts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCONTRACTS(BigDecimal value) {
        this.contracts = value;
    }

    /**
     * Gets the value of the focdelivery property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFOCDELIVERY() {
        return focdelivery;
    }

    /**
     * Sets the value of the focdelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFOCDELIVERY(BigDecimal value) {
        this.focdelivery = value;
    }

    /**
     * Gets the value of the delivery property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDELIVERY() {
        return delivery;
    }

    /**
     * Sets the value of the delivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDELIVERY(BigDecimal value) {
        this.delivery = value;
    }

    /**
     * Gets the value of the depreqmts property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDEPREQMTS() {
        return depreqmts;
    }

    /**
     * Sets the value of the depreqmts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDEPREQMTS(BigDecimal value) {
        this.depreqmts = value;
    }

    /**
     * Gets the value of the denominatr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDENOMINATR() {
        return denominatr;
    }

    /**
     * Sets the value of the denominatr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDENOMINATR(BigDecimal value) {
        this.denominatr = value;
    }

    /**
     * Gets the value of the numerator property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNUMERATOR() {
        return numerator;
    }

    /**
     * Sets the value of the numerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNUMERATOR(BigDecimal value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the recdepreq property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRECDEPREQ() {
        return recdepreq;
    }

    /**
     * Sets the value of the recdepreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRECDEPREQ(BigDecimal value) {
        this.recdepreq = value;
    }

    /**
     * Gets the value of the indreqmts property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getINDREQMTS() {
        return indreqmts;
    }

    /**
     * Sets the value of the indreqmts property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setINDREQMTS(BigDecimal value) {
        this.indreqmts = value;
    }

    /**
     * Gets the value of the totalstck property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALSTCK() {
        return totalstck;
    }

    /**
     * Sets the value of the totalstck property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALSTCK(BigDecimal value) {
        this.totalstck = value;
    }

    /**
     * Gets the value of the fixedrecpt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFIXEDRECPT() {
        return fixedrecpt;
    }

    /**
     * Sets the value of the fixedrecpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFIXEDRECPT(BigDecimal value) {
        this.fixedrecpt = value;
    }

    /**
     * Gets the value of the plreceipt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPLRECEIPT() {
        return plreceipt;
    }

    /**
     * Sets the value of the plreceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPLRECEIPT(BigDecimal value) {
        this.plreceipt = value;
    }

    /**
     * Gets the value of the fixedissues property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFIXEDISSUES() {
        return fixedissues;
    }

    /**
     * Sets the value of the fixedissues property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFIXEDISSUES(BigDecimal value) {
        this.fixedissues = value;
    }

    /**
     * Gets the value of the plissues property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPLISSUES() {
        return plissues;
    }

    /**
     * Sets the value of the plissues property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPLISSUES(BigDecimal value) {
        this.plissues = value;
    }

    /**
     * Gets the value of the stckintfr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTCKINTFR() {
        return stckintfr;
    }

    /**
     * Sets the value of the stckintfr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTCKINTFR(BigDecimal value) {
        this.stckintfr = value;
    }

    /**
     * Gets the value of the stckintransit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTCKINTRANSIT() {
        return stckintransit;
    }

    /**
     * Sets the value of the stckintransit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTCKINTRANSIT(BigDecimal value) {
        this.stckintransit = value;
    }

    /**
     * Gets the value of the stockintfr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTOCKINTFR() {
        return stockintfr;
    }

    /**
     * Sets the value of the stockintfr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTOCKINTFR(BigDecimal value) {
        this.stockintfr = value;
    }

}
