package app.com.imeitransaction;

public class TransactionDetails {

    String name=null;
    String mobile=null;
    String dob=null;

    String idProof=null;

    String cardno=null;
    String cvv=null;
    String expiredate=null;
    String upi=null;
    String amount=null;
    String remark=null;
    String otp=null;


    public TransactionDetails(String name, String mobile, String dob, String idProof, String cardno, String cvv, String expiredate, String upi, String amount, String remark, String otp) {
        this.name = name;
        this.mobile = mobile;
        this.dob = dob;
        this.idProof = idProof;
        this.cardno = cardno;
        this.cvv = cvv;
        this.expiredate = expiredate;
        this.upi = upi;
        this.amount = amount;
        this.remark = remark;
        this.otp = otp;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }












}
