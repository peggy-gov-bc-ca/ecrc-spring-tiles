/**
 * @(#)PaymentInformation.java
 * Copyright (c) 2006, B.C. Ministry of Attorney General.
 * All rights reserved.
 */
package ca.bc.gov.open.pssg.ecrc.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Container for payment information. This bean is filled before and after 
 * BCEP payment sequence.  
 * 
 * @author $LastChangedBy: smillar $
 * @version $Revision: 106022 $  $Date: 2014-03-31 15:09:52 -0700 (Mon, 31 Mar 2014) $
 */
public class PaymentInformation implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5450016131552438520L;

	private String payorType = Consts.ORG_TO_PAY;

    private String authorizationNumber;

    private String invoiceNumber;

    private String paymentDate;

    private String paymentStatus;

    private Integer transactionId;

    private String sessionId;

    private String bankISOResponseCode;

    private String bankResponseCode;

    private String bankResponseMessage;

    private String cardType;

    private String sequenceNumber;

    private String terminalNumber;

    private String cardNumber;
    
    private double chargeAmount = 0.00; 
    
    private String cardHolderName; 
    
    private String date;
    
    private String dateTime; 
    
    private boolean isDirty = false;

    private boolean isSavedInDatabase = false;
    
    public PaymentInformation() {
    }
    
    public String getDate() {
        if (date == null) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            date = sdf.format(new Date());
        }
        
        return date;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        isDirty = true;
        this.authorizationNumber = authorizationNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        isDirty = true;
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    
    public String getPaymentDateTime() {
    	if (dateTime == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            dateTime = sdf.format(new Date());
        }
        return dateTime;
    }

    public void setPaymentDate(String paymentDate) {
        isDirty = true;
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        isDirty = true;
        this.paymentStatus = paymentStatus;
    }
    
    public String getPaymentStatusAsString() {
    	 if (Consts.STATUS_APPROVED.equals(paymentStatus)) {
             return "Approved";
         }
    	 if (Consts.STATUS_DECLINED.equals(paymentStatus)) {
             return "Declined";
         }
         return "";
    }

    public String getPayorType() {
        return payorType;
    }

    public void setPayorType(String paymentType) {
        isDirty = true;
        this.payorType = paymentType;
    }

    public boolean isPayorOrganization() {
        return this.payorType.equals(Consts.ORG_TO_PAY);
    }
    
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        isDirty = true;
        this.transactionId = transactionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        isDirty = true;
        this.sessionId = sessionId;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setIsDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    public String getBankResponseCode() {
        return bankResponseCode;
    }

    public void setBankResponseCode(String bankResponseCode) {
        this.bankResponseCode = bankResponseCode;
    }

    public String getBankResponseMessage() {
        return bankResponseMessage;
    }

    public void setBankResponseMessage(String bankResponseMessage) {
        this.bankResponseMessage = bankResponseMessage;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardTypeAsString() {
        if ("V".equals(cardType)) {
            return "VISA";
        }
        if ("M".equals(cardType)) {
            return "Mastercard";
        }
        if ("AX".equals(cardType)) {
            return "American Express";
        }
        return "";
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getBankISOResponseCode() {
        return bankISOResponseCode;
    }

    public void setBankISOResponseCode(String bankISOResponseCode) {
        this.bankISOResponseCode = bankISOResponseCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	
    public String getChargeAmountAsString() {
    	DecimalFormat df = new DecimalFormat("0.00"); 
		return "$" + df.format(chargeAmount);
	}

	public boolean isSavedInDatabase() {
        return isSavedInDatabase;
    }

    public void setSavedInDatabase(boolean isSavedInDatabase) {
        this.isSavedInDatabase = isSavedInDatabase;
    }

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName; 
	}
	
	public String getCardHolderName() {
		return cardHolderName; 
	}

    
}
