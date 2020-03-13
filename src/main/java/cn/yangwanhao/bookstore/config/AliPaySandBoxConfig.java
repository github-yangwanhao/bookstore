package cn.yangwanhao.bookstore.config;

import lombok.Data;

/**
 * @author 杨万浩
 * @description AlipaySandBoxConfig类
 * @date 2020/3/13 15
 */
@Data
public class AliPaySandBoxConfig {

    /**
     * 沙箱网关地址
     */
    private String gateway = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 返回值格式(目前只支持JSON)
     */
    private String format = "json";

    /**
     * 编码集，支持GBK/UTF-8
     */
    private String charset = "utf-8";

    /**
     * 支付宝公钥，由支付宝生成
     */
    private String aliPayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoaTSR+NWPbB+ZXyUzIuTyiY0oZ8xpI1pakdUpcbKYdSKolCqG18jy38AwqtACB0sjITblfl01tA/Az/jmGloIRJ8sePpA+YCmxYwcl8Z/DYKuoHsHOks15eTDxPy3HtCYfcFd2NZZdoZfA7sVUdmaR62t9hXGPrx9WBfframwWhXpiYujSENDd3+NExbjq4LWxbRPp0elOZaNlWPlOzb56UiSwbhk1JZsB50LxOTIAyJ5ofQz7pcCHv9z1ltrOnOxQ6qyP9MFeSEREhAx6sJXaR3mVWg90O5WRtIOdO5qGLLrzz1na1PtiwEYeonpOyjnhbopiA7TLfPp9G8wQoilwIDAQAB";

    /**
     * 开发应用私钥
     */
    private String aliPayPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOusYxLhddxjFrrlbpbJyxkbc/E7axq9oN3nL24WKNGWD28ouxV1su9xdZn+VskADG8pXaJ9AZixVV1hLu/9qcCDUqBzq53EW9qmMlKWs3+BhHKx9Dge++Rkg5TUYNZfbu9b/pO1OG0x95rJdoz9jF+RGOwa9Q3r4vebG1SRUsuMsuGFsToOwEO4Ltv+YSmaNsKMKcnBbTDEUZcSrLKi05c+7u5bPmM2M14FukUK4PbGuYWMeJOhVO5cZw1vH+LNBXbc/dWOS+juvDMG2RXlvcg6B54Rod5A8mtB8B16eaKo/rH2EJugzkAmPgvSrNkTloeSC9eeHEej2Kk/HV7uVTAgMBAAECggEAcP7U4SKIRSke/wzqqane84VU+Y711/shilFN2r3cc7Bf36FtjlyGR8w+pCm+TBBFTTvH/wn96fr+TRJJswTDYTvuOvO2PmZwoBNAoq8Dvy+eqNkuyxcXYh/qFdNjXkAXGl9cK51YJykFhiJpTAUgrLred5JG5VMyZguF8OuG4/vGMtNHf9XffiZ7DY3dX+jJzpilD5fEJ/R+kphCNmXT8poWzXRNJNjUXt7pTd+Dw4XA7He4sggUGJr1JfxWaGX4+rVfIG/1ziAVut1cNJZT1PXfzwiBzmg/XV3MmxKMfTcGb4RYTKNQ9qoFzeUAt6hOYx+lRGCUgS0I6k8WoPP+AQKBgQDfKDw4UDQkI0t0E0YYJJ4RjYOrm5Qj8xee08HGuJfqVPzy3LsAb6w1Xd1wJtvl2PneYf2iNhPO4T7pcG/h+BH2cPbS+7rpA5nWeAK7pzld5SeWc+L0Lx8d0vB5QIGAfzso3nz/Ao+CLaQk67scPcMUv5hrvA4KOLdLOdiKbnNhkwKBgQCjvFClzBol5wbJ0Mfd7VIa6/6JX/p9YsPPMKvz6uauiEQtKFC9Z4nWZME3faZF0cjgSpUlKIPkzx1P5uBNC4LBlOwiNqpd3moKmgykJhdLACxaNSk6e8uGn+a6nLrUeGiqD7gdEHvO5EKXz3m0lJ2pIf1XsSoJZCmu/3o/6V/FQQKBgQCayzoaheflOWZZehUhIiVTX6p6G01DMpir34+G3Pzj21gqztoYj14RFq9H4Gygn0WT8L0TDBfpZW1SWGADiyRKoklUSLF6eLj5f/3FVVYBv7caMgC4CQe4lF2ifhBWperPiqWB7rDFSAuYYLxF9sGfXtfHw4apqHXchJ+kRsFaaQKBgEUMx54EAwr08OHvUDqBONztpTxd4485NYaM6hdFet8jzEyW8eXOYkyt1OYH8TUEkYg1K8yt12GJDr2BihozvoLQwPx0vUg04ddyu94FylDw08N/I9HsHxq5GF6edhPga5M8adKn/u5zSLoo3xSOkCahlOuEZUvsl7+bMfz9+EuBAoGANtCnPdbKT9+Wi3Jd7rrKT0b4qq0JWPvjDXAzJi1vEFJpYcIrUoowZnz/nlx+VqNuO2GXo0HPk+6unsAOM/5ygpQiffmZL+yWUMiNEoPUrbgbIvH3ou5QKe7JEQYSJdqmNcRDjN8iwkQJ+ns2OUajZnJ948s/qPNVR56hWseYmQ8=";

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType = "RSA2";

    /**
     * 商户uid
     */
    private String appId = "2016092900623505";
}
