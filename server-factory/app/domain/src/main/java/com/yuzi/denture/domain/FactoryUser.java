package com.yuzi.denture.domain;

import com.yuzi.denture.domain.exception.CodeException;
import com.yuzi.denture.domain.response.ResponseCode;
import com.yuzi.denture.domain.type.Educational;
import com.yuzi.denture.domain.util.CommonUtil;
import com.yuzi.denture.domain.util.RSAUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FactoryUser {

    private static final String PrivateKeyStr =
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC0n2obxuexYoKl4YdnCN7LvgL3\n" +
            "coAcwwcXozAl2/Zy/QZdtGMurbXZlucDmyCoFvzmVnL7k6iQdWP3pgQQ8gicjD0qTmrDDNfiPnBl\n" +
            "xAQp6We37/AoRDCDUVskWNRSnoUJ1gJWZ71FR8ejUbuw1Or0x/BVMeorsnj19DUVMkSr38NeLRqN\n" +
            "0cJF245i01St9tgAbunyeBMmdsF92l9IBxPa001spDLbtyNhwSylSfyP9Yq0TwAvB5uW67USflVA\n" +
            "Dr0LhxC+FVpEONvI5udpb/x5ZvfsD6OG/vAQOcDOyIVczVzVKZ9Fzt6gcc80AoGeHDWS3SEs0xXY\n" +
            "ti4KCSBGb9XdAgMBAAECggEADIMumpYA3uzwGAWzl5Opu3uCQRoRfeyUxTJs13KRdA3LLxqb9FPc\n" +
            "USJ/BmTssjhFdL9wX7Uu7DL1peY+Sd46cn3UjuW3x6NQIUAqESKvFygwIzHf9SokIjS0l6P/sWmB\n" +
            "1diZeNre5bm/PrUex3U4gZSINZ6rljNa5c7xDnLZBv7vSsZ+gJeuQWWbmQSJaO5g+llHyvhcFUmo\n" +
            "UTNX4Yj6c6Fo/L6mdqvLSuRH3sLZIlBG/qEf9/7tzcoV6AzdNevaoLYZ/X2aL4xp1I2dJImowhyT\n" +
            "FQPy3v+IxuE9BrgFKynQNqQU7mTFEP7ttRnfNNTujCiN0jK7WIj2SGIqmOt5EQKBgQDyuvfeBseO\n" +
            "Bjv4RfK2fc7+JW4x57uhtlCsGq0dGguzurwrxqmUP0sfm9+WCDAb+h2UxBLpnJ2+++OewNMx6rGb\n" +
            "Vc2iA/QyKg82QBLDtuquD1cOP8hmx5tuaOgZqpzz9auEf+Tm/jtdQqjHLlw7hJkvdJtbE0VYveRj\n" +
            "npPw1lMzMwKBgQC+fz6jNF1/S8+SlNzfShsczZFm1F6q3FCfQ2Su2HMkxW1Bx9T/TDlhc1qNpUsN\n" +
            "oURgdcEEPTewIbEKsgxHfXOn4XCnI0JUskNIGh1ZCBJtjZ+/LgZI/JpT9Mt0ns+MtlufBIExCQCz\n" +
            "ykaCuE8xpcvpaWp8pAK2YSyHWvecbjDSrwKBgE1mdVelTcjqEAFxPJ4YrAK6qNLdFCXS3xKiRwV6\n" +
            "orSjHRHQfY66SBhCOh+7aKvjK7+ebnFz8ZFO/RxIXJAUSKJB0BqTrDC63LLt543lLBxo49e1Ww/7\n" +
            "IoMojKpyjy3z7seHiNRnyUJJ2Uv6aorIE5jgVBHMqVZM5yElFgO8NDelAoGAGL0vpkRDrCdtQo0A\n" +
            "jiLymG/95QvJa4kHE1fOOIK1mV3mTXelKq58eTJg3SpNL+z0k05XjbRhigcmFcx/mb0qCvBVD++d\n" +
            "SqCH6lIx44EP25hb2ZkhidL8O9D7px6Et7Jq/aC5ImQX6kFnnMxQxJLSg3KEOMGX9JV0EfP3WiJZ\n" +
            "RtMCgYBBDaNl6NOHTMEZYS7H+tr2A1b4TrtUOj/ZPTLJ3lDOzOqjPzfCkPt35oylkqqm7adGi+dD\n" +
            "EN9zunETyxflqD4hySMre1WF616gyBRbwvnNgDewgcjkPnLxDsWC6wER3FBgseuoZIKus39QOlzQ\n" +
            "Z1GvkGxVTdQGx5z1qeM4ag20Ww==";
    private final PrivateKey PriKey = RSAUtil.string2PrivateKey(PrivateKeyStr);

    //id:
     Long id;
     String no;
    //factoryId
     Long factoryId;
    //name:
     String name;
    //contact:
     String contact;
     String password;
    //groupType:
     String groupType;
    //joinDate:
     Date joinDate;
    Date expiredDate;
    List<FactoryRole> roles;
    String avatar;
    Boolean marital;
    Integer age;
    String cardId;
    String address;
    Educational educational;
    String position;
    BigDecimal baseSalary;
    BigDecimal mealSubsidy;
    BigDecimal trafficSubsidy;
    BigDecimal accommodationSubsidy;
    Double commissionRate;
    String status;

    public FactoryUser(Long factoryId, String name, String contact, String cardId) {
        this.factoryId = factoryId;
        this.name = name;
        this.contact = contact;
        this.setCardId(cardId);
        this.password = hashPWD(DefaultPWD(this.contact).getBytes());
        //roles = new ArrayList<>();
        //roles.add(new FactoryRole(role));
        //this.groupType = role.group();
        this.status = Status.On.name();
    }

    public enum Status {
        On("在职"),
        Leave("离职"),
        Vacation("休假");

        private String text;
        Status(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static Status typeOf(String type) {
            if(StringUtils.isEmpty(type))
                return null;
            for(Status result : Status.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知状态");
        }
    }

    public static void main(String[] strs) throws Exception {
        /*System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
        KeyPair keyPair = RSAUtil.getKeyPair();
        String priKey = RSAUtil.getPrivateKey(keyPair);
        System.out.println("private key : \n" + priKey);
        String pubKey = RSAUtil.getPublicKey(keyPair);
        System.out.println("public key : \n" + pubKey);
        byte[] crypt = RSAUtil.encrypt("257503".getBytes(),RSAUtil.string2PublicKey(pubKey));
        String cryptStr = RSAUtil.byte2Base64(crypt);
        System.out.println("bas64密文:\n"+cryptStr);
        byte[] content = RSAUtil.decrypt(RSAUtil.base642Byte(cryptStr), RSAUtil.string2PrivateKey(priKey));
        System.out.println(new String(content));*/
        System.out.println(parseAge("360781999912105338"));
    }
     static String DefaultPWD(String contact) {
        return contact.substring(contact.length() - 6);
    }

     static String hashPWD(byte[] pwd) {
        return DigestUtils.md5DigestAsHex(pwd);
    }

    public boolean checkPWD(String pwd) {
        return Objects.equals(this.password, pwd);
    }

    public String decryptAndHashPwd(String encryptPwd) {
        byte[] pwd = RSAUtil.decrypt(RSAUtil.base642Byte(encryptPwd), this.PriKey);
        String hashPwd = hashPWD(pwd);
        return hashPwd;
    }

    public String token() {
        return UUID.randomUUID().toString();
    }

    public void setRoles(String roles) {
        if(StringUtils.isEmpty(roles))
            throw new IllegalArgumentException("角色为空");
        String[] strs = roles.split(",");
        this.roles = new ArrayList<>(strs.length);
        for(String role:strs) {
            this.roles.add(new FactoryRole(this.id, role));
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Boolean getMarital() {
        return marital;
    }

    public void setMarital(Boolean marital) {
        this.marital = marital;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCardId() {
        return cardId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCardId(String cardId) {
        if(!StringUtils.isEmpty(cardId)) {
            if(cardId.length() != 18)
                throw new RuntimeException("身份证号格式错误");
            try {
                this.cardId = cardId;
                this.age = parseAge(cardId);
            } catch (Exception ex) {
                throw new RuntimeException("身份证号格式错误");
            }
        }
    }

    private static Integer parseAge(String cardId) {
        Integer birthyear = Integer.parseInt(cardId.substring(6, 10));
        String birthday = cardId.substring(10, 14);
        Calendar calendar = Calendar.getInstance();
        Integer age = calendar.get(Calendar.YEAR) - birthyear;
        DateFormat df = new SimpleDateFormat("MMdd");
        age = df.format(calendar.getTime()).compareTo(birthday) < 0 ? age -1 : age;
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Educational getEducational() {
        return educational;
    }

    public void setEducational(Educational educational) {
        this.educational = educational;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = CommonUtil.parseDate(joinDate);
    }

    public FactoryUser(Long id) {
        this.id = id;
    }

    public List<FactoryRole> getRoles() {
        return roles;
    }

    public void setRoles(List<FactoryRole> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getMealSubsidy() {
        return mealSubsidy;
    }

    public void setMealSubsidy(BigDecimal mealSubsidy) {
        this.mealSubsidy = mealSubsidy;
    }

    public BigDecimal getTrafficSubsidy() {
        return trafficSubsidy;
    }

    public void setTrafficSubsidy(BigDecimal trafficSubsidy) {
        this.trafficSubsidy = trafficSubsidy;
    }

    public BigDecimal getAccommodationSubsidy() {
        return accommodationSubsidy;
    }

    public void setAccommodationSubsidy(BigDecimal accommodationSubsidy) {
        this.accommodationSubsidy = accommodationSubsidy;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public String toString() {
        return "FactoryUser{" +
                "id=" + id +
                ", factoryId=" + factoryId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", password='" + password + '\'' +
                ", groupType=" + groupType +
                ", joinDate=" + joinDate +
                ", roles=" + roles +
                ", avatar='" + avatar + '\'' +
                ", marital=" + marital +
                ", age=" + age +
                ", cardId='" + cardId + '\'' +
                ", address='" + address + '\'' +
                ", educational=" + educational +
                ", position='" + position + '\'' +
                ", baseSalary=" + baseSalary +
                ", mealSubsidy=" + mealSubsidy +
                ", trafficSubsidy=" + trafficSubsidy +
                ", accommodationSubsidy=" + accommodationSubsidy +
                ", commissionRate=" + commissionRate +
                '}';
    }

    public FactoryUser() {
    }
}
