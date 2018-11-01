package com.yuzi.denture.clinic.domain;

import com.yuzi.denture.clinic.util.RSAUtil;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ClinicUser {

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

    public ClinicUser(String name, ClinicRole role, String contact, String encryptPwd) {
        this.name = name;
        this.role = role;
        this.contact = contact;
        /*DateFormat formatter = new SimpleDateFormat("MMdd");
        this.name = String.format("yuzi_" + formatter.format(new Date()));*/
        this.password = decryptAndHashPwd(encryptPwd);
    }

    //编号(id):
    private Long id;
    //诊所编号(clinicId):
    private Clinic clinic;
    //角色(clinicRole):
    private ClinicRole role;
    //电话(contact):
    private String contact;
    //姓名(name):
    private String name;
    String password;

    public ClinicUser(Long id) {
        this.id = id;
    }

    public ClinicUser() {
    }

    public enum  ClinicRole {
        Dentist("医生"), Nurse("护士"), Other("其它");
        private String text;
        ClinicRole(String text) {
            this.text = text;
        }
        public String text() {
            return this.text;
        }
        public static ClinicRole typeOf(String type) {
            for(ClinicRole result : ClinicRole.values()){
                if(result.name().toLowerCase().equals(type.toLowerCase()))
                    return result;
            }
            throw new IllegalArgumentException("未知职称");
        }
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

    static String hashPWD(byte[] pwd) {
        return DigestUtils.md5DigestAsHex(pwd);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicRole getRole() {
        return role;
    }

    public void setRole(ClinicRole role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
